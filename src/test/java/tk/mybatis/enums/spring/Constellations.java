package tk.mybatis.enums.spring;

/**
 * @author liuzh
 */
public enum Constellations implements LabelValue {
  WaterCarrier(1),
  Fish(2),
  Ram(3),
  Bull(4),
  Twins(5),
  Crab(6),
  Lion(7),
  Virgin(8),
  Scales(9),
  Scorpion(10),
  Archer(11),
  Goat(12);

  private Integer value;

  Constellations(Integer value) {
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