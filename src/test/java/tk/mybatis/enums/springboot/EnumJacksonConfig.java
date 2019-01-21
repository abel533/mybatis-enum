package tk.mybatis.enums.springboot;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author liuzh
 */

/**
 * @author liuzh
 */
@Configuration
public class EnumJacksonConfig {

  /**
   * 处理 LabelValue 子类的序列化
   */
  @JsonComponent
  public static class Serializer extends JsonSerializer<LabelValue> {
    @Override
    public void serialize(LabelValue labelValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
      //这里使用数值序列化，因此在反序列化时，直接 getIntValue
      jsonGenerator.writeNumber(labelValue.getValue());
    }
  }

  /**
   * 处理枚举类型的反序列化，特殊处理 LabelValue
   */
  @JsonComponent
  public static class EnumDeserializer
      extends JsonDeserializer<Enum> implements ContextualDeserializer {

    @Override
    public Enum deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
      //不使用当前的类进行反序列化
      return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
        throws JsonMappingException {
      //获取参数类型
      final Class<?> enumClass = ctxt.getContextualType().getRawClass();
      //如果继承的 LabelValue 接口，使用 LabelValue 反序列化器
      if (LabelValue.class.isAssignableFrom(enumClass)) {
        return new LabelValueDeserializer(enumClass);
      } else {
        //这里返回的默认的枚举策略
        return new DefaultEnumDeserializer(enumClass);
      }
    }
  }

  /**
   * LabelValue 子类枚举的反序列化
   */
  public static class LabelValueDeserializer extends JsonDeserializer<LabelValue> {
    private Class<?> enumClass;

    public LabelValueDeserializer(Class<?> enumClass) {
      this.enumClass = enumClass;
    }

    @Override
    public LabelValue deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
      //获取序列化值
      int intValue = p.getIntValue();
      //遍历返回枚举值
      for (Object enumConstant : enumClass.getEnumConstants()) {
        if (((LabelValue) enumConstant).getValue().equals(intValue)) {
          return (LabelValue) enumConstant;
        }
      }
      //可以根据逻辑返回 null 或者抛出非法参数异常
      return null;
    }
  }

  /**
   * 默认枚举反序列化
   */
  public static class DefaultEnumDeserializer extends JsonDeserializer<Enum> {
    private Class<?> enumClass;

    public DefaultEnumDeserializer(Class<?> enumClass) {
      this.enumClass = enumClass;
    }

    @Override
    public Enum deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
      JsonToken token = p.getCurrentToken();
      //枚举可能是用的 name() 方式，也就是字符串
      if (token == JsonToken.VALUE_STRING) {
        String value = p.getValueAsString();
        for (Object enumConstant : enumClass.getEnumConstants()) {
          if (((Enum) enumConstant).name().equals(value)) {
            return (Enum) enumConstant;
          }
        }
      }
      //也可能使用的 ordinal() 数值方式
      else if (token == JsonToken.VALUE_NUMBER_INT) {
        int intValue = p.getIntValue();
        for (Object enumConstant : enumClass.getEnumConstants()) {
          if (((Enum) enumConstant).ordinal() == intValue) {
            return (Enum) enumConstant;
          }
        }
      }
      //可以根据逻辑返回 null 或者抛出非法参数异常
      return null;
    }
  }

}
