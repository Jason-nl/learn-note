## maven-day01

## 目录

[TOC]

## 学习目标

```
1、掌握maven的核心概念
2、掌握maven私服的搭建和使用
3、重点掌握maven的依赖管理、聚合管理、继承管理、版本管理
4、重点掌握理解maven分层构建SSM项目
5、了解maven的plugin组件
```



## 第一章 maven概述

### 1、为什么要使用maven

**① 一个项目就是一个工程**

如果项目非常庞大，就不适合使用package来划分模块，最好是每一个模块对应一个工程，利于分工协作。

借助于maven就可以将一个项目拆分成多个工程

**② 项目中使用jar包，需要“复制”、“粘贴”项目的lib中**

同样的jar包重复的出现在不同的项目工程中，你需要做不停的复制粘贴的重复工作。

借助于maven，可以将jar包保存在“仓库”中，不管在哪个项目只要使用引用即可就行。

**③ jar包需要的时候每次都要自己准备好或到官网下载**

借助于maven我们可以使用统一的规范方式下载jar包

**④ jar包版本不一致的风险**

不同的项目在使用jar包的时候，有可能会导致各个项目的jar包版本不一致，导致未执行错误。

借助于maven，所有的jar包都放在“仓库”中，所有的项目都使用仓库的一份jar包。

**⑤ 一个jar包依赖其他的jar包需要自己手动的加入到项目中**

FileUpload组件->IO组件，commons-fileupload-1.3.jar依赖于commons-io-2.0.1.jar

极大的浪费了我们导入包的时间成本，也极大的增加了学习成本。

借助于maven，它会自动的将依赖的jar包导入进来。

### 2、maven是什么

​		maven是基于项目对象模型（project object model：pom）的项目管理工具， 翻译为“专家”、“内行”。是一个采用纯 Java 编写的开源项目管理工具，Maven 采用了一种被称之为 project object model (pom)概念来管理项目， 所有的项目配置信息都被定义在一个叫做pom.xml的文件中, 通过该文件 maven 可以管理项目的整个构建生命周期：清除、编译、测试、报告、打包、部署等。
​	

​		目前 Apache 下绝大多数项目都已经采用 maven 进行管理. 而 maven 本身还支持多种插件，可以方便灵活的控制项目, 开发人员的主要任务应该是关注业务逻辑并去实现它，不需要把时间浪费在如何在不同的环境中去依赖 jar 包、项目部署等任务上。maven 正是为了将开发人员从这些任务中解脱出来，而诞生的一个项目管理工具。

### 3、maven的作用

​		**项目构建管理：** maven提供一套对项目生命周期管理的标准，开发人员、和测试人员统一使用maven进行项目构建。项目生命周期管理：编译、测试、打包、部署、运行。

​		**管理依赖：** maven能够帮我们统一管理项目开发中需要的jar包，依赖JAR管理，本地项目JAR管理；

​		**管理插件：** maven能够帮我们统一管理项目开发过程中需要的插件；

### 4、maven概念模型

![image-20191125153254630](image\image-20191125153254630.png)

项目对象模型：maven根据项目的pom.xml文件，把它转化成项目对象模型(POM)

依赖管理模型：根据maven加载的【**项目对象模型**】去【**仓库**】中下载对应的jar文件

仓库:统一的位置存储所有项目的共享的构件（依赖和插件)

构建生命周期：管理项目的生命周期，例如：清理，编译，测试、报告、打包、安装及部署

插件：工具

## 第二章 maven核心概念

### 1、坐标

什么是坐标？

```
在平面数学中我们说(X,Y)这样可以确定一个点的标记叫坐标。
```

maven中的坐标：

 使用下面的三个向量在仓库中唯一的定位一个maven工程 

```properties
- groupId：包名：组织或公司倒序+项目
- artifactId：子模块名称
- version：版本
```

例如：

![image-20191127102453554](image\image-20191127102453554.png)

maven坐标和仓库对应的映射关系：[groupId]\[artifactId]\[version]\[artifactId]-[version].jar

去本地仓库看一下此目录：

org\springframework\spring-core\5.2.0.RELEASE\spring-core-5.2.0.RELEASE.jar



### 2、仓库

​		maven 在某个统一的位置存储所有项目的共享的构件（依赖和插件），这个统一的位置，就称之为仓库（仓库就是存放依赖和插件的地方）分为：

- 本地仓库、

- 远程仓库（远程仓库中分为 3 种：中央仓库、其他公共库、私服仓库
  	

#### 【1】本地仓库

​		maven 在本机存储构件的地方。maven 的本地仓库，在安装 maven 后并不会创建，它是在第一次执行 maven 命令的时候才被创建。maven 本地仓库的默认位置：在操作系统用户的目录下，有一个.m2/repository/的仓库目录。仓库位置可以修改:

#### 【2】远程仓库

##### 【2.1】中央仓库

​		包含了绝大多数流行的开源 Java 构件，以及源码、作者信息、SCM、 信息、许可证信息等。开源的 Java 项目依赖的构件都可以在这里下载到
​	
中央仓库地址：http://repo1.maven.org/maven2/ 

##### 【2.2】其他公共库

阿里云：http://maven.aliyun.com/nexus/content/groups/public/

