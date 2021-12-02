# 1.介绍

## 1.1.什么是MongoDB ?

MongoDB 是由C++语言编写的，是一个基于分布式文件存储的开源数据库系统。

在高负载的情况下，添加更多的节点，可以保证服务器性能。

MongoDB 旨在为WEB应用提供可扩展的高性能数据存储解决方案。

MongoDB 将数据存储为一个文档，数据结构由键值(key=>value)对组成。MongoDB 文档类似于 JSON 对象。字段值可以包含其他文档，数组及文档数组。

{name:"", age:""}

## 1.2.基本概念

不管我们学习什么数据库都应该学习其中的基础概念，在mongodb中基本的概念包括文档、集合、数据库，下面我们逐个介绍。

我们通过与MySQL对比，来帮助您更容易理解Mongo中的一些概念：

| SQL术语/概念 | MongoDB术语/概念 | 解释/说明                                                    |
| :----------- | :--------------- | :----------------------------------------------------------- |
| database     | database         | 数据库，mongodb中也可以创建多个数据库，简称db                |
| table        | collection       | 集合，对应数据库中的表。集合的key就是“表的名称”              |
| row          | document         | 文档，对应数据库中的一行，一般是JSON风格。一个集合包含多个文档 |
| column       | field            | 字段，对应数据库每一列。一个文档包含多个字段field            |
| index        | index            | 索引                                                         |
| table joins  |                  | 表连接,MongoDB不支持                                         |
| primary key  | primary key      | 主键,MongoDB自动将`_id`字段设置为主键                        |





# 2.安装

本教程中采用docker安装，请确保自己具备docker环境。

## 2.1.安装MongoDB

我们会提供本地MongoDB镜像，如果希望使用最新镜像，也可以到docker-hub拉取。

### 2.1.1.加载mongo镜像

通过下面的命令拉取mongo镜像：

```sh
docker pull mongo
```



**备选**方案：

如果网络不好，也可以使用我们提供的镜像。在课前资料中提供了MongoDB的docker镜像：

 ![image-20200730105540832](assets/image-20200730105540832.png)

 

首先将mongo.tar上传到你的linux虚拟机中，然后**进入mongo.tar所在的目录**，通过下面的命令加载镜像：

```sh
docker load -i mongo.tar
```





### 2.1.2.创建容器并运行

下面我们创建一个docker容器，运行mongo镜像：

```sh
docker run  \
--name ly-mongo \
-e MONGO_INITDB_ROOT_USERNAME=root \
-e MONGO_INITDB_ROOT_PASSWORD=root123 \
-p 27017:27017  \
-v mongo-config:/data/configdb/ \
-v mongo-db:/data/db/ \
--privileged \
-d \
mongo
```

参数说明：

- `--name`：指定容器名称为 ly-mongo
- `-p` : 指定映射端口27017
- `-v` ：指定要挂载的逻辑卷，、
  - `mongo-config`对应mongo的配置目录
  - `mongo-db`对应数据目录
- `-- privileged` : 授权访问挂载卷
- `-d` : 后台运行
- `mongo` : 镜像名称
- `- e` : 环境变量
  - `MONGO_INITDB_ROOT_USERNAME`：默认用户名设置
  - `MONGO_INITDB_ROOT_PASSWORD`：默认密码设置

然后通过命令查看docker运行情况：

```
docker ps -a
```

![image-20200321223333368](assets/image-20200321223333368.png)

### 2.1.3.授权

接下来激活初始化的用户管理员权限。

以管理员身份进入容器：

```
docker exec -it ly-mongo mongo admin
```

然后执行命令.

切换到admin数据库

```
use admin
```

激活：

```
db.auth("root", "root123")
```

![image-20200321223737387](assets/image-20200321223737387.png) 





## 2.2.安装图形化工具

这里我们使用一个叫做mongo-express的GUI工具，官网地址：https://github.com/mongo-express/mongo-express

首先拉取镜像：

```sh
docker pull mongo-express
```

**备选**方案：

