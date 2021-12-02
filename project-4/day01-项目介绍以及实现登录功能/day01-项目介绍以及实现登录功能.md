## 课程介绍  《探花交友》

- 功能介绍
- 项目介绍
- 工程搭建
- 短信验证码
- 实现用户登录功能

## 1、功能介绍

探花交友是一个陌生人的在线交友平台，在该平台中可以搜索附近的人，查看好友动态，平台还会通过大数据计算进行智能推荐，通过智能推荐可以找到更加匹配的好友，这样才能增进用户对产品的喜爱度。探花平台还提供了在线即时通讯功能，可以实时的与好友进行沟通，让沟通随时随地的进行。  

### 1.1、功能列表

| 功能       | 说明                                           | 备注                                           |
| ---------- | ---------------------------------------------- | ---------------------------------------------- |
| 注册、登录 | 用户无需单独注册，直接通过手机号登录即可       | 首次登录成功后需要完善个人信息                 |
| 交友       | 主要功能有：测灵魂、桃花传音、搜附近、探花等   |                                                |
| 圈子       | 类似微信朋友圈，用户可以发动态、查看好友动态等 |                                                |
| 消息       | 通知类消息 + 即时通讯消息                      |                                                |
| 小视频     | 类似抖音，用户可以发小视频，评论等             | 显示小视频列表需要进行推荐算法计算后进行展现。 |
| 我的       | 我的动态、关注数、粉丝数、通用设置等           |                                                |

### 1.2、注册登录

业务说明：

用户通过手机验证码进行登录，如果是第一次登录则需要完善个人信息，在上传图片时，需要对上传的图片做人像的校验，防止用户上传非人像的图片作为头像。流程完成后，则登录成功。

 ![1563681330146](assets/1563681330146.png)

 ![1563681348382](assets/1563681348382.png)

 ![1563681365123](assets/1563681365123.png)

 ![1563681382592](assets/1563681382592.png)

 ![1563681397466](assets/1563681397466.png)

 ![1563681412881](assets/1563681412881.png)

### 1.3、交友

交友是探花项目的核心功能之一，用户可以查看好友，添加好友，搜索好友等操作。

 ![1563698504256](assets/1563698504256.png)

#### 1.3.1、首页

在首页中，主要功能有“今日佳人”、“推荐”、“最近访客”等

- 今日佳人
  - 按照“缘分值”进行匹配，将“缘分值”最高的用户展现出来
- 推荐
  - 按照“缘分值”进行推荐，由后台的推荐系统计算得出，展现出来
- 最近访客
  - 显示最近来看“我”的用户

#### 1.3.2、探花

 ![1563714212568](assets/1563714212568.png)

> 说明：左划喜欢，右划不喜欢，每天限量不超过100个，开通会员可增加限额。双方互相喜欢则配对成功。
>
> 实现：数据来源推荐系统计算后的结果。

#### 1.3.3、搜附近

 ![1563714826374](assets/1563714826374.png)

根据用户当前所在的位置进行查询，并且在10km的范围内进行查询，可以通过筛选按钮进行条件筛选。

#### 1.3.4、桃花传音

功能类似QQ中的漂流瓶，用户可以发送和接收语音消息，陌生人就会接收到消息。

 ![1563718868686](assets/1563718868686.png)

#### 1.3.5、测灵魂

1. 测试题用于对用户进行分类，每次提交答案后更新用户属性
2. 测试题在后台进行维护
3. 测试题测试完后产生结果页可以进行分享

4. 测试题为顺序回答，回答完初级题解锁下一级问题

5. 点击锁定问题 显示提示 请先回答上一级问题

 ![1563719052277](assets/1563719052277.png)

 ![1563719092059](assets/1563719092059.png)

 ![1563719100855](assets/1563719100855.png)

### 1.4、圈子

1、推荐频道为根据问卷及喜好推荐相似用户动态

2、显示内容为用户头像、用户昵称、用户性别、用户年龄、用户标签和用户发布动态

3、图片最多不超过6张或发布一个小视频

4、动态下方显示发布时间距离当时时间，例如10分钟前、3小时前、2天前，显示时间进行取整

5、动态下方显示距离为发布动态地与本地距离

6、显示用户浏览量

7、显示点赞数、评论数 转发数

 ![1563719380223](assets/1563719380223.png)

 ![1563719403171](assets/1563719403171.png)

### 1.5、消息

消息包含通知类的消息和好友消息。

 ![1563719621939](assets/1563719621939.png)

### 1.6、小视频

用户可以上传小视频，也可以查看小视频列表，并且可以进行点赞操作。

 ![1563719761162](assets/1563719761162.png)

### 1.7、我的

显示关注数、喜欢数、粉丝数、我的动态等信息。

 ![1563719894481](assets/1563719894481.png)

 ![1563719949700](assets/1563719949700.png)

## 2、项目介绍

### 2.1、项目背景

在线社交是互联网时代的产物，已成为互联网用户的基础需求之一。移动互联网自2003年起快速发展，促使在线社交逐渐从PC端转移至移动端。移动社交最初以熟人社交为主，以维系熟人关系、共享资源信息的形式存在。随着人们交友需求的延伸，移动社交开始向陌生人社交、兴趣社交等垂直方向发展，形式丰富多样。

### 2.2、市场分析

探花交友项目定位于 **陌生人交友市场**。

- 根据《2018社交领域投融资报告》中指出：虽然相比2017年，投融资事件减少29.5%，但是融资的总额却大幅增长，达到68%。
- 这些迹象说明：社交领域的发展规模正在扩大，而很多没有特色的产品也会被淘汰。而随着那些尾部产品的倒下，对我们来说就是机会，及时抓住不同社交需求的机会。以社交为核心向不同的细分领域衍生正在逐渐走向成熟化。
- 而我们按照娱乐形式和内容为主两个维度，将社交行业公司分类为：即时通信、内容社群、陌生人社交、泛娱乐社交以及兴趣社交几个领域。
- 而在2018年社交的各个细分领域下，均有备受资本所关注的项目，根据烯牛数据2018年的报告中，也同样指出：内容社交及陌生人社交为资本重要关注领域，合计融资占比达73%。

 ![img](assets/clip_image002.jpg)

根据市场现状以及融资事件来看：陌生人社交、内容社群、兴趣社交在2019年仍然保持强劲的动力，占到近70%的比例，它们仍然是资本市场主要关注领域。从增长率来看陌生人社交的增长速度远远大于其他几类，因此我们要从这个方向入手。

### 2.3、目标用户群体

从整体年龄段来看：目前目标用户群体主要以30岁以下为主，其中以18-25岁年龄群体为主要受众人群。

- **上班群体：**热衷于通过分享内容或表达“个人情绪”在陌生人面前建立特殊的人设，并借此提升自我价值扩大自己的交际圈；
- **学生群体：**追求个性选择，更倾向找到有共同话题的陌生人对象并建立长期的关系，乐于展现自我；
- **文艺群体：**拥有自己独特的爱好且拥有特别的个人追求，追求文艺圈子内的交流，希望通过分享结交更多好友；
- **沟通弱势群体：**对现有长期保持线上对社交模式表现无力且无效，渴望有更加有效且安全的社交方式出现，解决目前单调乏味的沟通方式；

### 2.4、技术方案

前端：

- flutter + android + 环信SDK + redux + shared_preferences + connectivity + iconfont + webview + sqflite

后端：

