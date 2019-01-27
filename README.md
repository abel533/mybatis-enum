连续好几天都在写这篇 GitChat，加起来的时间可能已经超过20多小时了。

本来想和之前的 Chat 一样发布后在 1 个月后免费，现在发现制度变了，付费文章需要等待 1 年后才能免费，想要参与的可以点击底部阅读原文查看。

为了能让更多人尽可能了解枚举的用法，本场 GitChat 文章对应的示例源码会同步放到 github 上，看不到文章的朋友也可以看示例源码进行了解。

文章写的非常详细，大概有20多页，下面是个目录以及开头的一部分内容（最终发布前仍然可能调整）。

>全文地址：https://gitbook.cn/gitchat/activity/5c31e09e0be52863d8388be4

## MyBatis 枚举全面使用指南

### 1. 从最早版本的用法说起
MyBatis 从一开始就自带了两个枚举的类型处理器 `EnumTypeHandler` 和 `EnumOrdinalTypeHandler`，这两个枚举类型处理器可以用于最简单情况下的枚举类型。

为了方便下面的讲解，先假设有如下简单的枚举类型：
```java
package tk.mybatis.enums.enumordinaltypehandler;

public enum Sex {
  MALE, FEMALE
}
```

#### 1.1 EnumTypeHandler
这个类型处理器是 MyBatis 中默认的枚举类型处理器，他的作用是将枚举的名字和枚举类型对应起来。对于 `Sex` 枚举来说，存数据库时会使用 `"MALE"` 或者 `"FEMALE"` 字符串存储，从数据库取值时，会将字符串转换为对应的枚举。

#### 1.2 EnumOrdinalTypeHandler
这是另一个枚举类型处理器，他的作用是将枚举的索引和枚举类型对应起来。对于 `YesNoEnum ` 枚举来说，存数据库时会使用枚举对应的顺序 `0(MALE)` 或者 `1(FEMALE)` 存储，从数据库取值时，会将整型顺序号(int)转换为对应的枚举。

#### 1.3 如何配置这两种枚举类型

因为 `EnumTypeHandler` 是默认的枚举处理器，所以默认不做任何配置的情况下，就使用的这个类型处理器，因此如果需要存储 `"MALE"` 或者 `"FEMALE"` 值，就不需要任何配置。

如果想存储枚举对应的索引，可以按照下面的方式进行配置：
```xml
<typeHandlers>
  <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler"
               javaType="tk.mybatis.enums.enumordinaltypehandler.Sex"
               jdbcType="VARCHAR"/>
</typeHandlers>
```

还可以省略 `jdbcType="VARCHAR"` 属性，按下面方式进行配置：
```xml
<typeHandlers>
  <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler"
               javaType="tk.mybatis.enums.enumordinaltypehandler.Sex"/>
</typeHandlers>
```

按上述方式进行配置后，就可以使用索引值存库，如果有很多枚举怎么办，难道还要一个个进行配置吗？目前有很多方式可以解决这个问题，不同 MyBatis 版本可以通过不同的方式去处理，后面会一一说明。

当你看到上面这两种配置的变化时，会不会有种心虚的感觉，以后遇到类似情况时会不会无从下手？

既然枚举用法只是类型处理器的一种，而类型处理器又存在着各种变化，我们不妨先深入看看类型处理器的处理过程。

>本文主要的配置都使用的 mybatis-3-config 格式的配置文件，**********后续和 Spring 及 Spring Boot 集成部分也有相应的配置方式**********。

