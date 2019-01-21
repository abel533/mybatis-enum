package tk.mybatis.enums.spring;

/**
 * @author liuzh
 */
public enum Sex implements LabelValue {
  MALE(1),
  FEMALE(2);

  private Integer value;

  Sex(Integer value) {
    this.value = value;
  }


  @Override
  public String getLabel() {
    return name();
  }

  @Override
  public Integer getValue() {
    return value;
  }

}