- Spring Boot + SpringMVC + Mybatis + MybatisPlus + Dubbo
- MongoDB geo 实现地理位置查询
- MongoDB 实现海量数据的存储
- Redis 数据的缓存
- Spark + MLlib 实现智能推荐
- 第三方服务 环信即时通讯 
- 第三方服务 阿里云 OSS 、 短信服务、人脸识别

### 2.5、技术架构 

 ![1563457093887](assets/1563457093887.png)

### 2.6、技术解决方案

- 使用MongoDB geo实现附近的人的解决方案
- 使用Spark + Mllib实现智能推荐的解决方案
- 使用MongoDB进行海量数据的存储的解决方案
- 使用采用分布式文件系统存储小视频数据的解决方案
- 使用阿里云平台进行人脸识别的解决方案
- 使用阿里云进行短信验证码发送的解决方案

### 2.7、技术亮点

- 采用RocketMQ作为消息服务中间件
- 采用MongoDB进行海量数据的存储
- 采用Spark + Mllib实现智能推荐
- 采用环信服务实现即时通讯
- 采用分布式文件系统存储小视频数据
- 采用Apache Dobbo作为微服务架构技术
- 采用SpringBoot + Mybatis实现系统主架构
- 采用Redis集群实现缓存的高可用

### 2.8、开发方式

探花交友项目采用前后端分离的方式开发，就是前端由前端团队负责开发，后端负责接口的开发：

![image-20210329095435756](assets/image-20210329095435756.png)

什么是接口？接口就是一个http的请求地址，在定义接口的时候主要就是去定义：请求路径，请求方式，请求参数，响应结果数据等内容。

对于接口的定义我们采用YApi进行管理，YApi是一个开源的接口定义、管理、提供mock数据的管理平台。

> 地址：https://mock-java.itheima.net/
>
> 用户名：tanhua@itcast.cn
>
> 密码：123456

![image-20201017100705286](assets/image-20201017100705286.png)

接口定义：

![image-20201017100848313](assets/image-20201017100848313.png)

mock数据，YApi提供了mock功能，就是模拟服务端返回测试数据：

 ![image-20201017101228582](assets/image-20201017101228582.png)

 ![image-20201017101252482](assets/image-20201017101252482.png)

还可以运行http请求（需要在Chrome中安装支持跨域扩展 https://juejin.im/post/6844904057707085832）：

![image-20201017103028622](assets/image-20201017103028622.png)

![image-20201017103045864](assets/image-20201017103045864.png)

### 2.9、基础环境

探花交友项目的开发统一使用提供的Centos7环境，该环境中部署安装了项目所需要的各种服务，如：MySQL、MongoDB、Redis、RocketMQ等。

- 虚拟机的root用户密码为：root123
- 默认参数：CPU：2核，内存：4G，硬盘：60G
- 必须将IP地址设置为192.168.31.81，否则有些服务将不可用，比如：Redis、RocketMQ等。

## 3、注册登录

### 3.1、流程说明

#### 3.1.1、整体认证流程

![image-20210329110253421](assets/image-20210329110253421.png)

流程说明：

- 用户根据手机号与验证码在sso系统（单点登录系统）进行登录，生成token（符合JWT规范）
- 将token响应给客户端，客户端将token存储到本地，后续的业务请求将携带token
- 客户端请求server（接口服务）时携带token，server系统对于token进行校验
- 如果校验失败，提醒用户重新登录，如果校验成功，视为已登录，可以完成后续的操作

#### 3.1.2、登录业务流程

业务说明：

用户通过手机验证码进行登录，如果是第一次登录则需要完善个人信息，在上传图片时，需要对上传的图片做人像的校验，防止用户上传非人像的图片作为头像。流程完成后，则登录成功。

![image-20210329101222241](assets/image-20210329101222241.png)

### 3.2、搭建工程

工程如下：

 ![image-20210329112333942](assets/image-20210329112333942.png)

.gitignore文件如下：

~~~shell
.idea
target/
*.iml
~~~

#### 3.2.1、my-tanhua

