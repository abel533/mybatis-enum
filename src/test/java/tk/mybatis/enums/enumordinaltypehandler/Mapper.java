package tk.mybatis.enums.enumordinaltypehandler;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