maven的setting文件配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <!--配置本地仓库地址-->
  <localRepository>D:/java/maven/repository2</localRepository>
  <pluginGroups>
    <pluginGroup>org.apache.maven.plugins</pluginGroup>
  </pluginGroups>

  <proxies>
  </proxies>
  <servers>
  </servers>

  <mirrors>
    <!-- 使用aliyun中央库 -->
    <mirror>
      <id>central</id>
      <name>aliyun-central</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>

  <profiles>
    <profile>
      <id>downloadSources</id>
      <properties>
        <downloadSources>true</downloadSources>
        <downloadJavadocs>true</downloadJavadocs>
      </properties>
    </profile>

    <profile>
      <!-- 使用aliyun中央库下载plugin -->
      <pluginRepositories>
        <pluginRepository>
          <id>aliyun-central</id>
          <name>aliyun-central</name>
          <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
          <snapshots><enabled>true</enabled></snapshots>
          <releases><enabled>true</enabled></releases>
          <layout>default</layout>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>

  <activeProfiles>
    <activeProfile>downloadSources</activeProfile>
  </activeProfiles>
</settings>

```



##### 【2.3】私服仓库

​		私服是架设在局域网的一种特殊的远程仓库，目的是代理远程仓库及部署第三方构件。有了私服之后，当 Maven 需要下载构件时，直接请求私服，私服上存在则下载到本地仓库；否则，私服请求外部的远程仓库，将构件下载到私服，再提供给本地仓库下载，如图：

![image-20191125155811054](image\image-20191125155811054.png)

在maven的高级应用中我们会搭建自己的私服服务器nexus

### 3、项目生命周期

#### 【1】什么是项目生命周期

​		在Maven出现之前，项目构建的生命周期就已经存在，软件开发人员每天都在对项目进行清理，编译，测试及部署。

#### 【2】maven的3套生命周期

​		Maven有三套相互独立的生命周期，分别是clean、default和site。每个生命周期包含一些阶段，阶段是有顺序的，后面的阶段依赖于前面的阶段。

​	**clean生命周期**：清理项目

 1）pre-clean：执行清理前需要完成的工作
 2）clean：清理上一次构建生成的文件
 3）post-clean：执行清理后需要完成的工作

​	**default生命周期**：构建项目

 1）validate：验证工程是否正确，所有需要的资源是否可用。
 2）compile：编译项目的源代码。
 3）test：使用合适的单元测试框架来测试已编译的源代码。这些测试不需要已打包和布署。
 4）package：把已编译的代码打包成可发布的格式，比如jar。
 5）integration-test：如有需要，将包处理和发布到一个能够进行集成测试的环境。
 6）verify：运行所有检查，验证包是否有效且达到质量标准。
 7）install：把包安装到maven本地仓库，可以被其他工程作为依赖来使用。
 8）deploy：在集成或者发布环境下执行，将最终版本的包拷贝到远程的repository，使得其他的开发者或者工程可以共享。

​	**site生命周期**：建立和发布项目站点

 1）pre-site：生成项目站点之前需要完成的工作
 2）site：生成项目站点文档
 3）post-site：生成项目站点之后需要完成的工作
 4）site-deploy：将项目站点发布到服务器	

## 第三章 maven私服【重点】

### 1、nexus私服搭建

#### 【1】下载nexus

nexus 的官网:http://www.sonatype.com/

下载 Nexus Repository Manager OSS 2.xx 

课前资料中已经下载 “ nexus-2.12.0-01-bundle.zip ” ==》jdk1.8

#### 【2】安装nexus

解压 “ nexus-2.12.0-01-bundle.zip ” 到当前目录

![image-20191126104058589](image\image-20191126104058589.png)

点击进入nexus-2.12.0-01-bundl==>nexus-2.12.0-01

![image-20191126104614832](image\image-20191126104614832.png)

进入nexus-2.12.0-01==>conf目录修改nexus.properties

```properties
#端口，这里默认8081端口换成8085
application-port=8085
#访问地址
application-host=0.0.0.0
nexus-webapp=${bundleBasedir}/nexus
#项目名称
nexus-webapp-context-path=/nexus

# Nexus section
nexus-work=${bundleBasedir}/../sonatype-work/nexus
runtime=${bundleBasedir}/nexus/WEB-INF

```

进入bin目录选择对应系统，我的是win10 64位操作系统

![image-20191126110806909](image\image-20191126110806909.png)

选择console-nexus.bat右键“以管理员身份运行”

![image-20191126111057039](image\image-20191126111057039.png)

启动完成

![image-20191126111204623](image\image-20191126111204623.png)

访问地址： http://localhost:8085/nexus 

![image-20191126111522388](image\image-20191126111522388.png)

```properties
【话外音】
	install-nexus.bat 安装nexus服务到Windows系统中
	start-nexus.bat 启动Windows系统中nexus服务
	stop-nexus,bat  关闭Windows系统中nexus服务
	uninstall-nexus.bat 从Windows系统中移除nexus服务
	以上命令都必须以管理员身份运行
```

#### 【3】nexus控制台简介

##### 【3.1】登陆

![image-20191126113525716](image\image-20191126113525716.png)

默认账号：admin

默认密码：admin123

##### 【3.2】面板

![image-20191126113838833](image\image-20191126113838833.png)

##### 【3.3】仓库

已有主要仓库：

```properties
#central: 
	用来代理 maven 中央仓库中发布版本构件的仓库
