package tk.mybatis.enums.enuminterfaces2;

public class User {
  private Integer id;
  private String name;
  private Sex sex;
  private Constellations constellations;

  public Constellations getConstellations() {
    return constellations;
  }

  public void setConstellations(Constellations constellations) {
    this.constellations = constellations;
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
