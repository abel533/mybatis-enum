package tk.mybatis.enums.enuminterfaces2;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
