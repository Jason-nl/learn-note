# maven

# 1.maven的介绍

~~~
maven是一款用于项目管理的软件

maven管理项目：
	1.项目的生命周期的管理，编译，测试，打包。。
	2.jar包的管理
	3.插件的管理（jdk，tomcat..）
	4.分模块构建
~~~



# 2.maven的安装



注意：要确保jdk的环境变量配置正确。   JAVA_HOME  和 PATH

~~~
1.解压
2.配置环境变量
3.配置私服和本地仓库
4.cmd中测试安装
~~~

配置maven_home和path环境变量：

![1606526340672](assets/1606526340672.png)

![1606526399185](assets/1606526399185.png)



配置本地仓库:

![1606526552098](assets/1606526552098.png)

配置阿里云私服：

![1606526695834](assets/1606526695834.png)

~~~xml
<mirror>
     <id>nexus-aliyun</id>
     <mirrorOf>*</mirrorOf>
     <name>Nexus aliyun</name>
     <url>http://maven.aliyun.com/nexus/content/groups/public</url>
 </mirror>
~~~



cmd测试

![1606526734574](assets/1606526734574.png)

注意：

~~~
如果出现JAVA类似的错误，表示jdk环境变量有问题。
~~~







# 3.maven的仓库

![1606525288283](assets/1606525288283.png)



# 4.idea中配置maven

2020版本，将idea运行环境托管给maven。

![1606533392028](assets/1606533392028.png)



**注意：idea中配置maven，必须全全局进行配置。（必须在关闭工程的情况下配置maven）**

![1606527078267](assets/1606527078267.png)





![1606527176531](assets/1606527176531.png)



```xml
-DarchetypeCatalog=internal
```



![1606527311320](assets/1606527311320.png)



~~~
-DarchetypeCatalog=internal   -Dfile.encoding=GB2312
~~~



# 5.idea中创建工程

## 5.1 创建javase工程（了解）

第一次，一定要连接网络，下载maven需要的内容。注意，保证网络通畅（打开百度页面）



![1606527486163](assets/1606527486163.png)





![1606527610758](assets/1606527610758.png)



其他直接next即可。



![1606527663751](assets/1606527663751.png)





![1606527803764](assets/1606527803764.png)





javase工程的目录结构

![1606528030235](assets/1606528030235.png)





## 5.2 创建javaweb工程（重点）



注意：

如果某些同学是idea是2020的版本，那么发现出现一些类似jdk，java等等这个问题。

```xml
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
            </configuration>
        </plugin>
       
    </plugins>
</build>
```





maven的web工程约定的目录结构：

![1606529701492](assets/1606529701492.png)



### 5.2.1 骨架创建

![1606529215728](assets/1606529215728.png)



其他步骤，直接下一步即可。



![1606529496099](assets/1606529496099.png)

所以，对于骨架方式创建web工程，需要手动不全目录结构：

![1606529577467](assets/1606529577467.png)

maven的web工程的完整目录结构：

![1606529668490](assets/1606529668490.png)



注意：maven是一款专门管理项目的工具，所以项目的jar必须由maven管理。

而servlet是依赖于servlet-api.jar包的。所以必须由maven引入servlet的依赖。

引入servlet的jar包



~~~xml
 <!-- servlet依赖-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.0.1</version>
      <scope>provided</scope>
    </dependency>
~~~



![1606529898433](assets/1606529898433.png)







### 5.2.2 手动创建

不需要选择骨架

![1606530220511](assets/1606530220511.png)



其他直接下一步即可。

![1606530381117](assets/1606530381117.png)



创建webapp目录：（src/main/webapp）

1.在pom.xml中添加打包方式为war包

![1606530974803](assets/1606530974803.png)

2.

![1606530488467](assets/1606530488467.png)

一定要注意此处webapp目录和webapp目录下的web.xml配置文件的路径。（web.xml默认路径是错误的，需要改正确了）

![1606530614639](assets/1606530614639.png)



引入servlet依赖

```xml
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.0.1</version>
    </dependency>
</dependencies>
```

创建servlet测试即可。





# 6.maven的生命周期

