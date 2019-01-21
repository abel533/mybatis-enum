package tk.mybatis.enums.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.enums.springboot.mapper.UserMapper;

import static org.junit.Assert.assertEquals;

/**
 * @author liuzh
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTest {

  @Autowired
  private UserMapper mapper;

  @Test
  public void shouldGetAUser() {
    User user = mapper.getUser(1);
    assertEquals("User1", user.getName());
    assertEquals(Sex.MALE, user.getSex());
    assertEquals(Constellations.Ram, user.getConstellations());
  }

  @Test
  public void shouldInsertAUser() {
    User user = new User();
    user.setId(2);
    user.setName("User2");
    user.setSex(Sex.FEMALE);
    user.setConstellations(Constellations.Fish);
    mapper.insertUser(user);
  }
}