#3rd party: 
	无法从公共仓库获得的第三方发布版本的构件仓库
#releases: 
	用来部署管理内部的发布版本构件的宿主类型仓库
#snapshots:
	用来部署管理内部的快照版本构件的宿主类型仓库
	
-------------------------------------------------------------
#apache snapshots: 
	用了代理 apache maven 仓库快照版本的构件仓库
#central M1 shadow: 
	用于提供中央仓库中 M1 格式的发布版本的构件镜像仓库
```

![image-20191126114142793](image\image-20191126114142793.png)



仓库类型：

```properties
#proxy：是远程仓库的代理 
	比如说在 nexus 中配置了一个 central repository 的 proxy，当用户向这个 proxy 请求一个 artifact，这个 proxy 就会先在本地查找，如果找不到的话，就会从远程仓库下载，然后返回给用户，相当于起到一个中转的作用
	
#hosted：是宿主仓库
	用户可以把自己的一些构件，deploy 到 hosted 中，也可以手工上传构件到 hosted 里。比如说 oracle 的驱动程序，在central repository 是获取不到的，就需要手工上传到 hosted 里
	
#group：是仓库组
	在 maven 里没有这个概念，是 nexus 特有的。目的是将上述多个仓库聚合，对用户暴露统一的地址，这样用户就不需要在 pom 中配置多个地址，只要统一配置 group 的地址就可以。右边那个 Repository Path 可以点击进去，看到仓库中artifact列表
	
#virtual：是中央仓库镜像,支持 M1 老版本【不需要关心，用不到】
```

![image-20191126115106795](image\image-20191126115106795.png)

##### 【3.4】修改默认中央仓库

nexus的中央仓库，默认配置的是maven的中央仓库：
	https://repo1.maven.org/maven2/
因为地址在国外，建议配置为阿里云：
	http://maven.aliyun.com/nexus/content/groups/public/

![image-20191126115443326](image\image-20191126115443326.png)



![1583329976509](image\1583329976509.png)

#### 【4】小结

```
主要仓库：
    #central: 
        用来代理 maven 中央仓库中发布版本构件的仓库
    #3rd party: 
        无法从公共仓库获得的第三方发布版本的构件仓库
    #releases: 
        用来部署管理内部的发布版本构件的宿主类型仓库
    #snapshots:
        用来部署管理内部的快照版本构件的宿主类型仓库
        
仓库类型：
    #proxy：是远程仓库的代理 
        比如说在 nexus 中配置了一个 central repository 的 proxy，当用户向这个 proxy 请求一个 artifact，这个 proxy 就会先在本地查找，如果找不到的话，就会从远程仓库下载，然后返回给用户，相当于起到一个中转的作用

    #hosted：是宿主仓库
        用户可以把自己的一些构件，deploy 到 hosted 中，也可以手工上传构件到 hosted 里。比如说 oracle 的驱动程序，在central repository 是获取不到的，就需要手工上传到 hosted 里

    #group：是仓库组
        在 maven 里没有这个概念，是 nexus 特有的。目的是将上述多个仓库聚合，对用户暴露统一的地址，这样用户就不需要在 pom 中配置多个地址，只要统一配置 group 的地址就可以。右边那个 Repository Path 可以点击进去，看到仓库中artifact列表
        
修改默认中央库：
    nexus的中央仓库，默认配置的是maven的中央仓库：
        http://repo1.maven.org/maven2/
    因为地址在国外，建议配置为阿里云：
        http://maven.aliyun.com/nexus/content/groups/public/
	
```



### 2、nexus私服应用【重点】



#### 【1】使用nexus下载资源

私服咱们搭建好了，那他是怎么工作的呢？

![image-20191127171109578](image\image-20191127171109578.png)



##### 【1.1】配置setting文件中

maven的setting配置中删除aliyun的中央仓库，替换成nexus私服服务器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
 
  <!--配置本地仓库地址-->
  <localRepository>F:/maven-repository/repository</localRepository>

  <proxies>
  </proxies>

  <servers>
    <!-- 定义了开发库帐号密码(id与项目POM中的distributionManagement元素id必须一样-->
    <server>
        <id>snapshots</id>
        <username>admin</username>
        <password>admin123</password>
    </server>
    <server>
        <id>releases</id>
        <username>admin</username>
        <password>admin123</password>
    </server>
    <server>
        <id>thirdparty</id>
        <username>admin</username>
        <password>admin123</password>
    </server>
  </servers>


  <mirrors>
    <!-- 使用nexus私服库,而我们搭建的nexus的central代理可以已经被我们修改为aliyun的中央库，所以这里可以不配置，阿里服务-->
    <mirror>
        <id>nexus</id>
        <mirrorOf>*</mirrorOf>
        <name>nexus-mirror</name>
        <url>http://127.0.0.1:8085/nexus/content/groups/public/</url>
    </mirror>
  </mirrors>

  <profiles>
    <!--下载jar源码-->
    <profile>
      <id>downloadSources</id>
      <properties>
        <downloadSources>true</downloadSources>
        <downloadJavadocs>true</downloadJavadocs>
      </properties>
    </profile>

    <profile>
      <id>nexusProfile</id>
      <!--指定JAR下载地址-->
      <repositories>
          <repository>
              <id>public</id>
              <name>public</name>
              <url>http://127.0.0.1:8085/nexus/content/groups/public/</url>
              <snapshots><enabled>true</enabled></snapshots>
              <releases><enabled>true</enabled></releases>
              <layout>default</layout>
          </repository>
      </repositories>
      <!--指定插件下载地址-->
      <pluginRepositories>
        <pluginRepository>
            <id>public</id>
            <name>public</name>
            <url>http://127.0.0.1:8085/nexus/content/groups/public/</url>
            <snapshots><enabled>true</enabled></snapshots>
            <releases><enabled>true</enabled></releases>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>

  <!--激活环境配置-->
  <activeProfiles>
    <activeProfile>downloadSources</activeProfile>
    <activeProfile>nexusProfile</activeProfile>
  </activeProfiles>

</settings>

```

