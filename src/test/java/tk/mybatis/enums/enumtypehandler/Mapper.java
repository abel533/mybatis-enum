package tk.mybatis.enums.enumtypehandler;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
