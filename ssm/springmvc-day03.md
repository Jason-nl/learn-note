## Springmvc-day03

## 目录

[TOC]

## 学习目的

## 第一章 知识点梳理

### 【1】Spring

Spring是一个开源框架，Spring的核心是控制反转（IoC）和面向切面（AOP）。简单来说，Spring是一个分层的JavaSE/EE full-stack(一站式) 轻量级开源框架。

#### 【1.2】Spring-IOC

##### 【定义】

IoC 全称为 Inversion of Control，翻译为 “控制反转”，控制对象的创建和销毁，将对象的控制权（创建和销毁）交给IOC容器

##### 【原理】

```properties
【1】读取配置文件中类的全限定名通过反射机制创建对象。  
【2】把创建出来的对象事先都存起来，当我们使用时可以直接从存储容器中获取。 
    存哪去？   
    由于我们是很多对象，肯定要找个集合来存。这时候有 Map 和 List 供选择。      
    到底选 Map 还是 List 就看我们有没有查找需求。有查找需求，选 Map。   
    所以我们的答案就是在应用加载时，创建一个 Map，用于存放bean对象。
    我们把这个 map 称之为容器。
```

##### 【工厂类】

BeanFactory：采用的是延迟加载的思想。即什么时候使用对象，什么时候创建

ApplicationContext：采用立即创建的思想。即一加载配置文件，立即就创建

#### 【1.3】Spring-DI

##### 【定义】

依赖注入：Dependency Injection（简称DI注入）。它是spring框架核心 ioc的具体实现。

##### 【原理】

```properties
简单来说依赖注入：属性赋值

依赖注入的2种：
	set方法注入（P标签方式）
	构造函数注入（C标签方式）
	
使用set方法注入集合属性：
    array:一般用来设置数组
    list:一般用来设置list集合
    map:一般用来设置map集合
    props:一般用来设置properties

```

#### 【1.4】Spring-AOP

##### 【定义】

AOP是面向切面编程，使用动态代理技术，实现在不修改java源代码的情况下，运行时实现方法功能的增强

##### 【原理】

```properties
动态代理：
	jdk动态代理:必须基于接口
	cglib动态代理:基于类，无需实现接口；
	
相关概念：
    Joinpoint（连接点）:
        在spring中，连接点指的都是方法（指的是那些要被增强功能的候选方法）
    Pointcut（切入点）:
        切入点是指我们要对哪些Joinpoint进行拦截的定义。
    Advice（通知）:
        通知指的是拦截到Joinpoint之后要做的事情。即增强的功能
        通知类型：前置通知、后置通知、异常通知、最终通知、环绕通知
            1、前置通知：
                在目标方法执行前执行
            2、后置通知：
                在目标方法正常返回后执行。它和异常通知只能执行一个
            3、异常通知：
                在目标方法发生异常后执行。它和后置通知只能执行一个
            4、最终通知：
                无论目标方法正常返回，还是发生异常都会执行
            5、环绕通知： 
                在目标方法执行前后执行该增强方法
    Target（目标对象）:
        被代理的对象。
    Proxy（代理对象）:
        一个类被AOP织入增强后，即产生一个结果代理类。
    Weaving（织入）:
        织入指的是把增强用于目标对象。创建代理对象的过程
    Aspect（切面）:
        切面指的是切入点和通知的结合`	
