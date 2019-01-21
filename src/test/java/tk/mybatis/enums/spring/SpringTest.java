package tk.mybatis.enums.spring;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.enums.spring.mapper.Mapper;

import static org.junit.Assert.assertEquals;

public class SpringTest {
  protected static ClassPathXmlApplicationContext context;

  @BeforeClass
  public static void setUp() {
    context = new ClassPathXmlApplicationContext("classpath:tk/mybatis/enums/spring/spring.xml");
  }

  @Test
  public void shouldGetAUser() {
    Mapper mapper = context.getBean(Mapper.class);
    User user = mapper.getUser(1);
    assertEquals("User1", user.getName());
    assertEquals(Sex.MALE, user.getSex());
    assertEquals(Constellations.Ram, user.getConstellations());
  }

  @Test
  public void shouldInsertAUser() {
    Mapper mapper = context.getBean(Mapper.class);
    User user = new User();
    user.setId(2);
    user.setName("User2");
    user.setSex(Sex.FEMALE);
    user.setConstellations(Constellations.Fish);
    mapper.insertUser(user);
  }
}