##### 【1.2】清空本地仓库

我的本地仓库F:/maven-repository/repository删除目录下所有文件

##### 【1.3】从新配置IDEA

![image-20191127172753785](image\image-20191127172753785.png)

##### 【1.4】测试结果

点击右侧maven刷新

![image-20191128105901677](image\image-20191128105901677.png)

setting文件中并没有配置aliyun的中央库，但是jar正常下载，说明我们通过nexus服务器访问了aliyun仓库

![image-20191127172922772](image\image-20191127172922772.png)

#### 【2】上传资源到nexus

![image-20191127164525768](image\image-20191127164525768.png)





如上图所示，我们在实际开发过程中，基础的业务层platform-service开发由A员工负责，A员为不同的网关业务端提供同一个版本的platform-service.jar依赖，这个时候A就需要把自己的项目打包成JAR然后上传到nexus服务器上，而其他模块的开发者只需要去nexus服务器下载就好了

新建maven-day01-01A和maven-day01-02B的2个普通jar项目

![image-20191128105722488](image\image-20191128105722488.png)

##### 【2.1】配置项目的pom.xml文件

```xml
  <distributionManagement>
    <!--当前项目版本后缀为：RELEASES的上传目录-->
    <repository>
      <id>releases</id>
      <name>Internal Releases</name>
      <url>http://localhost:8085/nexus/content/repositories/releases/</url>
    </repository>
    <!--当前项目版本后缀为：SNAPSHOT上传目录-->
    <snapshotRepository>
      <id>snapshots</id>
      <name>Internal Snapshots</name>
      <url>http://localhost:8085/nexus/content/repositories/snapshots/</url>
    </snapshotRepository>
  </distributionManagement>
```

##### 【2.2】snapshot仓库上传

maven-day01-01A当前默认版本为:1.0-SNAPSHOT

![image-20191128105956007](image\image-20191128105956007.png)

执行deploy

![image-20191128110236647](image\image-20191128110236647.png)

![image-20191128110105668](image\image-20191128110105668.png)

查看nexus服务器

![image-20191128110346855](image\image-20191128110346855.png)

snapshots仓库中有maven-day01-01A-1.0-SNAPSHOT.jar

##### 【2.3】releases的上传

修改maven-day01-01B当前默认版本为:1.0-RELEASES

![image-20191128110521464](image\image-20191128110521464.png)

执行deploy

![image-20191128110236647](image\image-20191128110236647.png)

![image-20191128110635213](image\image-20191128110635213.png)

查看nexus服务器

![image-20191128110738195](image\image-20191128110738195.png)

releases仓库中是有maven-day01-01A-1.0-RELEASES.jar

##### 【2.4】上传三方JAR

在实际开发中有些三方JAR在中央仓库和本地仓库都没有，那我们就需要手动上传JAR到thirdparty

![image-20191128093638786](image\image-20191128093638786.png)

例如oracle的数据库驱动：

```xml
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc5</artifactId>
    <version>11.2.0.3</version>
    <scope>test</scope>
</dependency>
```

![image-20191128095623807](image\image-20191128095623807.png)

![image-20191128100007103](image\image-20191128100007103.png)

![image-20191128100131391](image\image-20191128100131391.png)

![image-20191128100256855](image\image-20191128100256855.png)

![image-20191128100355818](image\image-20191128100355818.png)

如果还是不能下载，请清理本地缓存！！！！！

![image-20191128101723821](image\image-20191128101723821.png)

#### 【3】小结

```
使用nexus下载：在setting中配置
1、执行生命周期的install
2、没有nexus的时候，在setting配置中央库
3、有nexus，且nexus中指定aliyun中央库，在setting配置私服库
4、有nexus，且nexus中未指定aliyun中央库,在setting配置私服库和aliyun中央库
使用nexus上传：在项目POM文件中配置
1、执行生命周期的deploy
2、需要配置账号
3、需要配置pom.xml
4、上传nexus的位置与当前pom的version配置有关
5、手动上传jar,如果加载不成功，请清理本地缓存
```



## 第四章 maven高级特性【重点】

### 1、依赖管理

#### 【1】依赖范围

指的是当前依赖参与项目构建生命周期的哪些过程

​		例如：

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <!--这里scope标签里面的test就是一个依赖的范围-->
        <scope>test</scope>
    </dependency>