```



#### 【1.5】Spring-TX

##### 【定义】

​	事务是数据库操作的最小工作单元，是作为单个逻辑工作单元执行的一系列操作，这些操作作为一个整体一起向系统提交，要么都执行、要么都不执行；事务是一组不可再分割的操作集合 

##### 【原理】

```properties
是spring-AOP的一个具体实现方案
在J2EE的三层模式中，我们的事务管理放在那一层更合适==>service层
```

##### 【常用类】

PlatformTransactionManager【事务平台管理器】：是一个接口，定义了获取事务、提交事务、回滚事务的接口

TransactionDefinition【事务定义信息】：是一个接口,定义了事务隔离级别、事务传播行为、事务超时时间、事务是否只读 

【事务隔离级别】

![image-20191121132020256](image\image-20191121132020256.png)

【事务传播行为】

![image-20191121132118479](image\image-20191121132118479.png)

【spring事务工作】

1、Spring框架进行事务的管理，首先使用TransactionDefinition对事务进行定义。
2、PlatformTransactionManager根据TransactionDefinition的定义信息进行事务的管理。
3、在事务管理过程中产生一系列的状态：保存到TransactionStatus中。

TransactionStatus 用于保存当前事物状态 ，定义了一个简单的控制事务执行和查询事务状态的方法 

### 【2】Spring-MVC

#### 【定义】

 一种轻量级的、基于MVC的Web层应用框架。偏前端而不是基于业务逻辑层。Spring框架的一个后续产品 

- Spring 为展现层提供的基于 MVC 设计理念的优秀的 Web 框架

- Spring3.0 后全面超越 Struts2，成为最优秀的 MVC 框架。

- 通过一套 MVC 注解，让 POJO 成为处理请求的控制器，而无须实现任何接口。

- 支持 REST 风格的 URL 请求。

- 采用了松散耦合可插拔组件结构，比其他 MVC 框架更具扩展性和灵活性。

#### 【原理】

![image-20191204170408642](image\image-springMVC架构.png)

#### 【常用类】

DispatcherServlet：客户端发送http请求呗dispatcherServlet所拦截

RequestMappingHandlerMapping：getHandler(processedRequest)方法实际上就是从HandlerMapping中找到url和Controller的对应关系 ,具体实现是在抽象类AbstractHandlerMapping中，而RequestMappingHandlerMapping是使用注解方式时所使用的处理器映射器

RequestMappingHandlerAdapter：doDispatch方法中带着从getHandler(processedRequest)获得mappedHandler调用getHandlerAdapter(mappedHandler.getHandler())方法，获得在ApplicationObjectSupport中初始化的List<HandlerAdapter> handlerAdapters中对应的HandlerAdapter

ViewResolver：视图解析器

### 【3】Mybatis

#### 【定义】

 MyBatis 使用简单的 XML 或注解用于配置和原始映射，将接口和 Java 的 POJOs（Plain Old Java Objects，普通的 Java 对象）映射成数据库中的记录。 

#### 【原理】

 ![MyBatis框架的执行流程图](image\5-1ZF4130T31N.png) 

### 【4】注解汇总

​		

#### 【Spring-IOC相关】

| 注解                                                         | xml                         | 说明                      |
| ------------------------------------------------------------ | --------------------------- | ------------------------- |
| @Component父注解<br/>      @Controller:用于响应层的注解<br/>      @Service:用于业务层的注解<br/>      @Repository:一般用于持久层的注解 | < bean id="" class="">      | 声明bean交于springIOC管理 |
| @Scope                                                       | scope=“singleton/prototype” | 生命周期                  |
| @PostConstruct                                               | init-method                 | 初始化方法                |
| @PreDestroy                                                  | destroy-method              | 销毁方法                  |

#### 【Spring-DI相关】

| 注解                   | xml                | 说明                       |
| ---------------------- | ------------------ | -------------------------- |
| @Autowired、@Qualifier | ref="类型"         | 默认按照bean的类型注入数据 |
| @Resource              | ref="类型"         | 默认按照bean的名称注入数据 |
| @Value                 | ref="基础数据类型" | 给基本数据类型赋值         |

#### 【Spring-AOP相关】

| 注解            | xml                    | 说明         |
| --------------- | ---------------------- | ------------ |
| @Aspect         | <aop:aspect >          | 声明切面     |
| @Before         | <aop:before >          | 前置通知     |
| @AfterReturning | <aop:after-returning > | 后置正常通知 |
| @AfterThrowing  | <aop:after-throwing >  | 后置异常通知 |
| @After          | <aop:after >           | 最终通知     |
| @Around         | <aop:around >          | 环绕通知     |

#### 【Spring-TX相关】

@Transactional 

@Transactional 注解可以作用于接口、接口方法、类以及类方法上，但是 Spring 建议不要在接口或者接口方法上使用该注解，因为这只有在使用基于接口的代理时它才会生效。另外， @Transactional 注解应该只被应用到 public 方法上，这是由 Spring AOP 的本质决定的。如果你在 protected、private 或者默认可见性的方法上使用 @Transactional 注解，这将被忽略，也不会抛出任何异常。 

【属性】

| 属性                   | 类型                               | 说明                                   |
| ---------------------- | ---------------------------------- | -------------------------------------- |
| value                  | String                             | 可选的限定描述符，指定使用的事务管理器 |
| propagation            | enum: Propagation                  | 可选的事务传播行为设置                 |
| isolation              | enum: Isolation                    | 可选的事务隔离级别设置                 |
| readOnly               | boolean                            | 读写或只读事务，默认读写               |
| timeout                | int (in seconds granularity)       | 事务超时时间设置                       |
| rollbackFor            | Class对象数组，必须继承自Throwable | 导致事务回滚的异常类数组               |
| rollbackForClassName   | 类名数组，必须继承自Throwable      | 导致事务回滚的异常类名字数组           |
| noRollbackFor          | Class对象数组，必须继承自Throwable | 不会导致事务回滚的异常类数组           |
| noRollbackForClassName | 类名数组，必须继承自Throwable      | 不会导致事务回滚的异常类名字数组       |

#### 【Spring-JUNIT相关】

| 注解                  | 说明                            |
| --------------------- | ------------------------------- |
| @RunWith              | 指定使用SpringJUnit4ClassRunner |
| @ContextConfiguration | 指定加载配置文件                |

#### 【Spring-MVC相关】

| 注解              | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| @RequestMapping   | 配置映射地址                                                 |
| @GetMapping       | 配置映射地址GET:得到资源                                     |
| @PutMapping       | 配置映射地址PUT:修改整体内容                                 |
| @PostMapping      | 配置映射地址POST:新增内容                                    |
| @DeleteMapping    | 配置映射地址DELETE：删除内容                                 |
| @PatchMapping     | 配置映射地址PATCH:修改部分内容                               |
| @PathVariable     | 绑定URL中的参数值                                            |
| @RequestParam     | 绑定单个请求数据，既可以是URL中的参数，也可以是表单提交的参数 |
| @RequestBody      | 请求参数格式为json                                           |
| @RestController   | 注释在类上，生命一个bean，且表示此类中返回类型都是json（@Controller+@ResponseBody） |
| @ResponseBody     | 注解在方法上，表示此方法返回类型为json                       |
| @ExceptionHandler | 异常处理                                                     |
| @ControllerAdvice | 对controller层进行增强                                       |

#### 【配置类相关】

| 注解                         | XML                             | 说明              |
| ---------------------------- | ------------------------------- | ----------------- |
| @Configuration               | /                               | 声明此类为配置类  |
| @EnableAspectJAutoProxy      | <aop:aspectj-autoproxy >        | 开启AOP           |
| @EnableTransactionManagement | <tx:annotation-driven >         | 开启事务管理器    |
| @EnableWebMvc(*)             | <mvc:annotation-driven >        | 开启springMVC     |
| @PropertySource              | <context:property-placeholder > | 导入外部配置      |
| @MapperScan(*)               | /                               | mybatis的扫描配置 |
| @Bean                        | < bean >                        | 声明bean          |
| @ComponentScan               | <context:component-scan >       | 扫描配置          |

​		java config是指基于java配置的spring。传统的Spring一般都是基本xml配置的，后来spring3.0新增了许多java config的注解，特别是spring boot，基本都是清一色的java config。

**考虑使用JavaConfig替代XML配置**

​		对于总是固执地使用Spring的XML配置方式，同事们总是讥讽我。是的，这看起来太二太过时了，不过XML还是有它的优势：

​		1.集中式配置。这样做不会将不同组件分散的到处都是。你可以在一个地方看到所有Bean的概况和他们的装配关系。

​		2.如果你需要分割配置文件，没问题，Spring可以做到。它可以在运行时通过<import>标签或者上Context文件对分割的文件进行重新聚合。

​		3.相对于自动装配(autowiring)，只有XML配置允许显示装配(explicit wiring)

​		4.最后一点并不代表不重要，XML配置完全和JAVA文件解耦：两种文件完全没有耦合关系，这样的话，类可以被用作多个不同XML配置文件。

​		XML唯一的问题是，只有在运行时环境时你才能发现各种配置，但是如果使用Spring IDE Plugin(或者STS)的话，它会在编码时提示这些问题。

在XML配置和直接注解式配置之外还有一种有趣的选择方式-JavaConfig，它是在Spring 3.0开始从一个独立的项目并入到Spring中的。它结合了XML的解耦和JAVA编译时检查的优点。JavaConfig可以看成一个XML文件，只不过是使用Java编写的。相关文档在官方网站是可以找到的

## 第二章 SSM框架集成【重点】

【前置说明】

1、选择对于的组件，三层架构===》职能



### 1、需求分析

```properties
#添加用户
	【用户列表】页面点击【添加用户】按钮，跳转【用户新增】页面，【用户新增】页面点击【提交】按钮