如果网络不好，也可以使用我们提供的镜像。在课前资料中提供了`mongo-express` 的docker镜像：

 ![image-20200730105610779](assets/image-20200730105610779.png)



首先将`mongo-express.tar`上传到你的linux虚拟机中，然后**进入`mongo-express`所在的目录**，通过下面的命令加载镜像：

```sh
docker load -i mongo-express.tar 
```



然后创建并运行容器：

```sh
docker run --name ly-express \
 -e "ME_CONFIG_MONGODB_ADMINUSERNAME=root" \
 -e "ME_CONFIG_MONGODB_ADMINPASSWORD=root123" \
 --link ly-mongo:mongo \
 -p 9081:8081 \
 -d \
 mongo-express
```

命令说明：

- `-- link` ：指定关联的容器:
  - `ly-mongo`：容器名称
- `-e` : 环境变量
  - `ME_CONFIG_MONGODB_ADMINUSERNAME`：指定mongo的管理员用户名
  - `ME_CONFIG_MONGODB_ADMINPASSWORD`：指定mongo的管理员密码



在浏览器访问：http://{虚拟机ip}:9081即可，例如我的：

```
http://192.168.206.99:9081
```

效果如图：

![image-20200321171603837](assets/image-20200321171603837.png)



# 3.基本操作

下面来看看MongoDB的基本操作，官方文档：https://docs.mongodb.com/manual/text-search/

包括：

- Database操作
- Collection操作
- Document操作
- 条件查询
- 排序
- 索引
- 聚合

我们可以通过命令行操作，或者通过mongo-express图形化操作。

如果需要命令行操作，先执行下面命令：

```sh
docker exec -it 容器名 bash
```

因为我的容器名是`leyou-mongo`，所以我的命令是这样：

```sh
 docker exec -it ly-mongo bash
```

然后就会进入容器内部，此时输入命令：

```
mongo -u root
```

会提示你输入密码：

![image-20200331144247436](assets/image-20200331144247436.png) 

输入密码后，即可进入。

即可进入mongodb提供的命令行工具。

![image-20200321173711853](assets/image-20200321173711853.png)



## 3.1.数据库操作

数据库操作命令包括：

- `show dbs`: 展示所有的数据库
- `use 数据库名`：进入一个数据库，如果不存在，则创建该数据库
- `db.dropDatabase()`：在进入一个数据库后，输入该命令删除当前数据库

当然，使用图形界面会更直观：

![image-20200321174302363](assets/image-20200321174302363.png)



## 3.2.集合（Collection）操作

集合操作主要是集合的CRUD。

### 3.2.1.创建集合

语法格式：

```
db.createCollection(name, options)
```

参数说明：

- name: 要创建的集合名称
- options: 可选参数, 指定有关内存大小及索引的选项

options 可以是如下参数：

| 字段   | 类型 | 描述                                                         |
| :----- | :--- | :----------------------------------------------------------- |
| capped | 布尔 | （可选）如果为 true，则创建固定集合。固定集合是指有着固定大小的集合，当达到最大值时，它会自动覆盖最早的文档。 **当该值为 true 时，必须指定 size 参数。** |
| size   | 数值 | （可选）为固定集合指定一个最大值，以千字节计（KB）。 **如果 capped 为 true，也需要指定该字段。** |
| max    | 数值 | （可选）指定固定集合中包含文档的最大数量。                   |

在插入文档时，MongoDB 首先检查固定集合的 size 字段，然后检查 max 字段。



例如，我们创建一个名为test的数据库，并在数据库中添加一个叫做users的集合：

![image-20200321174624885](assets/image-20200321174624885.png) 



### 3.2.2.删除集合

基本语法：

```
db.集合名.drop()
```

参数说明：

- 无

**返回值**

如果成功删除选定集合，则 drop() 方法返回 true，否则返回 false。

示例，删除刚刚的users集合：

![image-20200321174817853](assets/image-20200321174817853.png) 



同样，图形界面操作会方便的多：

