package tk.mybatis.enums.packages.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.MappedTypes;
import tk.mybatis.enums.packages.Sex;

/**
 * @author liuzh
 */
@MappedTypes({Sex.class})
public class MyEnumOrdinalTypeHandler extends EnumOrdinalTypeHandler {

  public MyEnumOrdinalTypeHandler(Class type) {
    super(type);
  }

}