~~~
使用场景：如果资源没有成功的发布到target目录的时候，我们先clean，然后再package即可。
~~~



~~~
maven提供了3套声明周期，互不影响。

1.clean:  clean
2.核心：   编译，测试，打包，安装，部署
3.sit: 生成站点以及报告稳定
~~~

![1606531594535](assets/1606531594535.png)



~~~
clean: 清理target目录
compile:编译，将java文件编译成class文件
test:执行测试,测试类必须以Test结尾。
pageage:打war包。
install:安装到本地仓库，按照groupId+artifactId+version三者合一安装到本地仓库。
~~~

单元测试的依赖：

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```

如果测试时中文乱码：

```xml
   <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.5</version>
				<configuration>
					<forkMode>once</forkMode>
					<argLine>-Dfile.encoding=UTF-8</argLine>
					<includes>
						<include>**/*Test.java</include>
					</includes>
					<excludes>
					</excludes>
				</configuration>
			</plugin>
```





# 7.pom.xml

## 7.1 依赖管理

~~~
管理maven的坐标的网站:https://mvnrepository.com/
~~~



~~~
maven可以通过坐标管理工程的jar包（依赖）。
~~~

坐标：

```xml
<!-- 坐标通过groupId+artfiactId+version 三者合一去仓库中寻找依赖的。-->
<dependency>
    <!-- 依赖所属的组织机构名称 -->
    <groupId>junit</groupId>
    <!-- 依赖所属的项目名-->
    <artifactId>junit</artifactId>
    <!-- 依赖的版本号-->
    <version>4.12</version>
</dependency>
```



依赖引入的技巧：

alt+insert

![1606544518302](assets/1606544518302.png)



如果发现本地仓库有的依赖，但是搜索不到。更新索引

![1606544697505](assets/1606544697505.png)



## 7.2 爆红

maven工程的右边侧边栏爆红（dependencies，plugins），表示下载失败，根据groupId+artifactId+version去本地仓库寻找是否真的下载失败。

![1606544779423](assets/1606544779423.png)





下载失败怎么办？  

1.确保网络通畅

2.将下载失败的内容给删除。（cleanLastUpdated.bat文件）

3.关闭工程重新打开。

​	

cleanLastedUpated.bat文件

![1606545015284](assets/1606545015284.png)



## 7.3 依赖范围（理解）

~~~
依赖范围的作用：更多情况下是为了节省资源。（servlet的api除外）

一般不用考虑，直接从坐标网站拷贝出来的坐标，直接帮我们把依赖范围考虑好了。
~~~



~~~
编译环境：  书写java代码的环境。
测试环境：  test/java里面的环境
运行时环境： tomcat+war包
~~~



![依赖范围](img/clip_image090.jpg)



## 7.4 插件管理

jdk编译插件：

```xml
<!-- java编译插件 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.2</version>
    <configuration>
        <!-- 编译版本 -->
        <source>1.8</source>
        <target>1.8</target>
    </configuration>
</plugin>
```



tomcat插件：

```xml
<!-- tomcat7插件,命令： mvn tomcat7:run -DskipTests -->
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <!-- 解决get乱码问题的-->
        <uriEncoding>utf-8</uriEncoding>
        <!-- 端口号-->
        <port>8080</port>
        <!-- 项目的虚拟项目名-->
        <path>/</path>
    </configuration>
</plugin>
```



![1606547424437](assets/1606547424437.png)





测试时的中文乱码插件：

~~~xml
   <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.5</version>
				<configuration>
					<forkMode>once</forkMode>
					<argLine>-Dfile.encoding=UTF-8</argLine>
					<includes>
						<include>**/*Test.java</include>
					</includes>
					<excludes>
					</excludes>
				</configuration>
			</plugin>
~~~



maven的pom.xml配置模板

![1606547704983](assets/1606547704983.png)



~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

#if (${HAS_PARENT})
    <parent>
        <groupId>${PARENT_GROUP_ID}</groupId>
        <artifactId>${PARENT_ARTIFACT_ID}</artifactId>
        <version>${PARENT_VERSION}</version>