![image-20200321175011163](assets/image-20200321175011163.png)



### 3.2.3.查看所有的collection

命令：

```
show tables
```





## 3.3.文档操作

集合准备好以后，就可以在集合中添加文档，或者对文档做删、改、查操作。

而mongo中的文档是JSON风格，因此比较容易被大家接收。

### 3.3.1.新增文档

语法：

```
db.COLLECTION_NAME.insert(document)
```

举例，我们新增一个用户：

```
db.users.insertOne({name:"jack", age: 21})
```

这样新增的时候mongo会随机生成id，如果要自己指定id，可以这样：

```
db.users.insertOne({_id:1, name:"Lucy", age: 19})
```

这里的`_id`是文档的id，也就是主键



### 3.3.2.查询文档

#### 1）基本语法

MongoDB 查询数据的语法格式如下：

```
db.COLLECTION_NAME.find(query, projection)
```

- **query** ：可选，使用查询操作符指定查询条件
- **projection** ：可选，使用投影操作符指定返回的键。查询时返回文档中所有键值， 只需省略该参数即可（默认省略）。

如果你需要以易读的方式来读取数据，可以使用 pretty() 方法，语法格式如下：

```
db.COLLECTION_NAME.find().pretty()
```

pretty() 方法以格式化的方式来显示所有文档。

例如：

![image-20200321180620894](assets/image-20200321180620894.png)

这里我们没有指定条件，那就是查询所有。



#### 2）条件语法

其中query的可以支持基本的比较语法，如果你熟悉常规的 SQL 数据，通过下表可以更好的理解 MongoDB 的条件语句查询：

| 操作       | 格式                 | 范例                               | RDBMS中的类似语句    |
| :--------- | :------------------- | :--------------------------------- | :------------------- |
| 等于       | `{key: value}`       | `db.users.find({"name":"jack"})`   | `where name= 'jack'` |
| 小于       | `{key:{$lt:value}}`  | `db.users.find({"age":{$lt:25}})`  | `where age< 25`      |
| 小于或等于 | `{key:{$lte:value}}` | `db.users.find({"age":{$lte:25}})` | `where age<= 25`     |
| 大于       | `{key:{$gt:value}}`  | `db.users.find({"age":{$gt:18}})`  | `where age> 18`      |
| 大于或等于 | `{key:{$gte:value}}` | `db.users.find({"age":{$gte:18}})` | `where age>= 18`     |
| 不等于     | `{key:{$ne:value}}`  | `db.users.find({"age":{$ne:18}})`  | `where age!=18`      |



#### 3）类型判断

mongo中文档虽然是json，但是却包含多种数据类型，常见的如下：

| **类型**    | **数字** | **备注** |
| :---------- | :------- | :------- |
| Double      | 1        |          |
| String      | 2        |          |
| Object      | 3        |          |
| Array       | 4        |          |
| Binary data | 5        |          |
| Object id   | 7        |          |
| Boolean     | 8        |          |
| Date        | 9        |          |
| Null        | 10       |          |

因此，我们还可以在查询的时候，把数据类型作为匹配条件：

```
db.users.find({"name" : {$type : 'string'}})
```







#### 4）AND和OR

MongoDB 的 find() 方法可以传入多个键(key)，每个键(key)以逗号隔开，即常规 SQL 的 AND 条件。

语法格式如下：

```
db.col.find({key1:value1, key2:value2})
```



OR 条件语句使用了关键字 `$or`,语法格式如下：

```
db.col.find(
   {
      $or: [
         {key1: value1}, {key2:value2}
      ]
   }
)
```

`$or`后面的数组中的多个条件之间是or的关系



### 3.3.3.修改文档

update() 方法用于更新已存在的文档。语法格式如下：

```
db.collection.update(
   <query>,
   <update>,
   {
     upsert: <boolean>,
     multi: <boolean>,
     writeConcern: <document>
   }
)
```

**参数说明：**