提交表单，保存数据入库。

#删除用户
	【用户列表】页面点击【删除】按钮，弹出确定页面，点击【确定】，则删除数据，刷新【用户列表】

#修改用户
	【用户列表】页面点击【修改】按钮，跳转【用户编辑】页面，【用户编辑】页面点击【提交】按钮
提交表单，修改数据入库。

#用户列表
	用户已经登录，直接访问用户列表数据

#日志模块
	用户任何操作都有日志信息打印
```

### 2、方案思路

#### 【1】三层架构

![image-20191203145812960](image\image-20191203145812960.png)

![1597219508746](image\1597219508746.png)

#### 【2】职责分析

##### 【2.1】Spring容器职责(父容器)

```properties
1、管理所有bean对象==>IOC==扫描
2、日志功能==>AOP
3、开启事务管理器==>AOP
```

##### 【2.2】SpringMVC容器职责（子容器）

```properties
1、请求的映射关系===>处理器映射器
2、响应请求参数的处理===>处理器适配器
3、渲染视图==>视图解析器
```

##### 【2.3】Mybatis职责

```properties
数据库操作==>sqlSessionFactory
事务管理器
sqlsessionfactory
```

### 3、前期准备

#### 【1】数据库脚本

```sql
-- ----------------------------
-- Table structure for customer
-- ----------------------------
CREATE TABLE `customer` (
  `cust_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `cust_address` varchar(128) DEFAULT NULL COMMENT '客户联系地址',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '客户联系电话',
  `cust_create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '传智播客', '网络营销', '互联网', '普通客户', '津安创意园', '0208888887', '2020-01-03 10:09:24');
INSERT INTO `customer` VALUES ('2', '黑马程序员', '网络营销', '互联网', '普通客户', '津安创意园', '0208888887', '2020-01-03 10:09:27');
INSERT INTO `customer` VALUES ('3', '传智专修学院', '网络营销', '互联网', '普通客户', '津安创意园', '0208888887', '2020-01-03 10:09:31');
INSERT INTO `customer` VALUES ('4', '华山派', '电视广告', '传统媒体', 'VIP', '津安创意园', '0208888886', '2020-01-03 10:09:33');
INSERT INTO `customer` VALUES ('5', '武当派', '电视广告', '传统媒体', 'VIP', '津安创意园', '0208888886', '2020-01-03 10:09:37');
INSERT INTO `customer` VALUES ('6', '丐帮', '电视广告', '传统媒体', 'VIP', '津安创意园', '0208888886', '2020-01-03 10:09:39');

