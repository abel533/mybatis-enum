package tk.mybatis.enums.spring.mapper;

import tk.mybatis.enums.spring.User;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