- **query** : update的查询条件，类似sql update查询内where后面的。
- **update** : update的对象和一些更新的操作符（如$,$inc...）等，也可以理解为sql update查询内set后面的
- **upsert** : 可选，这个参数的意思是，如果不存在update的记录，是否插入objNew,true为插入，默认是false，不插入。
- **multi** : 可选，mongodb 默认是false,只更新找到的第一条记录，如果这个参数为true,就把按条件查出来多条记录全部更新。
- **writeConcern** :可选，抛出异常的级别。



示例，我们把之前的用户的age字段从21修改为24：

![image-20200321181124446](assets/image-20200321181124446.png)

这里我们指定的更新条件为{}，就是代表任意文档，那么集合中的第一个文档会被修改。



### 3.3.4.删除文档

remove() 方法的基本语法格式如下所示：

```
db.collection.remove(
   <query>,
   <justOne>
)
```

如果你的 MongoDB 是 2.6 版本以后的，语法格式如下：

```
db.collection.remove(
   <query>,
   {
     justOne: <boolean>,
     writeConcern: <document>
   }
)
```

**参数说明：**

- **query** :（可选）删除的文档的条件。
- **justOne** : （可选）如果设为 true 或 1，则只删除一个文档，如果不设置该参数，或使用默认值 false，则删除所有匹配条件的文档。
- **writeConcern** :（可选）抛出异常的级别。



示例：

![image-20200321181240788](assets/image-20200321181240788.png) 

这里删除的匹配条件为{}，那么集合中的所有文档会被删除。



### 3.3.5.图形化界面

通用，如果在图形化界面操作会非常简单。

#### 新增：

![image-20200321181633208](assets/image-20200321181633208.png)

然后弹出的窗口填写文档：

![image-20200321181648560](assets/image-20200321181648560.png)



#### 查看

输入查询条件，点击Find按钮即可：

![image-20200321181843195](assets/image-20200321181843195.png)



#### 修改：

点击上一步中查询到的文档，即可进入编辑模式：

![image-20200321181940103](assets/image-20200321181940103.png)



## 3.4.排序

sort()方法基本语法如下所示：

```
db.COLLECTION_NAME.find().sort({KEY:1})
```

- key：排序的字段
- 1：这里可以是1或-1，分别代表升序或降序

例如，我们对年龄升序：

```
db.users.find().sort({age: 1})
```



## 3.5.索引

索引通常能够极大的提高查询的效率，如果没有索引，MongoDB在读取数据时必须扫描集合中的每个文件并选取那些符合查询条件的记录。

这种扫描全集合的查询效率是非常低的，特别在处理大量的数据时，查询可以要花费几十秒甚至几分钟，这对网站的性能是非常致命的。

索引是特殊的数据结构，索引存储在一个易于遍历读取的数据集合中，索引是对数据库表中一列或多列的值进行排序的一种结构

MongoDB使用 createIndex() 方法来创建索引。

> 注意在 3.0.0 版本前创建索引方法为 db.collection.ensureIndex()，之后的版本使用了 db.collection.createIndex() 方法，ensureIndex() 还能用，但只是 createIndex() 的别名。

### 3.5.1.语法

createIndex()方法基本语法格式如下所示：

```
db.collection_name.createIndex(keys, options)
```

参数说明：

- keys：是排序的字段，是键值对格式，键是字段名，值是1或-1
- options：索引选项

例如：

```
db.col.createIndex({"title":1})
```

语法中 Key 值为你要创建的索引字段，1 为指定按升序创建索引，如果你想按降序来创建索引指定为 -1 即可。



createIndex() 方法中你也可以设置使用多个字段创建索引（关系型数据库中称作复合索引）。

```
>db.col.createIndex({"title":1,"description":-1})
```



createIndex() 接收可选参数options，可选参数列表如下：

