package tk.mybatis.enums.result;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

public interface Mapper {

  User getUser1(Integer id);

  User getUser2(Integer id);

  @ConstructorArgs({
      @Arg(column = "id", javaType = Integer.class, id = true),
      @Arg(column = "name", javaType = String.class),
      @Arg(column = "sex", javaType = Sex.class, typeHandler = EnumOrdinalTypeHandler.class),
  })
  @Select("select * from users where id = #{id}")
  User getUser3(Integer id);

  @Results({
      @Result(column = "id", property = "id", javaType = Integer.class, id = true),
      @Result(column = "name", property = "name", javaType = String.class),
      @Result(column = "sex", property = "sex", javaType = Sex.class, typeHandler = EnumOrdinalTypeHandler.class),
  })
  @Select("select * from users where id = #{id}")
  User getUser4(Integer id);

  void insertUser(User user);

}