```



#### 【2】搭建基础结构

如下图，建立springmvc-day03-project的web项目

![image-20200103105323224](image\image-20200103105323224.png)

##### 【2.1】pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.itheima.spring</groupId>
  <artifactId>springmvc-day03-project</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>springmvc-day03-project</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--spring 版本-->
    <spring.version>5.1.11.RELEASE</spring.version>
    <!-- log4j日志版本 -->
    <log4j.version>2.8.2</log4j.version>
    <!-- jstl标签版本 -->
    <jstl.version>1.2</jstl.version>
    <!--servlet版本-->
    <servlet.version>3.0.1</servlet.version>
    <!-- mybatis版本 -->
    <mybatis.version>3.4.5</mybatis.version>
    <!--mysql驱动版本-->
    <mysql.version>5.1.30</mysql.version>
    <!-- mybatis-spring整合包版本 -->
    <mybatis.spring.version>1.3.1</mybatis.spring.version>
    <!--druid版本-->
    <druid.version>1.0.29</druid.version>
    <!--jackson版本-->
    <jackson.version>2.9.0</jackson.version>
  </properties>

  <dependencies>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>${jstl.version}</version>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>${servlet.version}</version>
      <scope>provided</scope>
    </dependency>

    <!--spring 容器依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!--spring-jdbc依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!--spring 事务依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!--spring aop依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!--spring mvc容器依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!--mybatis依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>${mybatis.version}</version>
    </dependency>

    <!--mybatis-spring依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>${mybatis.spring.version}</version>
    </dependency>

    <!--druid依赖-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>${druid.version}</version>
    </dependency>

    <!-- mysql驱动依赖 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>${mysql.version}</version>
    </dependency>

    <!-- log4j2驱动依赖 -->
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-api</artifactId>
      <version>${log4j.version}</version>
    </dependency>

    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>${log4j.version}</version>
    </dependency>

    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-web</artifactId>
      <version>${log4j.version}</version>
    </dependency>

    <!--jackson依赖包-->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>${jackson.version}</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>${jackson.version}</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>${jackson.version}</version>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- tomcat7插件,命令： mvn tomcat7:run -DskipTests -->
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
          <uriEncoding>utf-8</uriEncoding>
          <port>8080</port>
          <path>/platform</path>
        </configuration>
      </plugin>
      <!-- 指定编译jdk的版本 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>8</source>
          <target>8</target>
        </configuration>
      </plugin>
    </plugins>
    <finalName>springmvc-day03-project</finalName>
  </build>
</project>

```

##### 【2.2】pojo

Customer用户实体类

```java
package com.itheima.project.pojo;

import java.util.Date;

/**
 * @Description：用户实体类
 */
public class Customer {

    //客户编号(主键)
    private Integer custId;

    //客户名称(公司名称)
    private String custName;

    //客户信息来源
    private String custSource;

    //客户所属行业
    private String custIndustry;

    //客户级别
    private String custLevel;

    //客户联系地址
    private String custAddress;

    //客户联系电话
    private String custPhone;

    //创建时间
    private Date custCreateTime;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Date getCustCreateTime() {
        return custCreateTime;
    }

    public void setCustCreateTime(Date custCreateTime) {
        this.custCreateTime = custCreateTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custCreateTime=" + custCreateTime +
                '}';
    }
}

```



##### 【2.3】mapper

CustomerMapper.java

```java
package com.itheima.project.mapper;

import com.itheima.project.pojo.Customer;

import java.util.List;

/**
 * @Description：持久化层
 */
public interface CustomerMapper {

    /**
     * @Description 保存用户
     * @param customer 用户对象信息
     * @return 影响行数
     */
    Integer saveCustomer(Customer customer);

    /**
     * @Description 删除用户
     * @param custId 用户Id
     * @return 影响行数
     */
    Integer deleteCustomer(Integer custId);

    /**
     * @Description 修改用户
     * @param customer 用户对象，其中custId不可以为空
     * @return 影响行数
     */
    Integer updateCusTomerByCustId(Customer customer);

    /**
     * @Description 按cusId查询用户
     * @param custId 用户Id
     * @return Customer用户对象
     */
    Customer findCustomerByCustId(Integer custId);

    /**
     * @Description 查询所有用户
     * @return 所有用户
     */
    List<Customer> findAll();
}



```

CustomerMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.itheima.project.mapper.CustomerMapper">

    <resultMap id="customerResultMap" type="com.itheima.project.pojo.Customer">
        <id property="custId" jdbcType="INTEGER" column="cust_id"/>
        <result property="custName" jdbcType="VARCHAR" column="cust_name"/>
        <result property="custSource" jdbcType="VARCHAR" column="cust_source"/>
        <result property="custIndustry" jdbcType="VARCHAR" column="cust_industry"/>
        <result property="custLevel" jdbcType="VARCHAR" column="cust_level"/>
        <result property="custAddress" jdbcType="VARCHAR" column="cust_address"/>
        <result property="custPhone" jdbcType="VARCHAR" column="cust_phone"/>
    </resultMap>

    <insert id="saveCustomer" parameterType="com.itheima.project.pojo.Customer">
    insert into `customer` (
	`cust_name`,
	`cust_source`,
	`cust_industry`,
	`cust_level`,
	`cust_address`,
	`cust_phone`
	)
	values
	(
	#{custName},
	#{custSource},
	#{custIndustry},
	#{custLevel},
	#{custAddress},
	#{custPhone}
	);
    </insert>

	<delete id="deleteCustomer" parameterType="integer">
		delete from `customer` where cust_id =#{custId}
	</delete>

	<update id="updateCusTomerByCustId" parameterType="com.itheima.project.pojo.Customer" >
		update `customer`
		<set>
			<if test="custName!=null">
				cust_name =#{custName},
			</if>
			<if test="custSource!=null">
				cust_source =#{custSource},
			</if>
			<if test="custIndustry!=null">
				cust_industry =#{custIndustry},
			</if>
			<if test="custLevel!=null">
				cust_level =#{custLevel},
			</if>
			<if test="custAddress!=null">
				cust_address =#{custAddress},
			</if>
			<if test="custPhone!=null">
				cust_phone =#{custPhone},
			</if>
		</set>
		where cust_id =#{custId}
	</update>

	<select id="findCustomerByCustId" resultMap="customerResultMap" parameterType="integer">
		select * from `customer` where cust_id =#{custId}
	</select>

	<select id="findAll" resultMap="customerResultMap">
		select * from `customer`
	</select>

</mapper>
```



##### 【2.4】service

用户业务接口CustomerService

```java
package com.itheima.project.service;

import com.itheima.project.pojo.Customer;

import java.util.List;

/**
 * @Description：客户服务层接口
 */
public interface CustomerService {

    /**
     * @Description 保存用户
     * @param customer 用户对象信息
     * @return 影响行数
     */
    Integer saveCustomer(Customer customer);

    /**
     * @Description 删除用户
     * @param custId 用户Id
     * @return 影响行数
     */
    Integer deleteCustomer(Integer custId);

    /**
     * @Description 修改用户
     * @param customer 用户对象，其中custId不可以为空
     * @return 影响行数
     */
    Integer updateCusTomerByCustId(Customer customer);

    /**
     * @Description 按cusId查询用户
     * @param custId 用户Id
     * @return Customer用户对象
     */
    Customer findCustomerByCustId(Integer custId);

    /**
     * @Description 查询所有用户
     * @return 所有用户
     */
    List<Customer> findAll();
}



```

用户业务实现CustomerServiceImpl

```java
package com.itheima.project.service.impl;

import com.itheima.project.mapper.CustomerMapper;
import com.itheima.project.pojo.Customer;
import com.itheima.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description：用户服务实现层
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    @Transactional
    public Integer saveCustomer(Customer customer) {
        return customerMapper.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Integer deleteCustomer(Integer custId) {
        return customerMapper.deleteCustomer(custId);
    }

    @Override
    @Transactional
    public Integer updateCusTomerByCustId(Customer customer) {
        return customerMapper.updateCusTomerByCustId(customer);
    }

    @Override
    public Customer findCustomerByCustId(Integer custId) {
        return customerMapper.findCustomerByCustId(custId);
    }

    @Override
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }
}



```

日志接口LogInfoService

```java
package com.itheima.project.service;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Description：日志服务
 */
public interface LogInfoService {

    /**
     * @Description 前置通知
     * @MethodName beforeLogInfo

     */
    void beforeLogInfo(JoinPoint joinPoint);

    /**
     * @Description 后置正常返回通知
     * @MethodName afterReturningLogInfo
     */
    void afterReturningLogInfo(JoinPoint joinPoint);

    /**
     * @Description 异常通知
     * @MethodName afterThrowingLogInfo
     */
    void afterThrowingLogInfo(JoinPoint joinPoint);

    /**
     * @Description 最终通知
     * @MethodName afterLogInfo
     */
    void afterLogInfo(JoinPoint joinPoint);

    /**
     * @Description 环绕通知
     * @MethodName aroundLogInfo
     */
    Object aroundLogInfo(ProceedingJoinPoint joinPoint);
}



```

日志实现LogInfoServiceImpl

```java
package com.itheima.project.service.impl;

import com.itheima.project.service.LogInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description：
 */
@Component
@Aspect
public class LogInfoServiceImpl implements LogInfoService {


    @Override
    @Before("execution(* com.itheima.project.service.impl.*.*(..))")
    public void beforeLogInfo(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("前置通知："+method.getName());
    }

    @Override
    @AfterReturning("execution(* com.itheima.project.service.impl.*.*(..))")
    public void afterReturningLogInfo(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("后置正常通知："+method.getName());
    }

    @Override
    @AfterThrowing("execution(* com.itheima.project.service.impl.*.*(..))")
    public void afterThrowingLogInfo(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("后置异常通知："+method.getName());
    }

    @Override
    @After("execution(* com.itheima.project.service.impl.*.*(..))")
    public void afterLogInfo(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("后置最终通知："+method.getName());
    }