### 2. 深入了解 TypeHandler 类型处理器
#### 2.1 只有 `typeHandlerClass` 时
#### 2.2 当提供 `typeHandlerClass` 和 `javaTypeClass` 时
#### 2.3 当这三个属性都提供时
#### 2.4 查找类型处理器
### 3. 配置类型处理器
#### 3.1 全局配置
##### 3.1.1 `<typeHandler>` 方式
##### 3.1.2 `<package>` 方式
#### 3.2 局部配置
##### 3.2.1 查询结果的映射
##### 3.2.2 `#{attr}` 方式
### 4. MyBatis 3.4.5+
#### 4.1 根据更新代码变化来学习新功能
##### 4.1.1 MyBatis 3.2.8 版本代码
##### 4.1.2 MyBatis 3.4.5 版本代码
##### 4.1.3 对比版本变化
#### 4.2 枚举接口的用法
##### 4.2.1 定义接口
##### 4.2.2 定义两个枚举类
##### 4.2.3 实现接口对应的类型处理器
##### 4.2.4 配置类型处理器
#### 4.3 默认枚举处理器
#### 4.4 继承形式的类型处理器
### 5. Spring 集成

由于 Spring 和 Spring Boot 配置方式不同，这里分别讲解各自的配置方式。

### [5.1 Spring XML 配置](https://github.com/abel533/mybatis-enum/tree/master/src/test/java/tk/mybatis/enums/spring)

Spring 集成 MyBatis 的时候，配置 `SqlSessionFactoryBean` 时如果指定 `configLocation`，就可以用 MyBatis 配置方式配置，示例如下：

```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"/>
  <property name="configLocation" value="classpath:tk/mybatis/enums/spring/mybatis-config.xml"/>
</bean>
```

如果你真用的这种方式，也没任何问题，如果我只提供这么一个方案就有点说不过去了。下面看看纯 Spring 的配置方式：

```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"/>
  <property name="typeHandlers">
    <array>
      <!--很明显这里没法配置 `javaType`，因此必须使用 @MappedTypes(LabelValue.class) 注解-->
      <bean class="tk.mybatis.enums.spring.LabelValueTypeHandler">
        <!--由于没有默认无参的构造方法，所以只能指定构造参数，实际上给这个类型处理器提供一个无参的构造方法会更简单，也没有错-->
        <constructor-arg index="0" value="tk.mybatis.enums.spring.LabelValue"/>
      </bean>
    </array>
  </property>
</bean>
```

`SqlSessionFactoryBean` 提供了 `typeHandlers` 属性配置，因此可以用这种方式进行配置。但是这种方式配置有点特殊的地方，在用这种方式时，无法指定 `javaType` 类型，因此上面的例子用了 `@MappedTypes(LabelValue.class)` 注解。
除此之外，配置 `LabelValueTypeHandler` 因为构造方法的原因，要多配置一个 `<constructor-arg>`，如果增加一个默认无参的构造方法，例如下面的代码：

```java
@MappedTypes(LabelValue.class)
public class LabelValueTypeHandler<E extends LabelValue> extends BaseTypeHandler<E> {
  private Class<E> type;
  private Map<Integer, E> enumMap;

  public LabelValueTypeHandler() {
  }

  public LabelValueTypeHandler(Class<E> type) {
    //省略其他
  }

```

此时上面的配置可以简化为：

```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"/>
  <property name="typeHandlers">
    <array>
      <bean class="tk.mybatis.enums.spring.LabelValueTypeHandler"/>
    </array>
  </property>
</bean>
```

这些都是技巧，结合本文所有示例看 **2. 深入了解 TypeHandler 类型处理器** 时会加深理解，对这种用法会了解的更多。

除了 `typeHandlers` 配置外，`SqlSessionFactoryBean` 还提供了 `typeAliasesPackage` 配置类型处理器的包名，通过扫描包获取包下所有类型处理器，示例如下：

```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"/>
  <property name="typeHandlersPackage" value="tk.mybatis.enums.spring"/>
</bean>
```

如果想在纯 Spring 配置情况下配置默认枚举类型处理，可以用下面的方式：
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"/>
  <property name="configuration">
    <bean class="org.apache.ibatis.session.Configuration">
      <property name="defaultEnumTypeHandler" value="tk.mybatis.enums.spring.LabelValueTypeHandler"/>
    </bean>
  </property>
</bean>
```

针对 Spring XML 配置方式就这几种，下面看看 Spring Boot 如何配置。

#### 5.2 Spring Boot 配置
#### 5.3 Spring MVC 中的枚举转换
### 6. 总结