my-tanhua是父工程，集中定义了依赖的版本以及所需要的依赖信息。

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <modules>
        <module>my-tanhua-common</module>
        <module>my-tanhua-sso</module>
        <module>my-tanhua-dubbo</module>
    </modules>

    <!--通过继承的方式集成SpringBoot-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.4.RELEASE</version>
    </parent>

    <groupId>cn.itcast</groupId>
    <artifactId>my-tanhua</artifactId>
    <version>1.0-SNAPSHOT</version>

    <packaging>pom</packaging>

    <!-- 集中定义依赖版本号 -->
    <properties>
        <mysql.version>5.1.47</mysql.version>
        <jackson.version>2.11.2</jackson.version>
        <druid.version>1.0.9</druid.version>
        <mongo.version>4.0.3</mongo.version>
        <servlet-api.version>2.5</servlet-api.version>
        <jsp-api.version>2.0</jsp-api.version>
        <joda-time.version>2.9.9</joda-time.version>
        <commons-lang3.version>3.7</commons-lang3.version>
        <commons-io.version>1.3.2</commons-io.version>
        <mybatis.version>3.2.8</mybatis.version>
        <mybatis.mybatis-plus>3.1.1</mybatis.mybatis-plus>
        <lombok.version>1.18.4</lombok.version>
        <spring-rocketmq.version>2.0.3</spring-rocketmq.version>
        <rocketmq.version>4.6.0</rocketmq.version>
        <commons-codec.version>1.11</commons-codec.version>
        <dubbo.version>2.7.9</dubbo.version>
        <nacos.version>1.1.1</nacos.version>
        <nacos-config.version>0.2.3</nacos-config.version>
        <netty.version>4.1.32.Final</netty.version>
        <aliyun-sdk-oss.version>3.10.2</aliyun-sdk-oss.version>
        <aliyun-sdk-sms.version>2.0.1</aliyun-sdk-sms.version>
        <aliyun-sdk-core.version>4.5.14</aliyun-sdk-core.version>
        <aliyun-sdk-facebody.version>1.2.14</aliyun-sdk-facebody.version>
        <fastdfs.version>1.26.7</fastdfs.version>
        <jjwt.version>0.9.1</jjwt.version>
    </properties>

    <!--通用依赖,所有的子工程都会引入这些依赖-->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <!--Hutool是一个小而全的Java工具类库，https://www.hutool.cn/docs/#/-->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.5.2</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
            <version>${lombok.version}</version>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>

            <!-- mybatis-plus插件依赖 -->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus</artifactId>
                <version>${mybatis.mybatis-plus}</version>
            </dependency>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis.mybatis-plus}</version>
            </dependency>
            <!-- MySql -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>

            <!--MongoDB驱动依赖-->
            <dependency>
                <groupId>org.mongodb</groupId>
                <artifactId>mongodb-driver-sync</artifactId>
                <version>${mongo.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>${commons-lang3.version}</version>
            </dependency>
            <!--RocketMQ相关-->
            <dependency>
                <groupId>org.apache.rocketmq</groupId>
                <artifactId>rocketmq-spring-boot-starter</artifactId>
                <version>${spring-rocketmq.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.rocketmq</groupId>
                <artifactId>rocketmq-client</artifactId>
                <version>${rocketmq.version}</version>
            </dependency>
            <!-- Jackson Json处理工具包 -->
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>${jackson.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>commons-codec</groupId>
                <artifactId>commons-codec</artifactId>
                <version>${commons-codec.version}</version>
            </dependency>
            <dependency>
                <groupId>joda-time</groupId>
                <artifactId>joda-time</artifactId>
                <version>${joda-time.version}</version>
            </dependency>
            <dependency>
                <groupId>io.netty</groupId>
                <artifactId>netty-all</artifactId>
                <version>${netty.version}</version>
            </dependency>
            <!--dubbo的springboot支持-->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-spring-boot-starter</artifactId>
                <version>${dubbo.version}</version>
            </dependency>
            <!--dubbo框架-->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo</artifactId>
                <version>${dubbo.version}</version>
            </dependency>
            <!--nacos客户端-->
            <dependency>
                <groupId>com.alibaba.nacos</groupId>
                <artifactId>nacos-client</artifactId>
                <version>${nacos.version}</version>
            </dependency>
            <!-- nacos config依赖 -->
            <dependency>
                <groupId>com.alibaba.boot</groupId>
                <artifactId>nacos-config-spring-boot-starter</artifactId>
                <version>${nacos-config.version}</version>
            </dependency>
            <!-- 阿里云OSS服务依赖-->
            <dependency>
                <groupId>com.aliyun.oss</groupId>
                <artifactId>aliyun-sdk-oss</artifactId>
                <version>${aliyun-sdk-oss.version}</version>
            </dependency>
            <!-- 阿里云短信服务依赖-->
            <dependency>
                <groupId>com.aliyun</groupId>
                <artifactId>dysmsapi20170525</artifactId>
                <version>${aliyun-sdk-sms.version}</version>
            </dependency>
            <!--阿里云核心sdk-->
            <dependency>
                <groupId>com.aliyun</groupId>
                <artifactId>aliyun-java-sdk-core</artifactId>
                <version>${aliyun-sdk-core.version}</version>
            </dependency>

            <!--阿里云人脸人体检测SDK-->
            <dependency>
                <groupId>com.aliyun</groupId>
                <artifactId>aliyun-java-sdk-facebody</artifactId>
                <version>${aliyun-sdk-facebody.version}</version>
            </dependency>
            <dependency>
                <groupId>com.github.tobato</groupId>
                <artifactId>fastdfs-client</artifactId>
                <version>${fastdfs.version}</version>
                <exclusions>
                    <exclusion>
                        <groupId>ch.qos.logback</groupId>
                        <artifactId>logback-classic</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <!-- JWT依赖-->
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt</artifactId>
                <version>${jjwt.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <!-- java编译插件 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
~~~

#### 3.2.2、my-tanhua-common

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>my-tanhua</artifactId>
        <groupId>cn.itcast</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>my-tanhua-common</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- 阿里云OSS服务依赖-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>nacos-config-spring-boot-starter</artifactId>
        </dependency>
    </dependencies>

</project>
~~~

#### 3.2.3、my-tanhua-sso

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>my-tanhua</artifactId>
        <groupId>cn.itcast</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>my-tanhua-sso</artifactId>

    <dependencies>
        <dependency>
            <groupId>cn.itcast.dubbo</groupId>
            <artifactId>my-tanhua-dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>cn.itcast</groupId>
            <artifactId>my-tanhua-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>dysmsapi20170525</artifactId>
        </dependency>
        <!-- 阿里云OSS服务依赖-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
        </dependency>
        <!--阿里云核心sdk-->
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>aliyun-java-sdk-core</artifactId>
        </dependency>

        <!--阿里云人脸人体检测SDK-->
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>aliyun-java-sdk-facebody</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>io.lettuce</groupId>
                    <artifactId>lettuce-core</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.rocketmq</groupId>
            <artifactId>rocketmq-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.rocketmq</groupId>
            <artifactId>rocketmq-client</artifactId>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>nacos-config-spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
~~~

#### 3.2.4、my-tanhua-dubbo

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>my-tanhua</artifactId>
        <groupId>cn.itcast</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.itcast.dubbo</groupId>
    <artifactId>my-tanhua-dubbo</artifactId>
    <packaging>pom</packaging>
    <modules>
        <module>my-tanhua-dubbo-interface</module>
        <module>my-tanhua-dubbo-service</module>
    </modules>

    <dependencies>
        <!--dubbo的springboot支持-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
        </dependency>
        <!--dubbo框架-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.nacos</groupId>
            <artifactId>nacos-client</artifactId>
        </dependency>
    </dependencies>

</project>
~~~

##### 3.2.4.1、my-tanhua-dubbo-interface

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>my-tanhua-dubbo</artifactId>
        <groupId>cn.itcast.dubbo</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>my-tanhua-dubbo-interface</artifactId>
    <dependencies>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus</artifactId>
        </dependency>
    </dependencies>
</project>
~~~

##### 3.2.4.2、my-tanhua-dubbo-service

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>my-tanhua-dubbo</artifactId>
        <groupId>cn.itcast.dubbo</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>my-tanhua-dubbo-service</artifactId>

    <dependencies>
        <dependency>
            <groupId>cn.itcast.dubbo</groupId>
            <artifactId>my-tanhua-dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus</artifactId>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
    </dependencies>

</project>
~~~

#### 3.2.4、雷电模拟器

探花交友项目的前端采用Android APP的形式，所以我们需要使用模拟器或真机进行测试。

对于模拟器这里推荐使用雷电模拟器，其兼容性好、功能完善而且还简洁。将探花交友APP拖入即可安装。

下载：https://www.ldmnq.com/

 ![image-20210216180705437](assets/image-20210216180705437.png)

### 3.3、数据库表

数据库使用的mysql：

~~~sql
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码，需要加密',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `tb_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `logo` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `tags` varchar(50) DEFAULT NULL COMMENT '用户标签：多个用逗号分隔',
  `sex` int(1) DEFAULT '3' COMMENT '性别，1-男，2-女，3-未知',
  `age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `edu` varchar(20) DEFAULT NULL COMMENT '学历',
  `city` varchar(20) DEFAULT NULL COMMENT '居住城市',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  `cover_pic` varchar(50) DEFAULT NULL COMMENT '封面图片',
  `industry` varchar(20) DEFAULT NULL COMMENT '行业',
  `income` varchar(20) DEFAULT NULL COMMENT '收入',
  `marriage` varchar(20) DEFAULT NULL COMMENT '婚姻状态',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

~~~

### 3.4、编写配置

#### 3.4.1、my-tanhua-sso

application.properties：

~~~properties
#激活的配置，规则：application-{profile}.properties
spring.profiles.active=local
~~~

application-local.properties：

~~~properties
spring.application.name = itcast-tanhua-sso
#设置最大的文件上传大小
spring.servlet.multipart.max-request-size=30MB
spring.servlet.multipart.max-file-size=30MB

server.port = 18080
# Redis相关配置
spring.redis.jedis.pool.max-wait = 5000ms
spring.redis.jedis.pool.max-Idle = 100
spring.redis.jedis.pool.min-Idle = 10
spring.redis.timeout = 10s
spring.redis.cluster.nodes = 192.168.31.81:6379,192.168.31.81:6380,192.168.31.81:6381
spring.redis.cluster.max-redirects=5

# RocketMQ相关配置
rocketmq.name-server=192.168.31.81:9876
rocketmq.producer.group=tanhua

#itcast_tanhua
#盐 值
jwt.secret=76bd425b6f29f7fcc2e0bfc286043df1

#dubbo配置
dubbo.application.name = itcast-tanhua-sso
dubbo.registry.address = nacos://192.168.31.81:8848
dubbo.registry.timeout = 60000
dubbo.consumer.timeout = 60000

#nacos配置中心
nacos.config.server-addr = 192.168.31.81:8848
~~~

#### 3.4.2、my-tanhua-dubbo-service

application.properties：

~~~properties
#激活的配置，规则：application-{profile}.properties
spring.profiles.active=local
~~~

application-local.properties：

~~~properties
# Spring boot application
spring.application.name = itcast-tanhua-dubbo-service

# dubbo 扫描包配置
dubbo.scan.base-packages = com.tanhua.dubbo
dubbo.application.name = dubbo-provider-tanhua

#dubbo 对外暴露的端口信息
dubbo.protocol.name = dubbo
dubbo.protocol.port = 20880

#dubbo注册中心的配置
dubbo.registry.address = nacos://192.168.31.81:8848
dubbo.registry.timeout = 6000

#mysql数据库链接
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.31.81:3306/mytanhua?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root

# 枚举包扫描
mybatis-plus.type-enums-package=com.tanhua.dubbo.enums
# 表名前缀
mybatis-plus.global-config.db-config.table-prefix=tb_
# id策略为自增长
mybatis-plus.global-config.db-config.id-type=auto
~~~

#### 3.4.3、日志

log4j.properties：

~~~properties
### 设置###
log4j.rootLogger = debug,S,D,E

### 输出信息到控制抬 ###
log4j.appender.S = org.apache.log4j.ConsoleAppender
log4j.appender.S.Target = System.out
log4j.appender.S.layout = org.apache.log4j.PatternLayout
log4j.appender.S.layout.ConversionPattern = [%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} method:%l%n%m%n

### 输出DEBUG 级别以上的日志到=F://logs/error.log 每天产生一个文件###
log4j.appender.D = org.apache.log4j.DailyRollingFileAppender
log4j.appender.D.File = F://logs/log.log
log4j.appender.D.Append = true
log4j.appender.D.Threshold = DEBUG 
log4j.appender.D.layout = org.apache.log4j.PatternLayout
log4j.appender.D.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n

### 输出ERROR 级别以上的日志到=F://logs/error.log 每天产生一个文件###
log4j.appender.E = org.apache.log4j.DailyRollingFileAppender
log4j.appender.E.File =F://logs/error.log 
log4j.appender.E.Append = true
log4j.appender.E.Threshold = ERROR 
log4j.appender.E.layout = org.apache.log4j.PatternLayout
log4j.appender.E.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
~~~

### 3.5、编写基础代码

#### 3.5.1、Lombok

lombok 提供了简单的注解的形式来帮助我们简化消除一些必须有但显得很臃肿的 java 代码，尤其是针对pojo。

官网：https://projectlombok.org/

##### 3.5.1.1、配置安装

导入依赖：

~~~xml
<!--简化代码的工具包-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
~~~

安装IDEA插件： ![1542036205393](assets/1542036205393.png)

> 如果不安装插件，程序可以正常执行，但是看不到生成的一些代码，如：get、set方法。

##### 3.5.1.2、常用注解

- @Data：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
- @Setter：注解在属性上；为属性提供 setting 方法
- @Getter：注解在属性上；为属性提供 getting 方法
- @Slf4j：注解在类上；为类提供一个 属性名为log 的 slf4j日志对象
- @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
- @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
- @Builder：使用Builder模式构建对象

> 测试一：使用@Data注解

 ![image-20201116113610810](assets/image-20201116113610810.png)

是不是很神奇？！

> 测试二：使用@Slf4j注解 

 ![image-20201116113645107](assets/image-20201116113645107.png)

测试： 

![image-20201116113812602](assets/image-20201116113812602.png)

> 测试三：@AllArgsConstructor、@NoArgsConstructor注解的使用

 ![image-20201116114013519](assets/image-20201116114013519.png)

> 测试四：@Builder

![image-20201116114233416](assets/image-20201116114233416.png)

测试结果：

![image-20201116114254202](assets/image-20201116114254202.png)

#### 3.5.2、SexEnum

用户的性别用枚举进行表示，代码在my-tanhua-dubbo-interface工程中。

~~~java
package com.tanhua.dubbo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum SexEnum implements IEnum<Integer> {

    MAN(1,"男"),
    WOMAN(2,"女"),
    UNKNOWN(3,"未知");

    private int value;
    private String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
~~~

#### 3.5.3、User、UserInfo

代码在my-tanhua-dubbo-interface工程中。

~~~java
package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;


public abstract class BasePojo implements java.io.Serializable{

    @TableField(fill = FieldFill.INSERT) //MP自动填充
    private Date created;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;
}

~~~



~~~java
package com.tanhua.dubbo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BasePojo {

    private static final long serialVersionUID = 4899169410509180585L;

    private Long id;
    private String mobile; //手机号

    @JsonIgnore
    private String password; //密码，json序列化时忽略

}

~~~

~~~java
package com.tanhua.dubbo.pojo;

import com.tanhua.dubbo.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BasePojo {

    private static final long serialVersionUID = -7814043583142105146L;
    private Long id;
    private Long userId; //用户id
    private String nickName; //昵称
    private String logo; //用户头像
    private String tags; //用户标签：多个用逗号分隔
    private SexEnum sex; //性别
    private Integer age; //年龄
    private String edu; //学历
    private String city; //城市
    private String birthday; //生日
    private String coverPic; // 封面图片
    private String industry; //行业
    private String income; //收入
    private String marriage; //婚姻状态

}

~~~

#### 3.5.4、MyMetaObjectHandler

对自动填充字段的处理，代码在my-tanhua-dubbo-service工程中。

~~~java
package com.tanhua.dubbo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object created = getFieldValByName("created", metaObject);
        if (null == created) {
            //字段为空，可以进行填充
            setFieldValByName("created", new Date(), metaObject);
        }

        Object updated = getFieldValByName("updated", metaObject);
        if (null == updated) {
            //字段为空，可以进行填充
            setFieldValByName("updated", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新数据时，直接更新字段
        setFieldValByName("updated", new Date(), metaObject);
    }
}

~~~

#### 3.5.5、UserMapper

代码在my-tanhua-dubbo-service工程中。

~~~java
package com.tanhua.dubbo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanhua.dubbo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
~~~

~~~java
package com.tanhua.dubbo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanhua.dubbo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}

~~~

#### 3.5.6、SSOApplication

SpringBoot的启动类。代码在my-tanhua-sso中。

~~~java
package com.tanhua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class, args);
    }
}
~~~

#### 3.5.7、DubboApplication

代码在my-tanhua-dubbo-service工程中。

~~~java
package com.tanhua.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class, args);
    }
}

~~~

### 3.6、Nacos

Nacos是一个阿里巴巴开源项目，致力于帮助您发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。

在我们项目中，我们主要讲Nacos用作dubbo注册中心以及配置中心使用，以取代ZooKeeper。

官方：https://nacos.io/zh-cn/

#### 3.6.1、部署

通过docker部署：

~~~shell
docker run --restart=always --env MODE=standalone --name nacos -d -p 8848:8848 nacos/nacos-server:2.0.0
~~~

打开浏览器：http://192.168.31.81:8848/nacos

默认用户名密码：nacos/nacos

![image-20210329153911040](assets/image-20210329153911040.png)

#### 3.6.2、服务列表

在服务列表中，可以查看dubbo注册到nacos的服务。

![image-20210329154108030](assets/image-20210329154108030.png)

可以查看详情：

![image-20210329154747972](assets/image-20210329154747972.png)

#### 3.6.3、配置列表

![image-20210329154815523](assets/image-20210329154815523.png)

新增配置，Data ID必填项，Group使用默认即可。

![image-20210329154920985](assets/image-20210329154920985.png)

修改配置：

![image-20210329154858742](assets/image-20210329154858742.png)

### 3.7、短信验证码

发送短信验证码的流程：

 ![image-20201116090449776](assets/image-20201116090449776.png)

流程说明：

- 用户向SSO系统发送请求，在请求中传递手机号；
- SSO系统接收到请求后，生成随机验证码以及短信内容，请求阿里云短信服务；
- 阿里云短信服务接收到请求后，会进行一系列的验证，比如账号余额、短信模板是否正确等，最后向运营商发起请求；
- 运营商接收到请求后，向该手机号下发短信，用户即可收到短信；

#### 3.7.1、阿里云短信服务

##### 3.7.1.1、申请签名与模板

https://dysms.console.aliyun.com/dysms.htm?spm=5176.12818093.0.ddysms.2a4316d0ql6PyD

![image-20201016193819097](assets/image-20201016193819097.png)

> 说明：申请签名时，个人用户只能申请一个并且签名的名称必须为“ABC商城”，否则审核不通过。

申请模板：

 ![image-20201016193927924](assets/image-20201016193927924.png)

> 审核时间需要1~2小时，请耐心等待~

##### 3.7.1.2、设置用户权限

在阿里云中，需要在RAM服务中创建用户以及权限，才能通过api进行访问接口。

![image-20201016194331790](assets/image-20201016194331790.png)

创建用户：

 ![image-20201016194409024](assets/image-20201016194409024.png)

![image-20201016195011698](assets/image-20201016195011698.png)

创建完成后要保存AccessKey Secret和AccessKey ID，AccessKey Secret只显示这一次，后面将不再显示。

添加权限：

![image-20201016194636739](assets/image-20201016194636739.png)

##### 3.7.1.3、示例代码

文档：https://help.aliyun.com/document_detail/101414.html?spm=a2c4g.11186623.6.625.18705ffa8u4lwj：

导入依赖：

~~~xml
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>dysmsapi20170525</artifactId>
    <version>2.0.1</version>
</dependency>
~~~

~~~java
package com.tanhua.sso.service;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;

public class SendSms {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint("dysmsapi.aliyuncs.com");
        // 访问的域名
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dysmsapi20170525.Client client = SendSms
                .createClient("**********", "**********");

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers( "158****7944") //目标手机号
                .setSignName("ABC商城") //签名名称
                .setTemplateCode("SMS_204756062") //短信模板code
                .setTemplateParam("{\"code\":\"1111\"}"); //模板中变量替换
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);

        SendSmsResponseBody body = sendSmsResponse.getBody();

        // code = OK 代表成功
        System.out.println(body.getCode() + "  " + body.getMessage());
    }

}
~~~

##### 3.7.1.4、实现发送短信方法

将配置文件写入到Nacos中，dataId为：aliyun.properties

~~~properties
aliyun.sms.accessKeyId = ***********
aliyun.sms.accessKeySecret = ***********
aliyun.sms.domain= dysmsapi.aliyuncs.com
aliyun.sms.signName= ABC商城
aliyun.sms.templateCode= SMS_204756062
~~~

读取配置：

~~~java
package com.tanhua.sso.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
//从nacos配置中心读取内容，并且支持自动刷新功能
@NacosConfigurationProperties(dataId = "aliyun.properties", prefix = "aliyun.sms", autoRefreshed = true)
@Data
public class AliyunSMSConfig {

    private String accessKeyId;
    private String accessKeySecret;
    private String domain;
    private String signName;
    private String templateCode;

}
~~~

代码实现：

~~~java
package com.tanhua.sso.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.tanhua.sso.config.AliyunSMSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {

    @Autowired
    private AliyunSMSConfig aliyunSMSConfig;

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    public String sendSms(String mobile) {
        //随机生成6位数字验证码
        String code = RandomUtil.randomNumbers(6);
        try {
            Config config = new Config()
                    .setAccessKeyId(this.aliyunSMSConfig.getAccessKeyId())
                    .setAccessKeySecret(this.aliyunSMSConfig.getAccessKeySecret())
                    .setEndpoint(this.aliyunSMSConfig.getDomain());

            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(mobile)//目标手机号
                    .setSignName(this.aliyunSMSConfig.getSignName()) //签名名称
                    .setTemplateCode(this.aliyunSMSConfig.getTemplateCode()) //短信模板code
                    .setTemplateParam("{\"code\":\"" + code + "\"}"); //模板中变量替换
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            SendSmsResponseBody body = sendSmsResponse.getBody();
            if (StrUtil.equals("OK", body.getCode())) {
                return code;
            }
        } catch (Exception e) {
            log.error("发送短信验证码失败！" + mobile, e);
        }
        return null;
    }
}

~~~

#### 3.7.2、SSO短信接口服务

##### 3.7.2.1、APP接口

地址：https://mock-java.itheima.net/project/35/interface/api/581

 ![1564535110994](assets/1564535110994.png)

##### 3.7.2.2、通用的响应处理

在项目中有这样的约定：

- 如果成功，就响应200状态码
- 如果失败，就响应500状态码
- 响应的数据直接返回，无需进行包装处理

如何确定是失败呢？我们定义一个ErrorResult对象，如果在Controller中返回该对象就说明是失败，其他对象说明是成功。

代码在my-tanhua-common。

~~~java
package com.tanhua.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {

    private String errCode;
    private String errMessage;
}

~~~

通用的响应处理：

~~~java
package com.tanhua.common.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.tanhua.common.utils.NoCommonResult;
import com.tanhua.common.vo.ErrorResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 在项目中约定：
 * 1. 如果成功，就响应200状态码
 * 2. 如果失败，就响应500状态码
 * 3. 响应的数据直接返回，无需进行包装处理
 */
@ControllerAdvice
public class CommonResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //所有没有包含@NoCommonResult注解的都进行处理
        return !methodParameter.hasMethodAnnotation(NoCommonResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (ObjectUtil.isEmpty(result)) {
            return result;
        }

        if (result instanceof ErrorResult) {
            // 如果返回对象为ErrorResult， 设置响应状态码为500
            serverHttpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            //正常情况下响应200
            serverHttpResponse.setStatusCode(HttpStatus.OK);
        }

        return result;
    }
}

~~~

~~~java
package com.tanhua.common.utils;

import java.lang.annotation.*;

/**
 * 默认情况下会统一处理响应，如果标记为此注解的方法不进行通用处理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //标记注解
public @interface NoCommonResult {

}
~~~

##### 3.7.2.3、统一异常处理

代码在my-tanhua-common。

~~~java
package com.tanhua.common.handler;

import com.tanhua.common.vo.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常的统一处理，如果Controller中没有处理异常会在这里捕获处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleExcepting(Exception e) {
        log.error("error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResult.builder().errMessage("出错了，请稍后再试~").errCode("500").build());
    }

}

~~~

##### 3.7.2.4、实现接口服务

SmsController：

~~~java
package com.tanhua.sso.controller;

import cn.hutool.core.convert.Convert;
import com.tanhua.sso.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class SmsController {

    @Autowired
    private SmsService smsService;

    /**
     * 登录第一步，发送手机短信验证码
     *
     * @param param
     * @return
     */
    @PostMapping("login")
    public Object sendCheckCode(@RequestBody Map<String, Object> param) {
        String phoneNumber = Convert.toStr(param.get("phone"));
        return this.smsService.sendCheckCode(phoneNumber);
    }
}

~~~

SmsService：

~~~java
package com.tanhua.sso.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.tanhua.sso.config.AliyunSMSConfig;
import com.tanhua.sso.vo.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class SmsService {

    @Autowired
    private AliyunSMSConfig aliyunSMSConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    public String sendSms(String mobile) {
        //随机生成6位数字验证码
        String code = RandomUtil.randomNumbers(6);
        try {
            Config config = new Config()
                    .setAccessKeyId(this.aliyunSMSConfig.getAccessKeyId())
                    .setAccessKeySecret(this.aliyunSMSConfig.getAccessKeySecret())
                    .setEndpoint(this.aliyunSMSConfig.getDomain());

            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(mobile)//目标手机号
                    .setSignName(this.aliyunSMSConfig.getSignName()) //签名名称
                    .setTemplateCode(this.aliyunSMSConfig.getTemplateCode()) //短信模板code
                    .setTemplateParam("{\"code\":\"" + code + "\"}"); //模板中变量替换
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            SendSmsResponseBody body = sendSmsResponse.getBody();
            if (StrUtil.equals("OK", body.getCode())) {
                return code;
            }
        } catch (Exception e) {
            log.error("发送短信验证码失败！" + mobile, e);
        }
        return null;
    }

    /**
     * 发送短信验证码
     * 实现：发送完成短信验证码后，需要将验证码保存到redis中
     * @param phone
     * @return
     */
    public ErrorResult sendCheckCode(String phone) {
        String redisKey = "CHECK_CODE_" + phone;

        //先判断该手机号发送的验证码是否还未失效
        if(this.redisTemplate.hasKey(redisKey)){
            String msg = "上一次发送的验证码还未失效！";
            return ErrorResult.builder().errCode("000001").errMessage(msg).build();
        }

        String code = this.sendSms(phone);
        if(StrUtil.isEmpty(code)){
            String msg = "发送短信验证码失败！";
            return ErrorResult.builder().errCode("000000").errMessage(msg).build();
        }

        //短信发送成功，将验证码保存到redis中，有效期为5分钟
        this.redisTemplate.opsForValue().set(redisKey, code, Duration.ofMinutes(5));

        return null;
    }
}

~~~

### 3.8、用户登录

用户接收到验证码后，进行输入验证码，点击登录，前端系统将手机号以及验证码提交到SSO进行校验。

 ![image-20201118231431191](assets/image-20201118231431191.png)

#### 3.7.1、dubbo服务

##### 3.7.1.1、定义接口

在my-tanhua-dubbo-interface工程中完成。

~~~java
package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.User;

public interface UserApi {

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    User queryByMobile(String mobile);

    /**
     * 根据手id查询用户
     *
     * @param id
     * @return
     */
    User queryById(Long id);


    /**
     * 注册新用户,返回用户id
     *
     * @param mobile
     * @return
     */
    Long save(String mobile);

}

~~~

~~~java
package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.UserInfo;

import java.util.List;

public interface UserInfoApi {

    /**
     * 完善个人信息
     *
     * @param userInfo
     * @return
     */
    Boolean save(UserInfo userInfo);

    /**
     * 更新个人信息，必须包含userId字段
     *
     * @param userInfo
     * @return
     */
    Boolean update(UserInfo userInfo);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    UserInfo queryByUserId(Long userId);

    /**
     * 根据用户id列表查询用户信息列表
     *
     * @param userIdList
     * @return
     */
    List<UserInfo> queryByUserIdList(List<Object> userIdList);


}

~~~

##### 3.7.1.2、编写实现

~~~java
package com.tanhua.dubbo.api.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.mapper.UserMapper;
import com.tanhua.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0")
public class UserApiImpl implements UserApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByMobile(String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        return this.userMapper.selectOne(queryWrapper);
    }

    @Override
    public User queryById(Long id) {
        return this.userMapper.selectById(id);
    }

    @Override
    public Long save(String mobile) {
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(SecureUtil.md5("123456")); //默认密码
        if (this.userMapper.insert(user) > 0) {
            return user.getId();
        }
        return null;
    }
}

~~~

~~~java
package com.tanhua.dubbo.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.mapper.UserInfoMapper;
import com.tanhua.dubbo.pojo.UserInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class UserInfoApiImpl implements UserInfoApi {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean save(UserInfo userInfo) {
        return this.userInfoMapper.insert(userInfo) == 1;
    }

    @Override
    public Boolean update(UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userInfo.getUserId());
        return this.userInfoMapper.update(userInfo, queryWrapper) > 0;
    }

    @Override
    public UserInfo queryByUserId(Long userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.userInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public List<UserInfo> queryByUserIdList(List<Object> userIdList) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", userIdList);
        return this.userInfoMapper.selectList(queryWrapper);
    }
}

~~~

#### 3.7.1、APP接口

接口地址：https://mock-java.itheima.net/project/35/interface/api/587

 ![1564300265546](assets/1564300265546.png)

#### 3.7.2、UserController

~~~java
package com.tanhua.sso.controller;

import cn.hutool.core.convert.Convert;
import com.tanhua.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过验证码进行登录
     *
     * @param param
     * @return
     */
    @PostMapping("loginVerification")
    public Object login(@RequestBody Map<String, Object> param) {
        String phone = Convert.toStr(param.get("phone"));
        String code = Convert.toStr(param.get("verificationCode"));
        return this.userService.login(phone, code);
    }
}

~~~

#### 3.7.3、UserService

~~~java
package com.tanhua.sso.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @DubboReference(version = "1.0.0")
    private UserApi userApi;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 实现登录
     *
     * @param phone
     * @param code
     * @return
     */
    public Object login(String phone, String code) {
        //校验验证码是否正确
        String redisKey = "CHECK_CODE_" + phone;
        String redisCode = this.redisTemplate.opsForValue().get(redisKey);
        if (!StrUtil.equals(redisCode, code)) {
            return ErrorResult.builder().errCode("000001")
                    .errMessage("验证错误，请重新输入！").build();
        }

        //删除验证码
        this.redisTemplate.delete(redisKey);

        //根据手机号查询用户数据
        User user = this.userApi.queryByMobile(phone);
        boolean isNew = false;
        Long userId;
        if(ObjectUtil.isEmpty(user)){
            //注册新用户
            userId = this.userApi.save(phone);
            isNew = true;
        }else{
            userId = user.getId();
        }

        //TODO 生成token
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjI4MjkzMzYsInVzZXJfaWQiOiIxIn0.Mbzn6LzsLrkVWEbhexR3lTYDZjxqIcqW11rJxDQ6Ewk";

        //发送消息
        try {
            //发送用户登录成功的消息
            Map<String, Object> msg = new HashMap<>();
            msg.put("id", userId);
            msg.put("date", System.currentTimeMillis());

            this.rocketMQTemplate.convertAndSend("tanhua-sso-login", msg);
        } catch (MessagingException e) {
            log.error("发送消息失败！", e);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("isNew", isNew);
        return result;
    }
}

~~~

#### 3.7.4、测试

 ![1564300534797](assets/1564300534797.png)

### 3.9、RSA加密算法

#### 3.9.1、加密技术的类型

加密技术是对信息进行编码和解码的技术，编码是把原来可读信息（又称明文）译成代码形式（又称密文），其逆过程就是解码（解密），加密技术的要点是加密算法，加密算法可以分为三类：  

- 对称加密，如AES
  - 基本原理：将明文分成N个组，然后使用密钥对各个组进行加密，形成各自的密文，最后把所有的分组密文进行合并，形成最终的密文。
  - 优势：**算法公开**、计算量小、加密速度快、加密效率高
  - 缺陷：**双方都使用同样密钥**，安全性得不到保证 
- 非对称加密，如RSA
  - 基本原理：同时生成两把密钥：私钥和公钥，**私钥隐秘保存**，**公钥可以下发给信任客户端**
    - 私钥加密，持有公钥可以解密
    - 公钥加密，持有私钥可以解密
    - 私钥加密，私钥无法解密，只能是公钥解密
    - 公钥加密，公钥无法解密，只能是私钥解密
  - 优点：安全，难以破解
  - 缺点：算法比较耗时
- 不可逆加密，如MD5，SHA
  - 基本原理：加密过程中不需要使用[密钥](https://baike.baidu.com/item/%E5%AF%86%E9%92%A5)，输入明文后由系统直接经过加密算法处理成密文，这种加密后的数据是无法被解密的，无法根据密文推算出明文。

#### 3.9.2、RSA加密

  ![img](assets/bg2013062702.jpg)

![image-20210329161343749](assets/image-20210329161343749.png)

更多内容：http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html

测试代码：

~~~java
package com.tanhua.sso;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Assert;
import org.junit.Test;

public class TestRSA {

    @Test
    public void testRSA() {
        RSA rsa = new RSA();

        // //获得私钥
        // rsa.getPrivateKey()
        System.out.println("私钥: " + rsa.getPrivateKeyBase64());
        // //获得公钥
        // rsa.getPublicKey()
        System.out.println("公钥: " + rsa.getPublicKeyBase64());

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("黑马程序员", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

        Assert.assertEquals("黑马程序员", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("黑马程序员", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        Assert.assertEquals("黑马程序员", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

    }
}
~~~

### 3.10、JWT

#### 3.10.1、简介

JSON Web token简称JWT， 是用于对应用程序上的用户进行身份验证的标记。也就是说, 使用 JWTS 的应用程序不再需要保存有关其用户的 cookie 或其他session数据。此特性便于可伸缩性, 同时保证应用程序的安全。

在身份验证过程中, 当用户使用其凭据成功登录时, 将返回 JSON Web token, 并且必须在本地保存 (通常在本地存储中)。

每当用户要访问受保护的路由或资源 (端点) 时, 用户代理(user agent)必须连同请求一起发送 JWT, 通常在授权标头中使用Bearer schema。后端服务器接收到带有 JWT 的请求时, 首先要做的是验证token。

#### 3.10.2、格式

- JWT就是一个字符串，经过加密处理与校验处理的字符串，形式为：A.B.C

- A由JWT头部信息header经过base64加密得到

  - ~~~json
    #默认的头信息
    {
      "alg": "HS256",
      "typ": "JWT"
    }
    
    #官网测试：https://jwt.io/
    #base64加密后的字符串为：eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
    ~~~

- B是payload，存放有效信息的地方，这些信息包含三个部分：

  - 标准中注册的声明 (建议但不强制使用) 

    - iss: jwt签发者
    - sub: jwt所面向的用户
    - aud: 接收jwt的一方
    - exp: jwt的过期时间，这个过期时间必须要大于签发时间
    - nbf: 定义在什么时间之前，该jwt都是不可用的.
    - iat: jwt的签发时间
    - jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。

  - 公共的声明

    - 公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.

  - 私有的声明

    - 私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。

  - ~~~json
    #存放的数据：
    {
      "sub": "1234567890",
      "name": "John Doe",
      "iat": 1516239022
    }
    
    #base64后的字符串为：
    eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ
    ~~~

- C由A和B通过加密算法得到，用作对token进行校验，看是否有效

  - 这个部分需要base64加密后的header和base64加密后的payload使用`.`连接组成的字符串，然后通过header中声明的加密方式进行加盐`secret`组合加密，然后就构成了jwt的第三部分。

  - ~~~json
    #secret为：itcast
    #得到的加密字符串为：DwMTjJktoFFdClHqjJMRgYzICo6FJOUc3Jmev9EScBc
    
    #整体的token为：eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.DwMTjJktoFFdClHqjJMRgYzICo6FJOUc3Jmev9EScBc
    ~~~


#### 3.10.3、流程

 ![1563895628713](assets/1563895628713.png)

#### 3.10.4、示例

导入依赖：

~~~xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
~~~

编写测试用例：

~~~java
package com.tanhua.sso;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJWT {

    String secret = "itcast";

    @Test
    public void testCreateToken(){

        Map<String, Object> header = new HashMap<String, Object>();
        header.put(JwsHeader.TYPE, JwsHeader.JWT_TYPE);
        header.put(JwsHeader.ALGORITHM, "HS256");

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("mobile", "1333333333");
        claims.put("id", "2");

        // 生成token
        String jwt = Jwts.builder()
                .setHeader(header)  //header，可省略
                .setClaims(claims) //payload，存放数据的位置，不能放置敏感数据，如：密码等
                .signWith(SignatureAlgorithm.HS256, secret) //设置加密方法和加密盐
                .setExpiration(new Date(System.currentTimeMillis() + 30000)) //设置过期时间，30秒后过期
                .compact();

        System.out.println(jwt);

    }

    @Test
    public void testDecodeToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiIxMzMzMzMzMzMzIiwiaWQiOiIyIiwiZXhwIjoxNjE3MDA5NDM0fQ.YQL2Y4IySMiIFAdMbr4HAUJ0e3tkTMhLZlPuSqizoNk";
        try {
            // 通过token解析数据
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(body); //{mobile=1333333333, id=2, exp=1605513392}
        } catch (ExpiredJwtException e) {
            System.out.println("token已经过期！");
        } catch (Exception e) {
            System.out.println("token不合法！");
        }
    }

}
~~~

#### 3.10.5、使用RSA加密

~~~java
package com.tanhua.sso;

import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestRSAJWT {

    @Test
    public void testRSAToken(){

        RSA rsa = new RSA();

        Map<String, Object> header = new HashMap<String, Object>();
        header.put(JwsHeader.TYPE, JwsHeader.JWT_TYPE);
        header.put(JwsHeader.ALGORITHM, "RS256");

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("mobile", "1333333333");
        claims.put("id", "2");

        // 生成token
        String token = Jwts.builder()
                .setHeader(header)  //header，可省略
                .setClaims(claims) //payload，存放数据的位置，不能放置敏感数据，如：密码等
                .signWith(SignatureAlgorithm.RS256, rsa.getPrivateKey()) //通过RSA的私钥加密
                .setExpiration(new Date(System.currentTimeMillis() + 30000)) //设置过期时间，30秒后过期
                .compact();

        System.out.println("token: " + token);

        try {
            // 通过token解析数据
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(rsa.getPublicKey()) //通过公钥校验
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(body); //{mobile=1333333333, id=2, exp=1605513392}

            Map<String, Object> body2 = Jwts.parser()
                    .setSigningKey(rsa.getPrivateKey()) //通过私钥校验
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(body2); //{mobile=1333333333, id=2, exp=1605513392}

        } catch (ExpiredJwtException e) {
            System.out.println("token已经过期！");
        } catch (Exception e) {
            System.out.println("token不合法！");
        }

    }

}
~~~

#### 3.10.6、如何选择

在通过jwt生成token的学习中，有2种方式，一种是对称加密，另一种是非对称加密，如何选择呢？

如果采用对称加密的，我们的登录以及校验流程如下：

![image-20210329174802269](assets/image-20210329174802269.png)

如果采用非对称加密的，我们的登录以及校验流程如下：

![image-20210329175029911](assets/image-20210329175029911.png)

可以看出，区别在于Server等系统在对token校验时是否需要通过sso系统完成，显然，使用非对称加密可以减少接口的调用，提升了整体的性能。

所以，我们在生成token时采用RSA非对称加密方式。即：

- 由sso系统生成密钥和公钥，密钥交由sso系统保存，并通过密钥生成token。
- 公钥下发给其他系统，其他系统可以通过公钥校验token是否有效，但是自己无法生成token。

### 3.11、整合实现

下面我们将使用RSA加密的方式实现token的生成。

#### 3.11.1、编写JwtUtils

在my-tanhua-common中编写JwtUtils工具类，方便我们对于jwt的操作。

首先导入依赖：

~~~xml
<dependency>
     <groupId>io.jsonwebtoken</groupId>
     <artifactId>jjwt</artifactId>
</dependency>
~~~

具体实现如下：

~~~java
package com.tanhua.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {


    /**
     * 生成token
     *
     * @param claims 数据
     * @param privateKey 私钥
     * @param time 有效期时间，单位是：小时
     * @return
     */
    public static String createToken(Map<String, Object> claims, String privateKey, int time) {
        //通过传入的私钥构造RSA对象，无需公钥，不再生成私钥
        RSA rsa = new RSA(privateKey, null);
        Map<String, Object> header = new HashMap<String, Object>();
        header.put(JwsHeader.TYPE, JwsHeader.JWT_TYPE);
        header.put(JwsHeader.ALGORITHM, "RS256");

        // 生成token
        return Jwts.builder()
                .setHeader(header)  //header，可省略
                .setClaims(claims) //payload，存放数据的位置，不能放置敏感数据，如：密码等
                .signWith(SignatureAlgorithm.RS256, rsa.getPrivateKey()) //通过RSA的私钥加密
                .setExpiration(DateUtil.offsetHour(new Date(), time)) //设置过期时间，单位是：小时
                .compact();
    }

    public static Map<String, Object> checkToken(String token, String publicKey) {
        //通过传入的公钥构造RSA对象，无需私钥，不再生成公钥
        RSA rsa = new RSA(null, publicKey);
        try {
            // 通过token解析数据
            return Jwts.parser()
                    .setSigningKey(rsa.getPublicKey()) //通过公钥校验
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            //无需输出任何内容
            //System.out.println("token已经过期！");
        } catch (Exception e) {
            log.error("token不合法！ token = " + token);
        }
        return null;
    }
}

~~~

#### 3.11.2、RSAService

RSA的私钥与公钥应保存在本地磁盘中，对于sso系统而言，要负责生成私钥、公钥，生成时使用私钥，对于其他系统而言，只需要保存公钥即可。一定要注意，千万不能将私钥泄漏出去。

在application-local.properties配置文件中配置存放的目录：

~~~properties
#rsa文件的目录
tanhua.rsa.dir = F:\\code\\rsa
~~~

具体实现如下：

~~~java
package com.tanhua.sso.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 初始化RSA，读取RSA的密钥文件
 */
@Service
@Data
public class RSAService {

    private String privateKey;
    private String publicKey;

    @Value("${tanhua.rsa.dir}")
    private String rsaDir;

    /**
     * 完成公钥、私钥的读取操作
     */
    @PostConstruct
    public void init() {
        //私钥的文件路径，通过File.separator解决跨平台的目录分隔符的问题
        String privateKeyFile = rsaDir + File.separator + "rsa";
        String publicKeyFile = rsaDir + File.separator + "rsa.pub";

        if (FileUtil.exist(privateKeyFile)) {
            //读取
            this.privateKey = FileUtil.readString(privateKeyFile, CharsetUtil.CHARSET_UTF_8);
        }

        if (FileUtil.exist(publicKeyFile)) {
            //读取
            this.publicKey = FileUtil.readString(publicKeyFile, CharsetUtil.CHARSET_UTF_8);
        }

        //如果公钥和私钥都不存在，就需要创建公钥和私钥
        if (!StrUtil.isAllEmpty(privateKey, publicKey)) {
            return;
        }
        //生成新的公钥和私钥
        RSA rsa = new RSA();
        //将公钥和私钥写入到文件
        FileUtil.writeString(rsa.getPrivateKeyBase64(), privateKeyFile, CharsetUtil.CHARSET_UTF_8);
        FileUtil.writeString(rsa.getPublicKeyBase64(), publicKeyFile, CharsetUtil.CHARSET_UTF_8);

        //第一次生成时，赋值给变量
        this.privateKey = rsa.getPrivateKeyBase64();
        this.publicKey = rsa.getPublicKeyBase64();

    }
}

~~~

#### 3.11.3、生成token

~~~java
package com.tanhua.sso.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.tanhua.common.utils.JwtUtils;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    // 引入dubbo服务
    @DubboReference(version = "1.0.0")
    private UserApi userApi;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RSAService rsaService;

    /**
     * 实现登录
     *
     * @param phone
     * @param code
     * @return
     */
    public Object login(String phone, String code) {
        //校验验证码是否正确
        String redisKey = "CHECK_CODE_" + phone;
        String redisCode = this.redisTemplate.opsForValue().get(redisKey);
        if (!StrUtil.equals(redisCode, code)) {
            return ErrorResult.builder().errCode("000001")
                    .errMessage("验证错误，请重新输入！").build();
        }

        //删除验证码
        this.redisTemplate.delete(redisKey);

        //根据手机号查询用户数据
        User user = this.userApi.queryByMobile(phone);
        boolean isNew = false;
        Long userId;
        if(ObjectUtil.isEmpty(user)){
            //注册新用户
            userId = this.userApi.save(phone);
            isNew = true;
        }else{
            userId = user.getId();
        }

        //生成token
        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        String token = JwtUtils.createToken(map, this.rsaService.getPrivateKey(), 12);

        //发送消息
        try {
            //发送用户登录成功的消息
            Map<String, Object> msg = new HashMap<>();
            msg.put("id", userId);
            msg.put("date", System.currentTimeMillis());

            this.rocketMQTemplate.convertAndSend("tanhua-sso-login", msg);
        } catch (MessagingException e) {
            log.error("发送消息失败！", e);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("isNew", isNew);
        return result;
    }
}

~~~

测试，生成的token数据如下：

~~~json
{
  "isNew": false,
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjE3MTEzMTE0fQ.dUccY26uld3gYH2LPKiOEcOyiKFqZIfgqGo13jXAlKYiB3Xpp0jLJDm2qaZvbO__b8wNKXaX-2YiSomQCxpasub28r9S070RcQGXqJL9FM1jH5xlKShDn1R4Ygtf5I-S49MxfbSIju_oB1-UB3RQuxYkgJq9DYHdhfcvCJMnSYw"
}
~~~