</dependencies>
```

**【分类】**

| 名称            | 域     | 说明                                                         |
| --------------- | ------ | ------------------------------------------------------------ |
| 【默认】compile | 编译域 | 表示当前依赖参与项目整个构建生命周期                         |
| test            | 测试域 | 表示被依赖的项目仅在项目进行测试的时候生效                   |
| provided        | 容器域 | 打包的时候可以不用打包进去，别的容器提供                     |
| runtime         | 运行域 | 本jar不会参与项目的编译，但项目的测试期和运行时期会参与      |
| import          | 导入域 | 使用在< dependencyManagement>中，从其它 pom 导入dependency 配置 |
| 【了解】system  | 系统域 | 系统范围,自定义构件，指定 systemPath                         |

**【阶段有效性】**

| 名称     | 编译是否有效 | 运行是否有效 | 测试是否有效 | 打包是否有效 | 例如       |
| -------- | :----------: | :----------: | :----------: | :----------: | ---------- |
| compile  |      √       |      √       |      √       |      √       | ssm        |
| test     |      ×       |      ×       |      √       |      ×       | junit      |
| provided |      √       |      ×       |      √       |      ×       | sevlet-api |
| runtime  |      ×       |      √       |      √       |      √       | jdbc       |

#### 【2】依赖冲突【重点】

什么是依赖传递？

​		我们在导入spring-context的依赖后，由他传递过来了4个依赖:spring-aop、spring-beans、spring-core、spring-expression

![image-20191126153725221](image\image-20191126153725221.png)

##### 【2.1】可选依赖

###### 【2.1.1】思考

```properties
1、现有项目A、B
2、项目A中导入jstl的依赖
3、项目B依赖项目A，根据依赖传递原则，B中也会有jstl
4、我们怎么在A中配置，使得B中无jstl的依赖呢？
	方案：A项目的jstl设置optional属性，true表示可选，false表示默认依赖
【画外音】
	A:我决定要不要传递给你，我的地方我做主
```

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

###### 【2.1.2】目标

```
使用optional属性,设置可选依赖
```

###### 【2.1.3】实现

第一步：设置maven-day01-01A的pom.xml文件导入jstl依赖

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

第二步：设置maven-day01-02B的pom.xml文件导入maven-day01-01A依赖

```xml
<dependency>
    <groupId>com.itheima.maven</groupId>
    <artifactId>maven-day01-01A</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency
```

第四步 观察maven-day01-02B的依赖

此时jstl依赖传递过来了

![image-20191128111227799](image\image-20191128111227799.png)

第五步：设置maven-day01-01case的pom.xml中jstl的optional

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
    <optional>true</optional>
</dependency>
```

此时jstl未传递

![image-20191128111418693](image\image-20191128111418693.png)

###### 【2.1.4】小结

```
optional属性，true表示可选，false表示默认依赖
控制权：被依赖方
作用：
当别的项目依赖我的时候，我不确定需要把指定JAR传递过去，为了减少冲突的发生，我设置JAR为可选依赖
```

##### 【2.2】依赖排除

###### 【2.2.1】思考

```properties
1、现有项目A、B
2、项目A中导入jstl的依赖
3、项目B依赖项目A，根据依赖传递原则，B中也会有jstl
4、我们怎么在B中配置，使得B中无jstl的依赖呢？
	方案：B项目的jstl设置<exclusions>
【画外音】
	B:你给我就要啊？我的地方我做主
```

###### 【2.2.2】目标

```
使用<exclusions>做依赖排除
```

###### 【2.2.3】实现

第一步：删除maven-day01-01A的jstl的<optional>true</optional>

第二步：配置maven-day01-02B如下

```xml
<dependency>
    <groupId>com.itheima.spring</groupId>
    <artifactId>maven-day01-01case</artifactId>
    <version>1.0-SNAPSHOT</version>
    <exclusions>
        <exclusion>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </exclusion>
    </exclusions>
</dependency
```

第三步：观察maven-day01-02case的依赖

此时依赖关系不存在

![image-20191128113246783](image\image-20191128113246783.png)

###### 【2.2.4】小结

```
<exclusions>做依赖排除
控制方：依赖方
```

#### 【3】传递优先【重点】

##### 【3.1】思考

```properties
在项目中，我们的依赖是很复杂的，相同JAR在复杂依赖的关系中是怎么传递的呢？
1、路径最短者优先原则
2、路径相同先声明优先原则
```

选择pom.xml查看依赖快捷键：ctrl+alt+u

![image-20191128120901157](image\image-20191128120901157.png)

##### 【3.2】路径最短者优先原则

![1583331605012](image\1583331605012.png)

 E依赖了B和C，B依赖A，那么Maven会使用依赖的C版本，因为它的路径是最短的。 

![1583331548128](image\1583331548128.png)

##### 【3.3】路径相同先声明优先原则

![1583331738593](F:\我的课件\maven-day01\01讲义\image\1583331738593.png)

 E依赖了A和C，因为A的配置顺序在前，那么Maven会让E使用依赖的A的版本

![image-20191128120205325](image\image-20191128120205325.png)

### 2、聚合管理

​		我们在平时的开发中，项目往往会被划分为好几个模块，比如common公共模块、system系统模块、log日志模块、reports统计模块、monitor监控模块等等。这时我们肯定会出现这么一个需要，我们需要一次构件多个模块，而不用每个模块都去`mvn clean install`一次，Maven聚合就是用来实现这个需求的。 

