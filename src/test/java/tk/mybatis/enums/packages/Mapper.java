package tk.mybatis.enums.packages;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
