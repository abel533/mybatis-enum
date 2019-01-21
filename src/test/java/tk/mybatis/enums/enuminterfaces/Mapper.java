package tk.mybatis.enums.enuminterfaces;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
