# Mybatis-plus前言：

mybatis在持久层框架中还是比较火的，一般项目都是基于ssm。

虽然mybatis可以直接在xml中通过SQL语句操作数据库，很是灵活。但正其操作都要通过SQL语句进行，就必须写大量的xml文件，很是麻烦。**mybatis-plus就很好的解决了这个问题**。

# 一、mybatis-plus简介：

Mybatis-Plus（简称MP）是一个 Mybatis 的增强工具，在 Mybatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

这是官方给的定义，关于mybatis-plus的更多介绍及特性，可以参考[mybatis-plus官网](http://mp.baomidou.com/#/)。

那么它是怎么增强的呢？其实就是**它已经封装好了一些crud方法，我们不需要再写xml了**，直接调用这些方法就行，就**类似于JPA**。

**先聊聊 什么是	ORM**

- ORM映射思想说明



- Java对象操作表



# 二、springboot整合mybatis-plus

正如官方所说，mybatis-plus在mybatis的基础上只做增强不做改变，因此其与spring的整合亦非常简单。

只需把mybatis的依赖换成mybatis-plus的依赖，再把sqlSessionFactory换成mybatis-plus的即可。

接下来看具体操作：
 **1、pom.xml:**
 核心依赖如下：

```xml
 <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

 <dependencies>
<dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.2</version>
        </dependency>
        <!--mp依赖包-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-extension</artifactId>
            <version>3.3.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
      </dependencies>
   <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

**注意：**这些是核心依赖，本项目还用到了mysql驱动、c3p0、日志（slf4j-api，slf4j-log4j2）、lombok。

集成mybatis-plus要把mybatis、mybatis-spring去掉，避免冲突；lombok是一个工具，添加了这个依赖，开发工具再安装Lombok插件，就可以使用它了，最常用的用法就是在实体类中使用它的@Data注解，这样实体类就不用写set、get、toString等方法了。

**2、yaml文件配置**

```properties
spring:
  # 数据源的配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/heimaxxx?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    username: root
    password: root

mybatis-plus:
  type-aliases-package: cn.itcast.xxx.pojo  # 实体类包路径
  configuration:
    #   sql日志显示，这里使用标准显示
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    #  数据库中如果有类似 如  user_name 等命名，会将 _后的字母大写，这里是为了和实体类对应
    map-underscore-to-camel-case: true
```



**3、entity+sql**

```kotlin
@Data
@TableName(value = "tb_employee")//指定表名
public class Employee {
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer id;
    //若没有开启驼峰命名，或者表中列名不符合驼峰规则，可通过该注解指定数据库表中的列名，exist标明数据表中有没有对应列
    @TableField(value = "last_name",exist = true)
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;
}


```

```mysql
/*
Navicat MySQL Data Transfer

Source Server         : heima_health
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : heima08

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2020-08-13 15:42:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_employee
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_employee
-- ----------------------------
INSERT INTO `tb_employee` VALUES ('2', '郭富城', '男性', 'tps520@163.com', '48', null);
INSERT INTO `tb_employee` VALUES ('3', 'tom', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('4', 'marry', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('5', 'jerry', null, null, null, null);

```

**4、mapper:**

```java
public interface EmplopyeeMapper extends BaseMapper<Employee> {
}
```

这样就完成了mybatis-plus与spring的整合。首先是把mybatis和mybatis-spring依赖换成mybatis-plus的依赖，然后把sqlsessionfactory换成mybatis-plus的，然后实体类中添加`@TableName`、`@TableId`等注解，最后mapper继承`BaseMapper`即可。

**5、测试：**

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class BankApplicationTests {
    
        @Autowired
    private  EmplopyeeMapper EmplopyeeMapper;
    
}
```

# 三、mp的通用crud:

**需求：**
 存在一张 tb_employee 表，且已有对应的实体类 Employee，实现tb_employee 表的 CRUD 操作我们需要做什么呢？
 **基于 Mybatis：**
 需要编写 EmployeeMapper 接口，并在 EmployeeMapper.xml 映射文件中手动编写 CRUD 方法对应的sql语句。
 **基于 MP：**
 只需要创建 EmployeeMapper 接口, 并继承 BaseMapper 接口。
 我们已经有了Employee、tb_employee了，并且EmployeeDao也继承了BaseMapper了，接下来就使用crud方法。

**1、insert操作：**

```java

    @Autowired
    private EmplopyeeDao emplopyeeDao;
    @Test
    public void testInsert(){
        Employee employee = new Employee();
        employee.setLastName("东方不败");
        employee.setEmail("dfbb@163.com");
        employee.setGender(1);
        employee.setAge(20);
        emplopyeeDao.insert(employee);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(employee.getId());
    }

```

执行添加操作，直接调用insert方法传入实体即可。

**2、update操作：**

```java
@Test
public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastName("更新测试");
        //emplopyeeDao.updateById(employee);//根据id进行更新，没有传值的属性就不会更新
        emplopyeeDao.updateAllColumnById(employee);//根据id进行更新，没传值的属性就更新为null
}
```

**注：**注意这两个update操作的区别，`updateById`方法，没有传值的字段不会进行更新，比如只传入了lastName，那么age、gender等属性就会保留原来的值；`updateAllColumnById`方法，顾名思义，会更新所有的列，没有传值的列会更新为null。

**3、select操作：**

**(1)、**根据id查询：

```undefined
Employee employee = emplopyeeDao.selectById(1);
```

**(2)、**根据条件查询一条数据：

```cpp
Employee employeeCondition = new Employee();
employeeCondition.setId(1);
employeeCondition.setLastName("更新测试");
//若是数据库中符合传入的条件的记录有多条，那就不能用这个方法，会报错
Employee employee = emplopyeeDao.selectOne(employeeCondition);
```

**注：**这个方法的sql语句就是`where id = 1 and last_name = 更新测试`，若是符合这个条件的记录不止一条，那么就会报错。

**(3)、**根据查询条件返回多条数据：
 当符合指定条件的记录数有多条时，上面那个方法就会报错，就应该用这个方法。

```dart
Map<String,Object> columnMap = new HashMap<>();
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
List<Employee> employees = emplopyeeDao.selectByMap(columnMap);
System.out.println(employees.size());
```

**注：**查询条件用map集合封装，columnMap，写的是数据表中的列名，而非实体类的属性名。比如属性名为lastName，数据表中字段为last_name，这里应该写的是last_name。selectByMap方法返回值用list集合接收。

**(4)、**通过id批量查询：

**注：**把需要查询的id都add到list集合中，然后调用selectBatchIds方法，传入该list集合即可，该方法返回的是对应id的所有记录，所有返回值也是用list接收。

**(5)、分页查询**

```csharp
List<Employee> employees = emplopyeeDao.selectPage(new Page<Employee>(1,2),null);
System.out.println(employees);
```

**注：**selectPage方法就是分页查询，在**page中传入分页信息，后者为null的分页条件**，这里先让其为null，讲了条件构造器再说其用法。

这个分页**其实并不是物理分页，而是查询表中所有数据。也就是说，实际查询的时候并没有limit语句**。

需要自己编写一个 配置类  ： 把分页插件配置一下即可

**确保springboot的启动类要可以扫描到该配置类即可**：

```java
@EnableTransactionManagement
@Configuration
@MapperScan("com.itheima.mapper") //  mapper接口包
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        System.out.println("===============mybaitsplus==========pagination===============");
        return new PaginationInterceptor();
    }

}
```

**4、delete操作：**

**(1)、**根据id删除：

```css
emplopyeeDao.deleteById(1);
```

**(2)、**根据条件删除：

```dart
Map<String,Object> columnMap = new HashMap<>();
columnMap.put("gender",0);
columnMap.put("age",18);
emplopyeeDao.deleteByMap(columnMap);
```

**注：**该方法与selectByMap类似，将条件封装在columnMap中，然后调用deleteByMap方法，传入columnMap即可，返回值是Integer类型，表示影响的行数。

**(3)、**根据id批量删除：

```csharp
 List<Integer> idList = new ArrayList<>();
 idList.add(1);
 idList.add(2);
 emplopyeeDao.deleteBatchIds(idList);
```

**注：**该方法和selectBatchIds类似，把需要删除的记录的id装进idList，然后调用deleteBatchIds，传入idList即可。

通过上面的小案例我们可以发现，实体类需要加@TableName注解指定数据库表名，通过@TableId注解指定id的增长策略。实体类少倒也无所谓，实体类一多的话也麻烦。所以可以在spring-dao.xml的文件中进行全局策略配置。

```xml
<!-- 5、mybatisplus的全局策略配置 -->
<bean id="globalConfiguration" class="com.baomidou.mybatisplus.entity.GlobalConfiguration">
        <!-- 2.3版本后，驼峰命名默认值就是true，所以可不配置 -->
        <!--<property name="dbColumnUnderline" value="true"/>-->
        <!-- 全局主键自增策略，0表示auto -->
        <property name="idType" value="0"/>
        <!-- 全局表前缀配置 -->
        <property name="tablePrefix" value="tb_"/>
</bean>
```



这里配置了还没用，还需要在sqlSessionFactory中注入配置才会生效。如下：

```xml
<!-- 3、配置mybatisplus的sqlSessionFactory -->
<bean id="sqlSessionFactory" class="com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="typeAliasesPackage" value="com.zhu.mybatisplus.entity"/>
        <!-- 注入全局配置 -->
        <property name="globalConfig" ref="globalConfiguration"/>
</bean>
```

如此一来，实体类中的@TableName注解和@TableId注解就可以去掉了。

# 四、条件构造器(EntityWrapper)：

以上基本的 CRUD 操作，我们仅仅需要继承一个 BaseMapper 即可实现大部分单表 CRUD 操作。BaseMapper 提供了多达 17 个方法供使用, 可以极其方便的实现单一、批量、分页等操作，极大的减少开发负担。但是mybatis-plus的强大不限于此，请看如下需求该如何处理：
 **需求：**
 我们需要分页查询 tb_employee 表中，年龄在 18~50 之间性别为男且姓名为 xx 的所有用户，这时候我们该如何实现上述需求呢？
 **使用MyBatis :** 需要在 SQL 映射文件中编写带条件查询的 SQL,并用PageHelper 插件完成分页. 实现以上一个简单的需求，往往需要我们做很多重复单调的工作。
 **使用MP:** 依旧不用编写 SQL 语句，MP 提供了功能强大的条件构造器 ------  EntityWrapper。

**接下来就直接看几个案例体会EntityWrapper的使用。**

**1、分页查询年龄在18 - 50且gender为0、姓名为tom的用户：**

```dart
List<Employee> employees = emplopyeeDao.selectPage(new Page<Employee>(1,3),
     new EntityWrapper<Employee>()
        .between("age",18,50)
        .eq("gender",0)
        .eq("last_name","tom")
);
```

**注：**由此案例可知，分页查询和之前一样，new 一个page对象传入分页信息即可。至于分页条件，new 一个EntityWrapper对象，调用该对象的相关方法即可。between方法三个参数，分别是column、value1、value2，该方法表示column的值要在value1和value2之间；eq是equals的简写，该方法两个参数，column和value，表示column的值和value要相等。注意column是数据表对应的字段，而非实体类属性字段。

**2、查询gender为0且名字中带有老师、或者邮箱中带有a的用户：**

```dart
List<Employee> employees = emplopyeeDao.selectList(
                new EntityWrapper<Employee>()
               .eq("gender",0)
               .like("last_name","老师")
                //.or()//和or new 区别不大
               .orNew()
               .like("email","a")
);
```

**注：**未说分页查询，所以用selectList即可，用EntityWrapper的like方法进行模糊查询，like方法就是指column的值包含value值，此处like方法就是查询last_name中包含“老师”字样的记录；“或者”用or或者orNew方法表示，这两个方法区别不大，用哪个都可以，可以通过控制台的sql语句自行感受其区别。

**3、查询gender为0，根据age排序，简单分页：**

```dart
List<Employee> employees = emplopyeeDao.selectList(
                new EntityWrapper<Employee>()
                .eq("gender",0)
                .orderBy("age")//直接orderby 是升序，asc
                .last("desc limit 1,3")//在sql语句后面追加last里面的内容(改为降序，同时分页)
);
```

**注：**简单分页是指不用page对象进行分页。orderBy方法就是根据传入的column进行升序排序，若要降序，可以使用orderByDesc方法，也可以如案例中所示用last方法；last方法就是将last方法里面的value值追加到sql语句的后面，在该案例中，最后的sql语句就变为`select ······ order by desc limit 1, 3`，追加了`desc limit 1,3`所以可以进行降序排序和分页。

**4、分页查询年龄在18 - 50且gender为0、姓名为tom的用户：**
 条件构造器除了EntityWrapper，还有Condition。用Condition来处理一下这个需求：

```dart
 List<Employee> employees = emplopyeeDao.selectPage(
                new Page<Employee>(1,2),
                Condition.create()
                        .between("age",18,50)
                        .eq("gender","0")
 );
```

**注：**Condition和EntityWrapper的区别就是，创建条件构造器时，EntityWrapper是new出来的，而Condition是调create方法创建出来。

**5、根据条件更新：**

```java
@Test
public void testEntityWrapperUpdate(){
        Employee employee = new Employee();
        employee.setLastName("苍老师");
        employee.setEmail("cjk@sina.com");
        employee.setGender(0);
        emplopyeeDao.update(employee,
                new EntityWrapper<Employee>()
                .eq("last_name","tom")
                .eq("age",25)
        );
}
```

**注：**该案例表示把last_name为tom，age为25的所有用户的信息更新为employee中设置的信息。

**6、根据条件删除：**

```cpp
emplopyeeDao.delete(
        new EntityWrapper<Employee>()
        .eq("last_name","tom")
        .eq("age",16)
);
```

**注：**该案例表示把last_name为tom、age为16的所有用户删除。


# 总结：

上述mybatis-plus介绍了其

- - mybatis-plus与spring整合
- - 通用crud的使用
  - 全局策略的配置
  - 条件构造器的使用