#### 【2.1】目标

```
使用maven的聚合管理A,B,C,E项目的clean、install、deploy等操作
```

#### 【2.2】案例

##### 【2.2.1】新建maven-day01-05parent

maven-day01-05parent项目为管理项目，自身不实现任何逻辑，建立好之后直接删除src目录所有内容，结果如下：

![image-20191128131532661](image\image-20191128131532661.png)

##### 【2.2.2】编辑pom.xml

设置maven-day01-05parent的 pom.xml文件中packaging属性为pom，使用modules管理各个模块，完整配置如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.itheima.parent</groupId>
    <artifactId>maven-day01-05parnt</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>../maven-day01-01A</module>
        <module>../maven-day01-02B</module>
        <module>../maven-day01-03C</module>
        <module>../maven-day01-04E</module>
    </modules>
</project>
```

##### 【2.2.3】执行测试

此时我们在maven-day01-05parent的生命周期中执行clean、install、deploy

【clean】

一次性清理了A,B,C,E项目

![image-20191128140344174](image\image-20191128140344174.png)

【install】

一次性插入了A,B,C,E项目

![image-20191128140549387](image\image-20191128140549387.png)

【deploy】

在parent,A,B,C,E项目中增加

```xml
<distributionManagement>
    <!--当前项目版本后缀为：RELEASES的上传目录-->
    <repository>
        <id>releases</id>
        <name>Internal Releases</name>
        <url>http://localhost:8085/nexus/content/repositories/releases/</url>
    </repository>
    <!--当前项目版本后缀为：SNAPSHOT上传目录-->
    <snapshotRepository>
        <id>snapshots</id>
        <name>Internal Snapshots</name>
        <url>http://localhost:8085/nexus/content/repositories/snapshots/</url>
    </snapshotRepository>
</distributionManagement>
```

一次性发布了A,B,C,E项目

![image-20191128141337992](image\image-20191128141337992.png)

#### 【2.3】小结

```
maven的聚合：对项目的生命周期做一次性管理，不需要一个一个的执行
```

### 3、继承管理

​		Maven的继承特性可以抽取重复的配置。在父POM中声明一些配置供子类POM继承，实现"一处声明，多处使用"。同聚合模块一样，继承父工程是一个maven项目，拥有自己的POM文件，packaging类型为pom。由于父工程只是帮助消除重复配置，因此其本身不包含除POM之外的项目文件。 

#### 【3.1】目标

```
使用maven的继承，声明统一版本、统一jar,统一插件
```

#### 【3.2】案例

##### 【3.2.1】新建maven-day01-06parent

maven-day01-06parnt项目为管理项目，自身不实现任何逻辑，建立好之后直接删除src目录所有内容，设置 pom.xml文件中packaging属性为pom，完整配置如下结果如下：

![image-20191128142846984](image\image-20191128142846984.png)

maven-day01-06parent的pom完整配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.itheima.maven</groupId>
    <artifactId>maven-day01-06parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>maven-day01-06dao</module>
    </modules>
    <packaging>pom</packaging>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



##### 【3.2.2】配置子模块

选中“maven-day01-06parent”,右键==>"new"==>Module

![image-20191128143201253](image\image-20191128143201253.png)

![image-20191128143252714](image\image-20191128143252714.png)

![image-20191128143608624](image\image-20191128143608624.png)

maven-day01-06dao的pom完整配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!--继承父亲模块-->
    <parent>
        <artifactId>maven-day01-06parent</artifactId>
        <groupId>com.itheima.maven</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven-day01-06dao</artifactId>
    <version>1.0-SNAPSHOT</version>

    <name>maven-day01-06dao</name>
    <!-- FIXME change it to the project's website -->
    <url>http://www.example.com</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
    </properties>

    <build>
    </build>
</project>

```

查看maven-day01-06dao的jar依赖,我们发现，maven-day01-06dao继承了maven-day01-06parent项目的JAR包

![image-20191128144309143](image\image-20191128144309143.png)

重复上面的操作继续创建maven-day01-06service、maven-day01-06web模块

最终maven-day01-06parent的pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.itheima.maven</groupId>
    <artifactId>maven-day01-06parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <modules>
        <module>maven-day01-06dao</module>
    </modules>

</project>
```

#### 【3.3】小结

```
maven的继承：在maven聚合的基础上，实现统一声明定义JAR
```

### 4、版本管理

#### 【4.1】思考

```
1、使用maven继承之后，咱们可以统一管理项目生命周期，定义统一JAR，但是parent项目不实现业务逻辑，那咱们能不能只声明而不具体应用JAR？
2、是否可以在parent中统一管理版本？
3、除了声明JAR之外我们还能做什么？
```

#### 【4.2】自定义变量

定义方式：

<properties>标签内定义

使用方式：${常量名}

在maven-day01-06parent添加

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
    <!--junit版本-->
    <junit.version>4.11</junit.version>
</properties>
```



![image-20191128150505896](image\image-20191128150505896.png)

junit.version就是我们定义的变量，

#### 【4.3】声明JAR版本引用

定义方式：

<dependencyManagement>标签表示只是声明，而不是具体引用