    @Override
    @Around("execution(* com.itheima.project.service.impl.*.*(..))")
    public Object aroundLogInfo(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        try {
            System.out.println("环绕-前置通知："+method.getName());
            Object object = joinPoint.proceed();
            System.out.println("环绕-后置正常通知："+method.getName());
            return object;
        } catch (Throwable throwable) {
            System.out.println("环绕-后置异常通知："+method.getName());
            throw new RuntimeException(throwable);
        }finally {
            System.out.println("环绕-最终通知："+method.getName());
        }
    }
}


```

##### 【2.5】web

CustomerController

```java
package com.itheima.project.web;

import com.itheima.project.pojo.Customer;
import com.itheima.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description：
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    /**
     * @Description 查询用户列表
     */
    @RequestMapping("list")
    public String list(Model model) {
        List<Customer> list = customerService.findAll();
        model.addAttribute("list", list);
        return "list";
    }

    /**
     * @Description 去添加页面
     */
    @RequestMapping("toAdd")
    public String toAdd() {
        return "add";

    }

    /**
     * @Description 添加操作
     */
    @RequestMapping("doAdd")
    public String doAdd(Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";

    }

    /**
     * @param customerId 用户ID
     * @return
     * @Description 删除操作
     */
    @RequestMapping("doDel")
    public String doDel(Integer customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customer/list";
    }

    /**
     * @param customerId 用户ID
     * @return
     * @Description 去修改页面
     */
    @RequestMapping("toUpdate")
    public String toUpdate(Model model, Integer customerId) {
        Customer customer = customerService.findCustomerByCustId(customerId);
        model.addAttribute("customer", customer);
        return "update";
    }

    /**
     * @param customer 修改对象
     * @return
     * @Description 修改操作
     */
    @RequestMapping("doUpdate")
    public String doUpdate(Customer customer) {
        customerService.updateCusTomerByCustId(customer);
        return "redirect:/customer/list";
    }
}


```

##### 【2.6】jsp页面

add.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加用户信息</title>
    <style>
        table {border:1px solid #000000}
        table th{border:1px solid #000000}
        table td{border:1px solid #000000}
    </style>
</head>
<body>
<form id="userForm"
      action="${pageContext.request.contextPath}/customer/doAdd"  method="post" accept-charset="UTF-8">
    添加用户信息：
    <table cellpadding="0" cellspacing="0" width="80%">

        <tr>
            <td>公司名称</td>
            <td><input type="text" name="custName" value="" /></td>
        </tr>
        <tr>
            <td>信息来源</td>
            <td><input type="text" name="custSource" value="" /></td>
        </tr>
        <tr>
            <td>所属行业</td>
            <td><input type="text" name="custIndustry" value="" /></td>
        </tr>
        <tr>
            <td>级别</td>
            <td><input type="text" name="custLevel" value="" /></td>
        </tr>
        <tr>
            <td>联系地址</td>
            <td><input type="text" name="custAddress" value="" /></td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td><input type="text" name="custPhone" value="" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="添加" />
            </td>
        </tr>
    </table>

</form>
</body>

</html>
```