| Parameter          | Type          | Description                                                  |
| :----------------- | :------------ | :----------------------------------------------------------- |
| background         | Boolean       | 建索引过程会阻塞其它数据库操作，background可指定以后台方式创建索引，即增加 "background" 可选参数。 "background" 默认值为**false**。 |
| unique             | Boolean       | 建立的索引是否唯一。指定为true创建唯一索引。默认值为**false**. |
| name               | string        | 索引的名称。如果未指定，MongoDB的通过连接索引的字段名和排序顺序生成一个索引名称。 |
| dropDups           | Boolean       | **3.0+版本已废弃。**在建立唯一索引时是否删除重复记录,指定 true 创建唯一索引。默认值为 **false**. |
| sparse             | Boolean       | 对文档中不存在的字段数据不启用索引；这个参数需要特别注意，如果设置为true的话，在索引字段中不会查询出不包含对应字段的文档.。默认值为 **false**. |
| expireAfterSeconds | integer       | 指定一个以秒为单位的数值，完成 TTL设定，设定集合的生存时间。 |
| v                  | index version | 索引的版本号。默认的索引版本取决于mongod创建索引时运行的版本。 |
| weights            | document      | 索引权重值，数值在 1 到 99,999 之间，表示该索引相对于其他索引字段的得分权重。 |
| default_language   | string        | 对于文本索引，该参数决定了停用词及词干和词器的规则的列表。 默认为英语 |
| language_override  | string        | 对于文本索引，该参数指定了包含在文档中的字段名，语言覆盖默认的language，默认值为 language. |





### 3.5.2.实例

我们可以在集合中添加多条数据：

```
db.users.insert({name:"rose", age: 18})
db.users.insert({name:"Lucy", age: 19})
```

然后创建索引：

```
 db.users.createIndex({name: 1},{background: true})
```

![image-20200321202428166](assets/image-20200321202428166.png)





# 4.Java客户端

接下来，我们一起学习如何通过java代码操作MongoDB。

## 4.1.SpringDataMongoDB

Spring已经对MongoDB客户端整合，并作为SpringData中的一个子模块。

![image-20200321204531248](assets/image-20200321204531248.png)

与SpringData中的其它子模块一样，其中的各种实现都是基于SpringDataCommon中的一些接口和注解。例如：

- `Repository<T,ID>`：定义基本的CRUD方法，T是实体类类型，ID是其中的id类型
- `@Document`：定义数据库信息，如mongo中的集合名
- `@Id`：主键

熟悉SpringData的同学对于其中的玩法应该非常熟练了，这里不再赘述



## 4.2.创建Demo工程

这里选择Spring的脚手架工具：

![image-20200321205305057](assets/image-20200321205305057.png)

项目信息：

![image-20200321205320970](assets/image-20200321205320970.png)

相关依赖：

![image-20200321205330738](assets/image-20200321205330738.png)

这里选择了SpringBoot中对于MongoDB的支持。

完整的pom：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.12.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>cn.itcast.demo</groupId>
    <artifactId>mongo-demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>mongo-demo</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
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

</project>
```

## 4.3.环境准备

现在， 我们先准备一些简单运行环境。

### 4.3.1.实体类

我们准备一个实体类，作为存入MongoDB的数据模型：

```java
package cn.itcast.mongo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    private Long id;
    private String name;
    private Integer age;
}
```

实体类相关注解：

- `@Document`：声明实体类相关的集合
  - `users`：就是集合名称
- `@Id`：声明将来作为`_id`的字段



### 4.3.2.Repository

SpringData中有一个Repository接口，代表了所有数据操作的顶级接口。

![image-20200321210242022](assets/image-20200321210242022.png)

它下面还包含很多儿子，定义了各种CRUD的方法：

![image-20200321210420247](assets/image-20200321210420247.png) 



在MongoDB中，也有一个对应的接口：

![image-20200321210512886](assets/image-20200321210512886.png) 



因此，只要我们的定义的接口继承了MongoRepository，就会包含这些CRUD的方法了。现在，我们定义一个Repository。

在`cn.itcast.mongo.repository`中定义一个`UserRepository`接口：

```java

