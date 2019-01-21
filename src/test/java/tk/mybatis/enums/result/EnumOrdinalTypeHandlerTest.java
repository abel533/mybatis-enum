package tk.mybatis.enums.result;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class EnumOrdinalTypeHandlerTest {

  protected static SqlSessionFactory sqlSessionFactory;

  @BeforeClass
  public static void setUp() throws Exception {
    // create an SqlSessionFactory
    Reader reader = Resources.getResourceAsReader("tk/mybatis/enums/result/mybatis-config.xml");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    reader.close();

    // populate in-memory database
    SqlSession session = sqlSessionFactory.openSession();
    Connection conn = session.getConnection();
    reader = Resources.getResourceAsReader("tk/mybatis/enums/result/CreateDB.sql");
    ScriptRunner runner = new ScriptRunner(conn);
    runner.setLogWriter(null);
    runner.runScript(reader);
    reader.close();
    session.close();
  }

  @Test
  public void shouldGetAUser1() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User user = mapper.getUser1(1);
      assertEquals("User1", user.getName());
      assertEquals(Sex.MALE, user.getSex());
    } finally {
      sqlSession.close();
    }
  }

  @Test
  public void shouldGetAUser2() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User user = mapper.getUser2(1);
      assertEquals("User1", user.getName());
      assertEquals(Sex.MALE, user.getSex());
    } finally {
      sqlSession.close();
    }
  }

  @Test
  public void shouldGetAUser3() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User user = mapper.getUser3(1);
      assertEquals("User1", user.getName());
      assertEquals(Sex.MALE, user.getSex());
    } finally {
      sqlSession.close();
    }
  }

  @Test
  public void shouldGetAUser4() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User user = mapper.getUser4(1);
      assertEquals("User1", user.getName());
      assertEquals(Sex.MALE, user.getSex());
    } finally {
      sqlSession.close();
    }
  }

  @Test
  public void shouldInsertAUser() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User user = new User();
      user.setId(2);
      user.setName("User2");
      user.setSex(Sex.FEMALE);
      mapper.insertUser(user);
    } finally {
      sqlSession.close();
    }
  }
}