#if (${HAS_RELATIVE_PATH})
        <relativePath>${PARENT_RELATIVE_PATH}</relativePath>
#end
    </parent>

#end
    <groupId>${GROUP_ID}</groupId>
    <artifactId>${ARTIFACT_ID}</artifactId>
    <version>${VERSION}</version>

    ${END}
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
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
				</configuration>
			</plugin>
			 <!-- tomcat7插件,命令： mvn tomcat7:run -DskipTests -->
          <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
              <uriEncoding>utf-8</uriEncoding>
              <port>8080</port>
              <path>/</path>
            </configuration>
          </plugin>
        </plugins>
    </build>
</project>
~~~



## 7.5 pom.xml详解

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

  <!-- pom.xml的版本 -->
  <modelVersion>4.0.0</modelVersion>

  <!-- install命令时，安装groupId+artifactId+version三者合一安装到本地仓库-->
  <!-- 组织机构名，一般是公司域名的倒写-->
  <groupId>com.itheima</groupId>
  <!-- 项目名称-->
  <artifactId>day01_02_javaweb_demo01</artifactId>
  <!-- 项目的版本号-->
  <version>1.0-SNAPSHOT</version>
  <!-- 打包方式，web工程打war包，不写不认打jar包，父工程打pom包-->
  <packaging>war</packaging>

  <!-- 生成文档时的项目名介绍 -->
  <name>day01_02_javaweb_demo01 Maven Webapp</name>
  <!-- 生成文档时的首页介绍 -->
  <url>http://localhost:8080</url>

  <!-- 属性-->
  <properties>
    <!-- 工程的编码方式，utf-8 -->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!-- jdk的编译环境-->
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>


    <!-- maven依赖的统一管理
        1.在<properties>中通过<依赖名称.version> 声明版本号
        2.在坐标中通过<version>${依赖名称.version} 引入依赖的版本
    -->
    <servlet.version>3.0.1</servlet.version>
    <junit.version>4.11</junit.version>
  </properties>

  
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
    <!-- servlet依赖-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>${servlet.version}</version>
      <scope>provided</scope>
    </dependency>

  </dependencies>

  <!-- 项目的构建相关-->
  <build>
    <!-- 最终编译名称-->
    <finalName>day01_02_javaweb_demo01</finalName>
    <pluginManagement><!--  用来锁定插件的版本的，不会真的去引入依赖。一般用在父工程中的配置。 -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>

        <plugin>
          <groupId>org.apache.tomcat.maven</groupId>
          <artifactId>tomcat7-maven-plugin</artifactId>
          <version>2.2</version>
          <configuration>
            <uriEncoding>utf-8</uriEncoding>
            <port>8080</port>
            <path>/</path>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```



# 8.将用户案例改造成maven的web工程

~~~
1.创建maven的web工程，确保maven的web工程目录结构的正确性
2.web下的资源拷贝到maven的src/main/webapp目录下
3.java代码可瓯北到maven的src/main/java目录下
4.配置文件拷贝靠maven的src/main/resources目录下
5.在maven的pom.xml中统一管理项目的依赖即可。
~~~



![1606549972430](assets/1606549972430.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.itheima</groupId>
    <artifactId>day01_04_user_maven</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>




    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

        <!-- 依赖版本的统一管理-->
        <beanUtils.version>1.7.0</beanUtils.version>
        <common.logging.version>1.2</common.logging.version>
    </properties>



    <dependencies>


        <!--beanUtils-->
        <dependency>
            <groupId>commons-beanutils</groupId>
            <artifactId>commons-beanutils</artifactId>
            <version>${beanUtils.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>${common.logging.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-collections</groupId>
            <artifactId>commons-collections</artifactId>
            <version>3.2.1</version>
        </dependency>

        <!--servlet-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>

        <!--jstl-->
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>
        <!--日志-->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.6.1</version>
        </dependency>


    </dependencies>

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
                </configuration>
            </plugin>
            <!-- tomcat7插件,命令： mvn tomcat7:run -DskipTests -->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <uriEncoding>utf-8</uriEncoding>
                    <port>8080</port>
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```