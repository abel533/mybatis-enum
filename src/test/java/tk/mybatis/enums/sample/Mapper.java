package tk.mybatis.enums.sample;

public interface Mapper {
  User getUser(Integer id);

  void insertUser(User user);
}
