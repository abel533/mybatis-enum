package tk.mybatis.enums.defaultenumtypehandler;

public class User {
  private Integer id;
  private String name;
  private Sex sex;

  public User() {
  }

  public User(Integer id, String name, Sex sex) {
    this.id = id;
    this.name = name;
    this.sex = sex;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }
}