public interface UserRepository extends MongoRepository<User, Long> {
}
```

我们继承MongoRepository的时候指定了两个泛型：

- User：实体类类型
- Long：其中的ID类型



### 4.3.3.新建用户

为了保证安全，我们不把管理员root放到代码中，而是生成一个新的用户。

通过命令行进入docker容器：

```sh
docker exec -it leyou-mongo bash
```

然后就会进入容器内部，此时输入命令：

```
mongo -u root
```

输入密码后进入mongo控制台

新建一个仓库carts，**必须**进入carts库：

```
use carts
```

创建新用户：

```json
db.createUser({ user: 'leyou', pwd: '123', roles: [ { role: "readWrite", db: "carts" } ] });
```

我们创建一个新用户：

- 用户名：leyou
- 密码：123
- 权限：readWrite（读和写）
- 可访问数据库：carts



### 4.3.4.配置mongo地址

刚刚创建了新用户，我们就可以在项目中，使用新建立的用户来连接MongoDB

在项目的application.yml文件中指定mongo的地址：

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://leyou:123@192.168.206.99/carts
```

uri地址的语法：

- `mongodb`:固定写法
- `leyou:123`：用户名:密码
- `192.168.206.99`：mongodb所在的虚拟机地址
- `carts`：database的名称



## 4.4.CRUD

来看下基本的CRUD代码

创建一个单元测试：

```java
package cn.itcast.mongo;

import cn.itcast.mongo.pojo.User;
import cn.itcast.mongo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDemoApplicationTests {

    @Autowired
    private UserRepository userRepository;
}

```



### 4.4.1.新增

一次增一个文档：

```java
@Test
public void testSave() {
    User user = new User(1L, "Amy", 16);
    userRepository.save(user);
}
```



批量新增文档：

```java
@Test
public void saveAll() {
    List<User> list = new ArrayList<>();
    list.add(new User(1L, "Amy", 16));
    list.add(new User(2L, "Lucy", 21));
    list.add(new User(3L, "Jack", 20));
    list.add(new User(4L, "Tom", 25));
    list.add(new User(5L, "John", 18));

    userRepository.saveAll(list);
}
```



### 4.4.2.更新

```java
@Test
public void testUpdate(){
    // id存在，则修改
    User user = new User(1L, "Amy", 15);

    userRepository.save(user);
}
```



### 4.4.3.删除

```java
@Test
public void testDelete(){
    userRepository.deleteById(2L);
}
```



### 4.4.4.根据id查询

```java
@Test
public void testFindById(){
    Optional<User> result = userRepository.findById(1L);

    result.ifPresent(System.out::println);
}
```



### 4.4.5.查询所有

```java
@Test
public void testFindAll(){
    List<User> list = userRepository.findAll();
    list.forEach(System.out::println);
}
```



### 4.4.6.带分页和排序

```java
@Test
public void testFindByPage(){
    // 查询，PageRequest就是分页条件对象，
    Page<User> info = userRepository.findAll(PageRequest.of(0, 3, Sort.Direction.DESC, "age"));
    // 总页数
    int totalPages = info.getTotalPages();
    System.out.println("totalPages = " + totalPages);
    // 总条数
    long totalElements = info.getTotalElements();
    System.out.println("totalElements = " + totalElements);
    // 当前页结果集合
    List<User> list = info.getContent();
    list.forEach(System.out::println);
}
```



### 4.4.7.自定义查询

我们在UserRepository接口中，按照固定单词创建一个新的方法，例如：

```java
List<User> findByAgeLessThan(Integer age);
```



SpringData会根据方法中单词的含义，自动生成对应的查询语句，完成查询。

我们无需实现任何功能，直接即可使用：

```java
@Test
public void testFindByAge(){
    List<User> list = userRepository.findByAgeLessThan(21);
    list.forEach(System.out::println);
}
```



自定义方法中使用的短语包括下面这些，官网介绍：https://docs.spring.io/spring-data/mongodb/docs/2.2.6.RELEASE/reference/html/#mongo.repositories