在maven-day01-06parent添加

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
    <!--junit版本-->
    <junit.version>4.11</junit.version>
    <!--spring版本-->
    <spring.version>5.0.2.RELEASE</spring.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

在maven-day01-06dao中使用

```xml
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
</dependency>
```

此时我们只要写groupId、artifactId而不需要再写version，并且只需要引用需要的JAR，例如：我们只引用了junit4的JAR，而在maven-day01-06parent定义的spring-context没有被引用

![image-20191128151452732](image\image-20191128151452732.png)

#### 【4.4】声明plugin插件版本

声明方式：

<pluginManagement>

在maven-day01-06parent的build节点添加

```xml
<!-- 直接引用部分 -->
    <plugins>
      <!-- maven 指定编译编码和JDK -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.3</version>
        <configuration>
          <source>${java.version}</source>
          <target>${java.version}</target>
          <encoding>${project.build.sourceEncoding}</encoding>
        </configuration>
      </plugin>
    </plugins>
    <pluginManagement>
      <plugins>
        <!-- maven 生成项目源码插件 -->
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-source-plugin</artifactId>
          <version>3.0.1</version>
          <executions>
            <execution>
              <phase>compile</phase>
              <goals>
                <goal>jar</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
        <!-- maven 打包 war插件 -->
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-war-plugin</artifactId>
          <version>2.4</version>
          <configuration>
            <warName>${project.artifactId}</warName>
          </configuration>
        </plugin>
        <!-- tomcat7插件,命令： mvn tomcat7:run -DskipTests -->
        <plugin>
          <groupId>org.apache.tomcat.maven</groupId>
          <artifactId>tomcat7-maven-plugin</artifactId>
          <version>2.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
```

在maven-day01-06parent的build添加

```xml

<plugins>
    <!-- maven 生成项目源码插件 -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-source-plugin</artifactId>
    </plugin>
</plugins>

```

#### 【4.5】小结

```
父工程中做如下定义：
    1、自定义变量
    <properties>中定义
    2、声明jar引用
    <dependencyManagement>中定义
    3、声明plugin插件版本
    <pluginManagement>中定义
子工程：
	可以使用父工程中的所有东西
```



## 第五章  maven分层构建SSM项目【重点】

### 1、开发中的那些事

小作坊式的开发：

![image-20191202154150363](image\image-20191202154150363.png)

前段、后端（web层，service层，dao层）开发是1,2个同事一起写，从前台写到后台，不管什么类型的业务都写到service这一层，使用svn等管理项目，然后一起打包发布一个Tomcat中

前、中、后系统模式下开发：

![image-20191202160614652](image\image-20191202160614652.png)

咱们前、中、后项目分离，业务系统之间的调用会非常复杂，也就是我们会拆很多个项目，我们在开发中可能只负责中间一部分的工作，而且模块可能有的是war项目，有的是jar项目，有的RPC通讯，有的http通讯，这些复杂的开发方式，maven给我们带来极大的方便

### 2、构建分层SSM架构

#### 【1】架构图解

![image-20191202164043370](image\image-20191202164043370.png)

**【maven-day01-parent】父模块**

​		整个项目发布仓库定义、引用版本定义，项目聚合定义

**【maven-day01-web】子模块**

​		继承maven-day01-parent使用发布仓库、引用版本

​		依赖maven-day01-service项目，调用业务

**【maven-day01-service】子模块**

​		继承maven-day01-parent使用发布仓库、引用版本

​		依赖maven-day01-log项目，完成日志采集

​		依赖maven-day01-dao项目，调用数据库访问层

**【maven-day01-dao】子模块**

​		继承maven-day01-parent使用发布仓库、引用版本

**【maven-day01-log】子模块**

​		继承maven-day01-parent使用发布仓库、引用版本

#### 【2】目标

```properties
1、掌握使用maven的继承、聚合构建SSM项目的模块化架构
```

#### 【3】案例

##### 【3.1】创建整体的结构

使用前面章节说到的maven聚合、继承的方式创建整体结构，如下

![image-20191203100817865](image\image-20191203100817865.png)

【细节】

所有的resource要设置资源根目录

![image-20191203095420401](image\image-20191203095420401.png)

##### 【3.2】maven-day01-parent父模块

设置 <packaging>pom</packaging>

使用properties定义jar版本

使用distributionManagement定义JAR上传路径

使用dependencyManagement定义JAR的引用

使用pluginManagement定义plug的引用

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.itheima.project</groupId>
  <artifactId>maven-day01-parent</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <name>maven-day01-parent</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--spring 版本-->
    <spring.version>5.1.11.RELEASE</spring.version>
    <!-- slf4j日志版本 -->
    <slf4j.version>1.7.7</slf4j.version>
    <!-- slf4j2日志版本 -->
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

  <dependencyManagement>
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
  </dependencyManagement>

  <build>
    <pluginManagement>
    <plugins>
      <!-- 声明打包时，不需要web.xml -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>2.4</version>
      </plugin>
      <!-- tomcat7插件,命令： mvn tomcat7:run -DskipTests -->
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
      </plugin>
    </plugins>
    </pluginManagement>
    <plugins>
      <!--编译JDK版本-->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <!--指定JDK版本为1.8-->
          <source>8</source>
          <target>8</target>
        </configuration>
      </plugin>
    </plugins>
    <finalName>maven-day01-smm-annotation</finalName>
  </build>