list.jsp

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--导入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表jsp页面</title>
    <style>
        table {border:1px solid #000000}
        table th{border:1px solid #000000}
        table td{border:1px solid #000000}
    </style>

    <script>

        // 添加用户
        function toAdd(id) {
            window.location.href="${pageContext.request.contextPath}/customer/toAdd";
        }

        // 修改用户
        function toUpdate(id) {
            window.location.href="${pageContext.request.contextPath}/customer/toUpdate?customerId="+id;
        }
        // 删除用户
        function  doDel(id) {
            if(confirm("确定要删除吗？")){
                window.location.href="${pageContext.request.contextPath}/customer/doDel?customerId="+id;
            }
        }
    </script>
</head>
<body>
    用户列表：<button type="button"  onclick="toAdd()">添加用户</button>
    <table cellpadding="0" cellspacing="0" width="80%">
        <tr>
            <th>编号</th>
            <th>公司名称</th>
            <th>信息来源</th>
            <th>所属行业</th>
            <th>级别</th>
            <th>联系地址</th>
            <th>联系电话</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="customer">
            <tr>
                <td>${customer.custId}</td>
                <td>${customer.custName}</td>
                <td>${customer.custSource}</td>
                <td>${customer.custIndustry}</td>
                <td>${customer.custLevel}</td>
                <td>${customer.custAddress}</td>
                <td>${customer.custPhone}</td>
                <td>
                    <button type="button"  onclick="toUpdate('${customer.custId}')">修改</button>
                    <button type="button"  onclick="doDel('${customer.custId}')">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>

</html>




```

update.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改用户信息</title>
    <style>
        table {border:1px solid #000000}
        table th{border:1px solid #000000}
        table td{border:1px solid #000000}
    </style>
</head>
<body>
<form id="userForm"
      action="${pageContext.request.contextPath }/customer/doUpdate"  method="post">
    修改用户信息：
    <table cellpadding="0" cellspacing="0" width="80%">
        <input type="hidden" name="custId" value="${customer.custId}"/>
        <tr>
            <td>公司名称</td>
            <td><input type="text" name="custName" value="${customer.custName}" /></td>
        </tr>
        <tr>
            <td>信息来源</td>
            <td><input type="text" name="custSource" value="${customer.custSource}" /></td>
        </tr>
        <tr>
            <td>所属行业</td>
            <td><input type="text" name="custIndustry" value="${customer.custIndustry}" /></td>
        </tr>
        <tr>
            <td>级别</td>
            <td><input type="text" name="custLevel" value="${customer.custLevel}" /></td>
        </tr>
        <tr>
            <td>联系地址</td>
            <td><input type="text" name="custAddress" value="${customer.custAddress}" /></td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td><input type="text" name="custPhone" value="${customer.custPhone}" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="修改" />
            </td>
        </tr>
    </table>

</form>
</body>

</html>
```



### 4、配置集成

#### 【1】spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
        ">
    <!--主要负责：
        1、扫描
        2、开启事务自动配置
        3、开启AOP自动配置-->
    <!--配置自动扫描service层，且只启用@Service-->

    <context:component-scan base-package="com.itheima.project">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        <context:exclude-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
    </context:component-scan>

    <!--开启事务的自动注解-->
    <tx:annotation-driven/>

    <!--开启AOP的自动注解-->
    <aop:aspectj-autoproxy/>

</beans>
```



#### 【2】spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--指定springMVC容器扫描位置-->
    <context:component-scan base-package="com.itheima.project.web"/>

    <!--开启springMVC-->
    <mvc:annotation-driven/>

    <!--配置视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```

#### 【3】spring-mybatis.xml

单独使用mybatis时：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--加载属性配置文件-->
    <properties resource="db.properties" >
    </properties>

    <!-- 配置别名 -->
    <typeAliases>
        <!-- 包扫描的方式，配置自定义别名-->
        <package name="com.itheima.pojo.pojo"/>
    </typeAliases>

    <!-- 数据源运行环境配置 -->
    <environments default="mysql">
        <environment id="mysql">
            <transactionManager type="JDBC" />
            <dataSource type="POOLED">
                <property name="driver" value="${db.driverClassName}" />
                <property name="url" value="${db.url}" />
                <property name="username" value="${db.username}" />
                <property name="password" value="${db.password}" />
            </dataSource>
        </environment>
    </environments>

    <!--加载mapper文件 -->
    <mappers>
        <package name="com.itheima.project"/>
    </mappers>

</configuration>
```

加载方式

```java
package com.itheima.project.test;

import com.itheima.ssm.dao.UserDao;
import com.itheima.ssm.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * 测试mybatis框架在web项目中独立运行
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception {
        // 1.加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis/sqlMapConfig.xml");

        // 2.读取配置文件内容，获取SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);

        // 3.打开SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.获取接口代理对象
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 5.查询全部账户列表
        List<User> list = mapper.findAllUsers();
        for(User u:list){
            System.out.println(u);
        }

        // 6.释放资源
        sqlSession.close();
    }
}

```

db.properties

```properties
dataSource.driverClassName=com.mysql.jdbc.Driver
dataSource.url=jdbc:mysql://127.0.0.1:3306/springplay
dataSource.username=root
dataSource.password=root
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
        ">

    <!--1、读取配置-->
    <context:property-placeholder location="classpath:db.properties"/>

    <!--2、配置数据源-->
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${dataSource.driverClassName}"/>
        <property name="url" value="${dataSource.url}"/>
        <property name="username" value="${dataSource.username}"/>
        <property name="password" value="${dataSource.password}"/>
    </bean>

    <!--3、配置一个事务管理器 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 注入DataSource -->
        <property name="dataSource" ref="druidDataSource"/>
    </bean>

    <!--4、会话工厂bean sqlSessionFactoryBean -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 传入 数据源 -->
        <property name="dataSource" ref="druidDataSource"/>
        <!-- 传入 pojo类所在包 -->
        <property name="typeAliasesPackage" value="com.itheima.project.pojo"/>
        <!-- 传入 mapper.xml映射文件所在路径 -->
        <property name="mapperLocations" value="classpath:sqlMapper/*Mapper.xml"/>
        <!-- 加载mybatis的全局配置文件 -->
        <property name="configuration">
            <bean class="org.apache.ibatis.session.Configuration">
                <property name="logImpl" value="org.apache.ibatis.logging.log4j2.Log4j2Impl"></property>
            </bean>
        </property>
    </bean>

    <!--5、自动扫描接口的关系映射 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 传入 sqlSessionFactory -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!-- 传入 mapper接口所在包 -->
        <property name="basePackage" value="com.itheima.project.mapper"/>
    </bean>

</beans>
```



#### 【4】web.xml

```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">

  <display-name>spring-ssm</display-name>

  <!--配置加载spring配置文件-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>
      classpath*:spring.xml
      classpath*:spring-mybatis.xml
    </param-value>
  </context-param>

  <!--配置监听器：ContextLoaderListener，说明：
    1.配置ContextLoaderListener监听器，用于监听ServletContext对象创建，一旦ServletContext对象创建，
    就创建spring的ioc容器。并且ioc容器放入ServletContext上下文中
    2.该监听器默认只能加载WEB-IFN目录下，名称叫做applicationContext.xml配置文件
    3.通过<context-param>全局参数标签，指定加载spring配置文件的位置
  -->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!--配置前端控制器：DispatcherServlet-->
  <servlet>
    <servlet-name>springServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

    <!--加载主配置文件springmvc.xml-->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath*:spring-mvc.xml</param-value>
    </init-param>

    <!--配置什么时候加载前端控制器，说明：
      1.配置大于等于0的整数，表示在tomcat启动的时候，就加载前端控制器
      2.配置小于0的整数，表示在第一次请求到达的时候加载前端控制器
    -->
    <load-on-startup>1</load-on-startup>

  </servlet>

  <servlet-mapping>
    <servlet-name>springServlet</servlet-name>
    <!--配置请求的路径规则，说明：
      1.*.do，表示以.do结尾的请求进入前端控制器
      2./，表示所有请求都进入前端控制器
    -->
    <url-pattern>/</url-pattern>
  </servlet-mapping>

  <!--配置字符集编码过滤器：CharacterEncodingFilter-->
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!--指定使用的字符集编码-->
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <!--配置所有请求都经过字符集编码过滤器处理-->
    <url-pattern>/*</url-pattern>
  </filter-mapping>

</web-app>

```



#### 【5】log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 设置log4j2的自身log级别为warn -->
<!-- OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
<Configuration status="DEBUG">
    <Appenders>
        <!-- 控制台输出 -->
        <Console name="Console" target="SYSTEM_OUT">
            <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <ThresholdFilter level="DEBUG" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>

        <RollingFile name="RollingFileDebug" fileName="D:/logs/debug.log"
                     filePattern="D:/logs/$${date:yyyy-MM}/debug-%d{yyyy-MM-dd}-%i.log">
            <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <Filters>
                <ThresholdFilter level="DEBUG"/>
                <ThresholdFilter level="INFO" onMatch="DENY" onMismatch="NEUTRAL"/>
            </Filters>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="100 MB"/>
            </Policies>
            <!--最多保留20个日志文件-->
            <DefaultRolloverStrategy max="20" min="0" />
        </RollingFile>

        <RollingFile name="RollingFileInfo" fileName="D:/logs/info.log"
                     filePattern="D:/logs/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">
            <Filters>
                <ThresholdFilter level="INFO"/>
                <ThresholdFilter level="WARN" onMatch="DENY" onMismatch="NEUTRAL"/>
            </Filters>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="100 MB"/>
            </Policies>
            <!--最多保留20个日志文件-->
            <DefaultRolloverStrategy max="20" min="0" />
        </RollingFile>

        <RollingFile name="RollingFileWarn" fileName="D:/logs/warn.log"
                     filePattern="D:/logs/$${date:yyyy-MM}/warn-%d{yyyy-MM-dd}-%i.log">
            <Filters>
                <ThresholdFilter level="WARN"/>
                <ThresholdFilter level="ERROR" onMatch="DENY" onMismatch="NEUTRAL"/>
            </Filters>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="100 MB"/>
            </Policies>
            <!--最多保留20个日志文件-->
            <DefaultRolloverStrategy max="20" min="0" />
        </RollingFile>

        <RollingFile name="RollingFileError" fileName="D:/logs/error.log"
                     filePattern="D:/logs/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">
            <ThresholdFilter level="ERROR"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="100 MB"/>
            </Policies>
            <!--最多保留20个日志文件-->
            <DefaultRolloverStrategy max="20" min="0" />
        </RollingFile>

        <RollingFile name="RollingFileFatal" fileName="D:/logs/fatal.log"
                     filePattern="D:/logs/$${date:yyyy-MM}/fatal-%d{yyyy-MM-dd}-%i.log">
            <ThresholdFilter level="FATAL"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="100 MB"/>
            </Policies>
            <!--最多保留20个日志文件-->
            <DefaultRolloverStrategy max="20" min="0" />
        </RollingFile>
    </Appenders>
    <Loggers>
        <!-- 将业务dao接口填写进去,并用控制台输出即可 -->
        <logger name="com.itheima.project.mapper" level="DEBUG" additivity="false">
            <appender-ref ref="Console"/>
        </logger>

        <logger name="com.itheima.project.service" level="DEBUG" additivity="false">
            <appender-ref ref="Console"/>
        </logger>

        <Root level="all">
            <appender-ref ref="Console"/>
            <appender-ref ref="RollingFileDebug"/>
            <appender-ref ref="RollingFileInfo"/>
            <appender-ref ref="RollingFileWarn"/>
            <appender-ref ref="RollingFileError"/>
            <appender-ref ref="RollingFileFatal"/>
        </Root>
    </Loggers>
</Configuration>
```

### 5、测试及小结

访问 http://localhost:8080/platform/customer/list 

![image-20200103172511099](image\image-20200103172511099.png)

```properties
#整合层框架：
	spring：
		spring.xml
		扫描com.itheima.project包
		声明开启事务
		声明开启AOP
	
#表现层框架：
	springmvc：
		spring-mvc.xml
        配置包扫描controller
        配置处理器映射器和处理器适配器
        配置视图解析器
				
#持久层框架：
	mybatis：
		spring-mybatis.xml
		读取配置
        创建数据源
        创建事务管理器
        创建sqlsessionfactory
        创建扫描mapper类位置
#公共的配置文件：
	web.xml:
		配置监听器：ContextLoaderListner
		配置字符集编码过滤器：CharacterEncodingFilter
		配置前端控制器：DispatcherServlet
	db.properties
		配置连接数据库的四个要素
	log4j2.xml
		配置log4j2
```

