package tk.mybatis.enums.springboot;

import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.enums.springboot.mapper.UserMapper;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Configuration
  public static class WebConfig {

    @RestController
    @RequestMapping("/users")
    public static class UserController {

      @Autowired
      private UserMapper userMapper;

      @RequestMapping("/{id}")
      public User user(@PathVariable("id") Integer id) {
        return userMapper.getUser(id);
      }

      @RequestMapping("/save")
      public String save(@RequestBody User user) {
        userMapper.insertUser(user);
        return "success";
      }
    }

  }

  @Configuration
  public class MyBatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
      return new ConfigurationCustomizer() {
        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
          TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
          registry.register(tk.mybatis.enums.springboot.LabelValueTypeHandler.class);
        }
      };
    }
  }
}
