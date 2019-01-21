package tk.mybatis.enums.defaultenumtypehandler;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
