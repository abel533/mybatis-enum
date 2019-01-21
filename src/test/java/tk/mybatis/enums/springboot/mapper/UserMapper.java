package tk.mybatis.enums.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.enums.springboot.User;

@Mapper
public interface UserMapper {
  User getUser(Integer id);

  void insertUser(User user);
}