</project>


```



##### 【3.3】maven-day01-log子模块

###### 【3.3.1】pom文件定义

继承maven-day01-parent

设置<packaging>jar</packaging>

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven-day01-parent</artifactId>
        <groupId>com.itheima.project</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven-day01-log</artifactId>
    <packaging>jar</packaging>

    <name>maven-day01-log</name>

    <dependencies>
        <!--spring 容器依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>

        <!--spring aop依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
        </dependency>

        <!-- log4j2驱动依赖 -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-web</artifactId>
        </dependency>
    </dependencies>

    <build>
    </build>
</project>

```

###### 【3.3.2】cope代码

从maven-day01-smm-annotation拷贝如下内容：

​		com.itheima.project.service下LogInfoService、LogInfoServiceImpl

##### 【3.4】maven-day01-dao子模块

###### 【3.4.1】pom文件定义

继承maven-day01-parent

设置<packaging>jar</packaging>

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven-day01-parent</artifactId>
        <groupId>com.itheima.project</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven-day01-dao</artifactId>
    <packaging>jar</packaging>

    <name>maven-day01-dao</name>

    <dependencies>
        <!--spring 容器依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>

        <!--spring-jdbc依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>

        <!--mybatis依赖-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </dependency>

        <!--mybatis-spring依赖-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
        </dependency>

        <!--druid依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>

        <!-- mysql驱动依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
    </build>
</project>

```

###### 【3.4.2】cope代码

从maven-day01-smm-annotation拷贝如下内容：

​		com.itheima.project.pojo所有内容

​		com.itheima.project.mapper所有内容

​		com.itheima.project.config下的MybatisConfig内容

​		resources目录下的sqlMapper所有内容

##### 【3.5】maven-day01-service子模块

###### 【3.5.1】pom文件定义

继承maven-day01-parent

依赖maven-day01-dao、maven-day01-log

设置<packaging>jar</packaging>

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven-day01-parent</artifactId>
        <groupId>com.itheima.project</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven-day01-service</artifactId>
    <packaging>jar</packaging>

    <name>maven-day01-service</name>

    <dependencies>

        <dependency>
            <groupId>com.itheima.project</groupId>
            <artifactId>maven-day01-log</artifactId>
        </dependency>

        <dependency>
            <groupId>com.itheima.project</groupId>
            <artifactId>maven-day01-dao</artifactId>
        </dependency>

    </dependencies>

    <build>
    </build>
</project>

```

###### 【3.5.2】cope代码

从maven-day01-smm-annotation拷贝如下内容：

​		com.itheima.project.service下CustomerService、CustomerServiceImpl

##### 【3.6】maven-day01-web子模块

###### 【3.6.1】pom文件定义

继承maven-day01-parent

依赖maven-day01-service

设置<packaging>war</packaging>

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven-day01-parent</artifactId>
        <groupId>com.itheima.project</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven-day01-web</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>maven-day01-web</name>

    <dependencies>

        <dependency>
            <groupId>com.itheima.project</groupId>
            <artifactId>maven-day01-service</artifactId>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <scope>provided</scope>
        </dependency>

        <!--spring mvc容器依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>

        <!--jackson依赖包-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <finalName>maven-day01-web</finalName>
        <plugins>
            <!-- tomcat7插件,命令： mvn tomcat7:run -DskipTests -->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <configuration>
                    <uriEncoding>utf-8</uriEncoding>
                    <port>8080</port>
                    <path>/platform</path>
                </configuration>
            </plugin>
            <!-- 声明打包时，不需要web.xml -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

```

###### 【3.6.2】cope代码

从maven-day01-smm-annotation拷贝如下内容：

​		com.itheima.project.web所有内容

​		com.itheima.project.config下ProjectInitConfig、SpringConfig、SpringMvcConfig内容

​		resources下db.properties、log4j2.xml

##### 【3.7】发布测试

###### 【3.7.1】install

![image-20191203101220855](image\image-20191203101220855.png)

![image-20191203101141099](image\image-20191203101141099.png)

![image-20191203101905769](image\image-20191203101905769.png)

###### 【3.7.2】deploy

![image-20191203102302678](image\image-20191203102302678.png)

![image-20191203102326859](image\image-20191203102326859.png)

![image-20191203102507639](image\image-20191203102507639.png)

###### 【3.7.3】tomcat7:run

![image-20191203102628230](image\image-20191203102628230.png)

![image-20191203102700805](image\image-20191203102700805.png)

![image-20191203102747665](image\image-20191203102747665.png)

#### 【4】小结

```
maven-day01-parent：
	设置 <packaging>pom</packaging>
	使用properties定义jar版本
	使用distributionManagement定义JAR上传路径
	使用dependencyManagement定义JAR的引用
	使用pluginManagement定义plug的引用		
	
maven-day01-log： 
 	继承maven-day01-parent
	设置<packaging>jar</packaging>
	
maven-day01-dao:
	继承maven-day01-parent
	设置<packaging>jar</packaging>
	
maven-day01-service:
	继承maven-day01-parent
	依赖maven-day01-dao、maven-day01-log
	设置<packaging>jar</packaging>
	
maven-day01-web
	继承maven-day01-parent
	依赖maven-day01-service
	设置<packaging>war</packaging>
```