| Keyword                              | Sample                                                       | Logical result                                               |
| :----------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| `After`                              | `findByBirthdateAfter(Date date)`                            | `{"birthdate" : {"$gt" : date}}`                             |
| `GreaterThan`                        | `findByAgeGreaterThan(int age)`                              | `{"age" : {"$gt" : age}}`                                    |
| `GreaterThanEqual`                   | `findByAgeGreaterThanEqual(int age)`                         | `{"age" : {"$gte" : age}}`                                   |
| `Before`                             | `findByBirthdateBefore(Date date)`                           | `{"birthdate" : {"$lt" : date}}`                             |
| `LessThan`                           | `findByAgeLessThan(int age)`                                 | `{"age" : {"$lt" : age}}`                                    |
| `LessThanEqual`                      | `findByAgeLessThanEqual(int age)`                            | `{"age" : {"$lte" : age}}`                                   |
| `Between`                            | `findByAgeBetween(int from, int to)` `findByAgeBetween(Range range)` | `{"age" : {"$gt" : from, "$lt" : to}} lower / upper bounds ($gt / $gte & $lt/ $lte) according to Range` |
| `In`                                 | `findByAgeIn(Collection ages)`                               | `{"age" : {"$in" : [ages…]}}`                                |
| `NotIn`                              | `findByAgeNotIn(Collection ages)`                            | `{"age" : {"$nin" : [ages…]}}`                               |
| `IsNotNull`, `NotNull`               | `findByFirstnameNotNull()`                                   | `{"firstname" : {"$ne" : null}}`                             |
| `IsNull`, `Null`                     | `findByFirstnameNull()`                                      | `{"firstname" : null}`                                       |
| `Like`, `StartingWith`, `EndingWith` | `findByFirstnameLike(String name)`                           | `{"firstname" : name} (name as regex)`                       |
| `NotLike`, `IsNotLike`               | `findByFirstnameNotLike(String name)`                        | `{"firstname" : { "$not" : name }} (name as regex)`          |
| `Containing` on String               | `findByFirstnameContaining(String name)`                     | `{"firstname" : name} (name as regex)`                       |
| `NotContaining` on String            | `findByFirstnameNotContaining(String name)`                  | `{"firstname" : { "$not" : name}} (name as regex)`           |
| `Containing` on Collection           | `findByAddressesContaining(Address address)`                 | `{"addresses" : { "$in" : address}}`                         |
| `NotContaining` on Collection        | `findByAddressesNotContaining(Address address)`              | `{"addresses" : { "$not" : { "$in" : address}}}`             |
| `Regex`                              | `findByFirstnameRegex(String firstname)`                     | `{"firstname" : {"$regex" : firstname }}`                    |
| `(No keyword)`                       | `findByFirstname(String name)`                               | `{"firstname" : name}`                                       |
| `Not`                                | `findByFirstnameNot(String name)`                            | `{"firstname" : {"$ne" : name}}`                             |
| `Near`                               | `findByLocationNear(Point point)`                            | `{"location" : {"$near" : [x,y]}}`                           |
| `Near`                               | `findByLocationNear(Point point, Distance max)`              | `{"location" : {"$near" : [x,y], "$maxDistance" : max}}`     |
| `Near`                               | `findByLocationNear(Point point, Distance min, Distance max)` | `{"location" : {"$near" : [x,y], "$minDistance" : min, "$maxDistance" : max}}` |
| `Within`                             | `findByLocationWithin(Circle circle)`                        | `{"location" : {"$geoWithin" : {"$center" : [ [x, y], distance]}}}` |
| `Within`                             | `findByLocationWithin(Box box)`                              | `{"location" : {"$geoWithin" : {"$box" : [ [x1, y1], x2, y2]}}}` |
| `IsTrue`, `True`                     | `findByActiveIsTrue()`                                       | `{"active" : true}`                                          |
| `IsFalse`, `False`                   | `findByActiveIsFalse()`                                      | `{"active" : false}`                                         |
| `Exists`                             | `findByLocationExists(boolean exists)`                       | `{"location" : {"$exists" : exists }}`                       |









