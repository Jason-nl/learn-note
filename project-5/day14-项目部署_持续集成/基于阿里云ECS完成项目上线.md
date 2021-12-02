# 基于阿里云ECS服务器实战部署

## 1 ECS服务器准备

### 1.1 ECS服务器购买

购买地址: [阿里云ECS](https://ecs-buy.aliyun.com/wizard?spm=5176.13329450.0.0.42264df5YvIwaZ&accounttraceid=b4bbaacd3dcc4d01b91a78338c04d06ehymz#/postpay/cn-shanghai)

1 选择配置

![image-20210403160340872](assets/image-20210403160340872.png)

2 选择服务和对应的操作系统

![image-20210502162922255](assets/image-20210502162922255.png)

3 选择网络带宽 5M

默认5m即可，当然想更快可以设置大一些，但流量是单独收费的

![image-20210502162940451](assets/image-20210502162940451.png)

4 直接点击确认订单

![image-20210403160806200](assets/image-20210403160806200.png)

5 点击创建实例

![image-20210403161009743](assets/image-20210403161009743.png)

<img src="assets/image-20210403161041703.png" alt="image-20210403161041703" style="zoom:50%;" />

点击管理控制台进入服务器管理界面如下: 

![image-20210403161137366](assets/image-20210403161137366.png)

其中:

公网IP: 47.103.2.34.130

私网IP: 172.20.170.76

收到短信: 发送服务器的 密码

重置密码:

![image-20210403161550152](assets/image-20210403161550152.png)

<img src="assets/image-20210403161713527.png" alt="image-20210403161713527" style="zoom:67%;" />

接收短信后,立即重启生效.

### 1.2 客户端工具连接

推荐使用 finalshell 连接.

1 打开 finalshell 创建 ssh 连接

<img src="assets/image-20210403161945737.png" alt="image-20210403161945737" style="zoom:67%;" />

2 输入 阿里云的  用户名(root) 和 设置的新密码

<img src="assets/image-20210403162116660.png" alt="image-20210403162116660" style="zoom:67%;" />

<img src="assets/image-20210403162150013.png" alt="image-20210403162150013" style="zoom: 50%;" />

<img src="assets/image-20210403162412058.png" alt="image-20210403162412058" style="zoom:80%;" />

### 1.3 安全组设置

在云服务器中，只有配置了安全规则的端口才允许被外界访问

一般默认   开启:    80 (http)   443 （https）  22 (ssh远程连接)  3389 (windows远程连接)

![image-20210502163125902](assets/image-20210502163125902.png)

![image-20210502163310344](assets/image-20210502163310344.png)



那如果你安装了mysql 端口是3306  ，那么外界是无法直接访问到的，需要配置一下规则



在入方向中配置安全规则:  ( 用户  访问 -->  阿里云)

![image-20210502163458317](assets/image-20210502163458317.png)

如果配置端口范围:   3306/3306  那就是允许3306端口访问



但是我们的软件很多， 可以通过 `1/65535` 范围 来开放所有的端口访问（不安全，学习阶段这么搞）



## 2  基础环境配置

### 2.1 配置docker环境

（1）yum 包更新到最新

```
sudo yum update -y
```

（2）安装需要的软件包， yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的

```shell
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```

（3）设置yum源为阿里云

```shell
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

（4）安装docker

```shell
sudo yum -y install docker-ce
```

（5）安装后查看docker版本

```shell
docker -v
```

（6）启动docker

```properties
systemctl start docker

#设置开机自启
systemctl enable docker
```

（7）阿里云镜像加速

阿里云开设了一个[容器开发平台](https://www.aliyun.com/product/acr?spm=5176.19720258.J_8058803260.326.57a22c4aJHmwq7)

需要注册一个属于自己的阿里云账户，登录后进入管理中心

<img src="assets/image-20210403231903208.png" alt="image-20210403231903208" style="zoom:50%;" />

针对Docker客户端版本大于 3.2.10.0 的用户

您可以通过修改daemon配置文件[/etc/docker/daemon.json](/etc/docker/daemon.json)来使用加速器

```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://hf23ud62.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

观察镜像是否生效：

```shell
docker info
```

### 2.2 配置jdk环境

云服务可以直接使用下面命令 一键安装jdk1.8

```
yum install java-1.8.0-openjdk* -y
```

### 2.3 配置maven环境

1. 下载安装包

   下载地址： https://maven.apache.org/download.cgi

2. 解压安装包

   ```sh
   mkdir -p /usr/local/maven
   
   
   cd /usr/local/maven
   
   # 将压缩包上传至 /usr/local/maven下 解压
   unzip -o apache-maven-3.3.9.zip
   
   # 不支持unzip
   yum install -y unzip
   ```

3. 配置

   环境变量配置

   ```sh
   vi /etc/profile
   ```

   增加：

   ```sh
   export MAVEN_HOME=/usr/local/maven/apache-maven-3.3.9
   export PATH=$PATH:$MAVEN_HOME/bin
   ```

   如果权限不够，则需要增加当前目录的权限

   ```shell
   chmod 777 /usr/local/maven/apache-maven-3.3.9/bin/mvn
   ```

   

   修改镜像仓库配置：

   ```sh
   # 修改maven镜像中心为 阿里云镜像中心
   vi /usr/local/maven/apache-maven-3.3.9/conf/settings.xml
   
   
   # 让配置生效
   source /etc/profile
   
   # 测试是否安装成功
   mvn -v
   ```




### 2.4 配置git环境

```
# 安装git客户端
yum -y install git 
```





## 3. 准备持续集成软件jenkins

### 3.1 Jenkins环境搭建

1. 采用YUM方式安装

   加入jenkins安装源：

   ```sh
   sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo --no-check-certificate
   
   sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
   ```

   执行yum命令安装：

   ```sh
   yum -y install jenkins
   ```

2. **采用RPM安装包方式（采用）**

   [Jenkins安装包下载地址](https://pkg.jenkins.io/redhat-stable/)

   **可以导入资料中jenkins RPM安装包：**

   ```sh
   wget https://pkg.jenkins.io/redhat-stable/jenkins-2.249-1.1.noarch.rpm
   ```

   执行安装：

   ```sh
   rpm -ivh jenkins-2.249-1.1.noarch.rpm
   ```

3. 配置：

   修改配置文件：

   ```sh
   vi /etc/sysconfig/jenkins
   ```

   修改内容：

   ```sh
   # 修改为对应的目标用户， 这里使用的是root
   $JENKINS_USER="root"
   # 服务监听端口
   JENKINS_PORT="16060"
   ```

   目录权限：

   ```sh
   chown -R root:root /var/lib/jenkins
   chown -R root:root /var/cache/jenkins
   chown -R root:root /var/log/jenkins
   ```

   重启：

   ```sh
   systemctl restart jenkins
   ```

   管理后台初始化设置

   http://阿里云外网IP地址:16060/
   
   

   需要输入管理密码， 在以下位置查看：
   
   ```sh
   cat /var/lib/jenkins/secrets/initialAdminPassword
   ```

   ![1569564399216](assets/1569564399216.png)

   按默认设置，把建议的插件都安装上

   ![1569564606846](assets/1569564606846.png)
   
   这一步等待时间较长， 安装完成之后， 创建管理员用户：

   ![1569564966999](assets/1569564966999.png)

配置访问地址：

![1569564989527](assets/1569564989527.png)



配置完成之后， 会进行重启， 之后可以看到管理后台：

![1569565238541](assets/1569565238541.png)

### 3.2  Jenkins插件安装

在实现持续集成之前， 需要确保以下插件安装成功。

- `Maven Integration plugin`： Maven 集成管理插件。**(必装)**
- `Docker plugin`： Docker集成插件。
- `GitLab Plugin`： GitLab集成插件。
- `Git Plugin`： Git 集成插件。**（必装）**
- `Publish Over SSH`：远程文件发布插件。**( 可选 )**
- `SSH`: 远程脚本执行插件。**( 可选 )**

安装方法：

1. 进入【系统管理】-【插件管理】

2. 点击标签页的【可选插件】

   在过滤框中搜索插件名称

   ![1569742624798](assets/1569742624798.png)

3. 勾选插件， 点击直接安装即可。

>**注意，如果没有安装按钮，需要更改配置**
>
>在安装插件的高级配置中，修改升级站点的连接为：http://updates.jenkins.io/update-center.json   保存
>
>![1603521604783](assets/1603521604783.png)
>
>![1603521622211](assets/1603521622211.png)

4. 安装完毕后重启jenkins

```
systemctl restart jenkins
```



### 3.3 jenkins全局配置

1. 进入【系统管理】--> 【全局工具配置】

   ![1603200359461](assets\1603200359461.png)

2. MAVEN配置全局设置

   ![1603200435502](assets\1603200435502.png)

3. 指定JDK配置

   不用指定， 前面已安装



4. 指定MAVEN 目录

   点击新增maven **配置name:** maven  **配置maven地址:**  /usr/local/maven/apache-maven-3.3.9

   ![1603200472287](assets\1603200472287.png)

   

   

4. 设置远程应用服务主机**（不涉及远程服务器构建，可不做）**

   添加凭证：

   ![1603735746602](assets\1603735746602.png)

   新增凭证，输入用户名和密码保存即可

   ![1603735794051](assets\1603735794051.png)

   

   进入【系统管理】-【系统设置】

   ![1603200611899](assets\1603200611899.png)

   输入主机名称和登陆信息， 点击【check connections】验证， 如果成功， 会显示“Successfull connection”。

   ![1603735668415](assets\1603735668415.png)





## 4 docker-compose安装依赖软件

黑马头条涉及前端后端服务众多，设备性能有限 所以我们简化下部署步骤， 基于docker + docker-compose方式快速部署，先来了解下黑马头条的部署架构

![1613375860330](assets/1613375860330.png)

1. nginx作为接入层 所有请求全部通过nginx进入 (部署在100服务 端口80)
2. 前端工程app、admin、wemedia全部部署在nginx中
3. nginx通过反向代理将对微服务的请求，代理到网关
4. 网关根据路由规则，将请求转发到下面的微服务
5. 所有微服务都会注册到nacos注册中心
6. 所有微服务都会将配置存储到nacos中进行统一配置
7. 网关服务及所有微服务部署在 100  服务器   jenkins    微服务
8. 所有依赖的软件部署在    131   服务器

### 4.1 相关软件部署

MAC下或者Windows下的Docker自带Compose功能，无需安装。



Linux下需要通过命令安装：

```sh
# 安装
curl -L https://github.com/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
# 如果下载慢，可以上传资料中的 docker-compose 文件到 /usr/local/bin/

# 修改权限
chmod +x /usr/local/bin/docker-compose

ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
```



**黑马头条相关软件 一键脚本**

```yaml
# 涉及软件 容器启动脚本
# 将 当前文件夹内容拷贝到有docker的虚拟机
# 通过docker命令即可启动所有软件
version: '3'
services:
  mysql:
    image: mysql:5.7
    ports:
      - "3306:3306"
    volumes:
      - "/root/mysql/conf:/etc/mysql/conf.d"
      - "/root/mysql/logs:/logs"
      - "/root/mysql/data:/var/lib/mysql"
    environment:
      - MYSQL_ROOT_PASSWORD=root
    restart: always
  nacos:
    image: nacos/nacos-server:1.3.2
    ports:
      - "8848:8848"
    restart: always
    environment:
      - MODE=standalone
      - JVM_XMS=256m
      - JVM_XMX=256m
      - JVM_XMN=128m
      - SPRING_DATASOURCE_PLATFORM=mysql
      - MYSQL_SERVICE_HOST=mysql
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_USER=root
      - MYSQL_SERVICE_PASSWORD=root
      - MYSQL_SERVICE_DB_NAME=nacos_config
      - NACOS_SERVER_IP=47.101.211.236
    depends_on:
      - mysql
  seata:
    image: seataio/seata-server:1.3.0
    ports:
      - "8091:8091"
    environment:
      - "SEATA_IP=47.101.211.236"
    restart: always
  zookeeper:
    image: zookeeper:3.4.14
    restart: always
    expose:
      - 2181
  kafka:
    image: wurstmeister/kafka:2.12-2.3.1
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 47.101.211.236
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://47.101.211.236:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_HEAP_OPTS: "-Xmx256M -Xms256M"
    ports:
      - "9092:9092"
    depends_on:
      - zookeeper
  xxljob:
    image: xuxueli/xxl-job-admin:2.2.0
    volumes:
      - "/tmp:/data/applogs"
    environment:
      PARAMS: "--spring.datasource.url=jdbc:mysql://47.101.211.236:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai --spring.datasource.username=root --spring.datasource.password=root"
    ports:
      - "8888:8080"
    depends_on:
      - mysql
  reids:
    image: redis
    ports:
      - "6379:6379"
  mongo:
    image: mongo:4.2.5
    ports:
      - "27017:27017"
  elasticsearch:
    image: elasticsearch:7.4.2
    ports:
      - "9200:9200"
      - "9300:9300"
    environment:
      - "discovery.type=single-node"
      - "ES_JAVA_OPTS=-Xms256m -Xmx256m"
    volumes:
      - "/usr/share/elasticsearch/plugins:/usr/share/elasticsearch/plugins"
  kibana:
    image: kibana:7.4.2
    links:
      - elasticsearch
    environment:
      - "ELASTICSEARCH_URL=http://elasticsearch:9200"
    ports:
      - "5601:5601"
    depends_on:
      - elasticsearch
```

**使用步骤**

1. 运行所有容器：

```
# 运行
docker-compose up -d
# 停止
docker-compose stop
# 停止并删除容器
docker-compose down
# 查看日志
docker-compose logs -f [service...]
# 查看命令
docker-compose --help
```



### 4.2 相关软件配置

导入sql语句

```
导入微服务相关数据库

导入xxljob使用数据库

导入nacos配置中心数据库

导入seata分布式事务数据库（不用）
```

安装es 中文ik分词器

```
把资料中的 elasticsearch-analysis-ik-7.4.2.zip 上传到服务器上,放到对应目录（plugins）解压

#切换目录
cd /usr/share/elasticsearch/plugins
#新建目录
mkdir analysis-ik
cd analysis-ik
#root根目录中拷贝文件
mv elasticsearch-analysis-ik-7.4.2.zip /usr/share/elasticsearch/plugins/analysis-ik
#解压文件
cd /usr/share/elasticsearch/plugins/analysis-ik
unzip elasticsearch-analysis-ik-7.4.2.zip
```

创建es索引库

```
PUT app_info_article
{
    "mappings":{
        "properties":{
            "id":{
                "type":"long"
            },
            "publishTime":{
                "type":"date"
            },
            "layout":{
                "type":"integer"
            },
            "images":{
                "type":"keyword"
            },
            "authorId": {
          		"type": "long"
       		},
          "title":{
            "type":"text",
            "analyzer":"ik_smart"
          }
        }
    }
}
```

### 4.3 实现nacos统一配置

将application.yml中的配置文件内容复制到nacos中

![1613379700439](assets/1613379700439.png)



配置文件是可导入导出的，导入资料中的nacos_config配置压缩包

然后根据自己的配置情况进行修改

![1613379876496](assets/1613379876496.png)



需要在  gateways 和 services 两个父工程中 引入nacos配置中心依赖

```xml
	 <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
```



修改每个微服务  注释掉原来的 application.yml配置 ，创建 bootstrap.yml 启动配置

```yml
spring:
  cloud:
    nacos:
      config: # 配置中心    name - 环境 .yml
        server-addr: 47.101.211.236:8848
        file-extension: yml # 配置文件的后缀
        namespace: b6bd13e2-1323-428a-b43e-bb9d5d84a4f2 # 连接指定环境的配置
      discovery: # 注册中心
        server-addr: 47.101.211.236:8848
  application:
    name: leadnews-admin-gateway
  profiles:
    active: dev
```





## 5 微服务持续部署

每个微服务使用的dockerfile的方式进行构建镜像后创建容器，需要在每个微服务中添加docker相关的配置

（1）修改**每个微服务**的pom文件，添加dockerfile的插件

```xml
	<properties>
        <docker.image>docker_storage</docker.image>
    </properties>
    <build>
        <finalName>heima-leadnews-wemedia</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.7.0</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>1.3.6</version>
                <configuration>
                    <repository>${docker.image}/${project.build.finalName}</repository>
                    <buildArgs>
                        <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                    </buildArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

（2）在每个微服务的根目录下创建Dockerfile文件，如下：

```dockerfile
# 设置JAVA版本
FROM java:8
# 指定存储卷, 任何向/tmp写入的信息都不会记录到容器存储层
VOLUME /tmp
# 拷贝运行JAR包
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
# 设置JVM运行参数， 这里限定下内存大小，减少开销
ENV JAVA_OPTS="\
-server \
-Xms256m \
-Xmx512m \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=512m"
# 入口点， 执行JAVA运行命令
ENTRYPOINT java ${JAVA_OPTS}  -jar /app.jar
```

![1603556179776](assets\1603556179776.png)

### 5.1 基础依赖打包配置

在微服务运行之前需要在本地仓库中先去install所依赖的jar包，所以第一步应该是从git中拉取代码，并且把基础的依赖部分安装到仓库中

（1）新创建一个item,起名为heima-leadnews

![1603202842506](assets\1603202842506.png)



![1603556912178](assets\1603556912178.png)

（2）配置当前heima-leadnews

- 描述项目

![1603556984260](assets\1603556984260.png)

- 源码管理：

  选中git，输入git的仓库地址（前提条件，需要把代码上传到gitee仓库中），最后输入gitee的用户名和密码

  如果没有配置Credentials，可以选择添加，然后输入用户名密码即可 **(公开仓库无需密码)**
  
  
  
  **jenkins拉取gitlab代码 ssh配置**
  https://blog.csdn.net/weixin_33709590/article/details/92425799
  
  

![1603557100765](assets\1603557100765.png)

- 其中构**建触发器**与**构建环境**暂不设置

- 设置**构建**配置

  选择`Invoke top-level Maven targets`

![1603557353285](assets\1603557353285.png)

​	maven版本：就是之前在jenkins中配置的maven

​	目标：输入maven的命令  `clean install -Dmaven.test.skip=true`  跳过测试安装

![1603557446935](assets\1603557446935.png)

(3)启动项目

创建完成以后可以在主页上看到这个item

![1603557661626](assets\1603557661626.png)

启动项目：点击刚才创建的项目，然后Build Now

![1603557703328](assets\1603557703328.png)

在左侧可以查看构建的进度：

![1603557774990](assets\1603557774990.png)

点进去以后，可以查看构建的日志信息

构建的过程中，会不断的输入日志信息，如果报错也会提示错误信息

![1603557824124](assets\1603557824124.png)

jenkins会先从git仓库中拉取代码，然后执行maven的install命令，把代码安装到本地仓库中

最终如果是success则为构建成功

![1603557903228](assets\1603557903228.png)

### 5.2 微服务打包配置

（1）新建item，以**heima-leadnews-admin**微服务为例

![1603558664549](assets\1603558664549.png)

(2)配置

- 概述

![1603558714167](assets\1603558714167.png)

- 源码管理

![1603558748960](assets\1603558748960.png)

- 构建

配置maven

![1603558861469](assets\1603558861469.png)

**执行maven命令：**

```
clean install -Dmaven.test.skip=true -P dev dockerfile:build -f heima-leadnews-services/admin-service/pom.xml
```

**注意目录接口， maven命令要找到pom.xml的位置**

>-Dmaven.test.skip=true   跳过测试
>
>-P prod  指定环境为生成环境
>
>dockerfile:build  启动dockerfile插件构建容器
>
>-f heima-leadnews-admin/pom.xml  指定需要构建的文件（必须是pom）

![image-20210427115048199](assets/image-20210427115048199.png)

![1603558973180](assets\1603558973180.png)

执行shell命令

![1603558910209](assets\1603558910209.png)

![1603559164996](assets\1603559164996.png)

```shell
if [ -n  "$(docker ps -a -f  name=heima-$JOB_NAME  --format '{{.ID}}' )" ]
 then
 #删除之前的容器
 docker rm -f $(docker ps -a -f  name=heima-$JOB_NAME  --format '{{.ID}}' )
fi
 # 清理镜像
docker image prune -f 
 # 启动docker服务
docker run -d --net=host  --name heima-$JOB_NAME docker_storage/heima-$JOB_NAME
```



这里不是只单纯的启动服务， 我们要考虑每次构建， 都会产生镜像， 所以要先做检查清理， 然后再启动服务。

Docker有五种网络连接模式， 因为我们不是所有服务都采用docker构建， 中间件服务部署在宿主机上面， 这里我们采用host模式， 这样docker容器和主机服务之间就是互通的。

- bridge模式

  使用命令： --net=bridge， 这是dokcer网络的默认设置，为容器创建独立的网络命名空间，容器具有独立的网卡等所有单独的网络栈，这是默认模式。

- host模式

  使用命令： --net=host，直接使用容器宿主机的网络命名空间， 即没有独立的网络环境。它使用宿主机的ip和端口。

- none模式

  命令： --net=none,  为容器创建独立网络命名空间, 这个模式下，dokcer不为容器进行任何网络配置。需要我们自己为容器添加网卡，配置IP。

- container模式

  命令： --net=container:NAME_or_ID， 与host模式类似， 这个模式就是指定一个已有的容器，共享该容器的IP和端口。

- 自定义模式

  docker 1.9版本以后新增的特性，允许容器使用第三方的网络实现或者创建单独的bridge网络，提供网络隔离能力。



**到此就配置完毕了，保存即可**

（3）启动该项目 Build Now

- 首先从git中拉取代码
- 编译打包项目
- 构建镜像
- 创建容器
- 删除多余的镜像

可以从服务器中查看镜像

![1603559728626](assets\1603559728626.png)

容器也已创建完毕

![1603559790306](assets\1603559790306.png)

可以使用postman测试测试该服务接口

### 5.3 构建其他微服务

可以参考admin微服务创建其他微服务，每个项目可能会有不同的maven构建命令，请按照实际需求配置

- heima-leadnews-admin-gateway微服务的配置：

```
maven命令：

clean install -Dmaven.test.skip=true dockerfile:build -f  heima-leadnews-gateways/admin-gateway/pom.xml
```



>![1603561212541](assets\1603561212541.png)



heima-leadnews-user微服务的配置：

```
maven命令：

clean install -Dmaven.test.skip=true dockerfile:build -f heima-leadnews-services/user-service/pom.xml
```

>![1603561293105](assets\1603561293105.png)

所有项目构建完成以后，在本地启动admin前端工程，修改configs中的网关地址为：`192.168.200.100`,进行效果测试

**同样方式配置其它微服务**





## 6 接入层及前端部署

### 6.1 接入层nginx搭建

官方网站下载 nginx：http://nginx.org/，也可以使用资料中的安装包，版本为：nginx-1.18.0

**安装依赖**

- 需要安装 gcc 的环境

```shell
yum install gcc-c++
```

- 第三方的开发包。

  - PCRE(Perl Compatible Regular Expressions)是一个 Perl 库，包括 perl 兼容的正则表达式库。nginx 的 http 模块使用 pcre 来解析正则表达式，所以需要在 linux 上安装 pcre 库。

    ```shell
    yum install -y pcre pcre-devel
    ```

    注：pcre-devel 是使用 pcre 开发的一个二次开发库。nginx 也需要此库。

  - zlib 库提供了很多种压缩和解压缩的方式，nginx 使用 zlib 对 http 包的内容进行 gzip，所以需要在 linux 上安装 zlib 库。

    ```shell
    yum install -y zlib zlib-devel
    ```

  - OpenSSL 是一个强大的安全套接字层密码库，囊括主要的密码算法、常用的密钥和证书封装管理功能及 SSL 协议，并提供丰富的应用程序供测试或其它目的使用。nginx
    不仅支持 http 协议，还支持 https（即在 ssl 协议上传输 http），所以需要在 linux安装 openssl 库。

    ```shell
    yum install -y openssl openssl-devel
    ```

**Nginx安装**

第一步：把 nginx 的源码包nginx-1.18.0.tar.gz上传到 linux 系统

第二步：解压缩

```shell
tar -zxvf nginx-1.18.0.tar.gz
```

第三步：进入nginx-1.18.0目录   使用 configure 命令创建一 makeFile 文件。

```shell
./configure \
--prefix=/usr/local/nginx \
--pid-path=/var/run/nginx/nginx.pid \
--lock-path=/var/lock/nginx.lock \
--error-log-path=/var/log/nginx/error.log \
--http-log-path=/var/log/nginx/access.log \
--with-http_gzip_static_module \
--http-client-body-temp-path=/var/temp/nginx/client \
--http-proxy-temp-path=/var/temp/nginx/proxy \
--http-fastcgi-temp-path=/var/temp/nginx/fastcgi \
--http-uwsgi-temp-path=/var/temp/nginx/uwsgi \
--http-scgi-temp-path=/var/temp/nginx/scgi
```

执行后可以看到Makefile文件

第四步：编译

```shell
make
```

第五步：安装

```shell
make install
```

第六步：启动

注意：启动nginx 之前，上边将临时文件目录指定为/var/temp/nginx/client， 需要在/var  下创建此
目录

```shell
mkdir /var/temp/nginx/client -p
```

进入到Nginx目录下的sbin目录

```shell
cd /usr/local/nginx/sbin
```

输入命令启动Nginx

```shell
./nginx
```

启动后查看进程

```shell
ps aux|grep nginx
```

![1613376602041](assets/1613376602041.png)



### 6.2 发布前端工程

前端在开发时，是基于node环境在本地开发，引用了非常多的基于node的js 在开发完毕后也许要发布，webpack依赖就是用于发布打包的，它会将很多依赖的js进行整合，最终打包成 html css js 这三种格式的文件，我们把发布后的静态文件拷贝到nginx管理的文件夹中，即可完成部署

```shell
# 创建目录  用于存放对应的前端静态资源
mkdir -p /root/workspace/admin 
mkdir -p /root/workspace/web
mkdir -p /root/workspace/wemedia
```

**admin前端工程发布**

在admin工程下，打开cmd  输入: `npm run build` 进行发布

发布后的静态文件，会存放到dist文件夹中

![1613377149886](assets/1613377149886.png)

![1613377225038](assets/1613377225038.png)

把dist文件夹上传到服务器上，拷贝到150虚拟机的/root/workspace/admin目录中

**wemedia前端工程发布**

在wemedia工程下，打开cmd  输入: `npm run build` 进行发布

发布后的静态文件，会存放到dist文件夹中

![1613377649182](assets/1613377649182.png)

把dist文件夹上传到服务器上，拷贝到150虚拟机的/root/workspace/wemedia目录中

**app前端工程发布**

前端工程比较特殊，因为使用了被称为三端合一的weex框架,也就是说它即可以发布android端，也可以发布ios端，也可以发布web端。命令会有区别

在app工程下，打开cmd  输入: `npm run clean:web && npm run build:prod:web` 进行发布web端

```
小贴士: 其它端需要安装对应软件才能发布，比如android需要有android studio
npm run pack:android   发布安卓
npm run pack:ios       发布ios
```

![1613377967699](assets/1613377967699.png)

把releases文件夹下的web文件夹上传到服务器上，拷贝到150虚拟机的/root/workspace/目录中

### 6.3 nginx配置前端工程访问

对于不同的前端工程 ， 我们会通过不同的域名来访问, 先给三个前端工程准备3个访问域名

1. 使用type下载hosts插件
2. 配置3个域名:   
3. 47.101.211.236  admin.leadnews.com    运营端
4. 47.101.211.236  wemedia.leadnews.com    媒体端
5. 47.101.211.236  web.leadnews.com    app端



![1613378464091](assets/1613378464091.png)

![1613378379712](assets/1613378379712.png)

```
小贴士: 
如果想部署外网访问的项目，可以使用内网穿透 准备三个外网地址
全部映射到
47.101.211.236  的 80 端口

下面的nginx也使用对应的外网地址
```

打开linux的目录：/usr/local/nginx/conf

编辑nginx.conf文件，替换如下:

**网关地址请按自己实际地址配置**

**访问三个端的域名，请按自己实际地址配置**

```nginx
user  root;
worker_processes  1;
events {
    worker_connections  1024;
}
http {
    include       mime.types;
    default_type  application/octet-stream;
    
    # 反向代理配置 代理admin gateway
    upstream  heima-admin-gateway{
        server 47.101.211.236:6001;  
    }
    # 反向代理配置 代理wemedia gateway
    upstream  heima-wemedia-gateway{
       server 47.101.211.236:6002;
    }
    # 反向代理配置 代理app gateway
    upstream  heima-app-gateway{
       server 47.101.211.236:5001;
    }
    
    server {
	listen 80;
	server_name localhost;
        location / {
            root /usr/local/nginx/html;
            index index.html ;
        }	
     }
     server {
        listen 80;
        server_name hmtt-web.cn.utools.club;
        location / {
            root /root/workspace/web;
            index index.html ;
        }   
        location ~/app/(.*) {
            proxy_pass http://heima-app-gateway/$1;
            proxy_set_header HOST $host;
            proxy_pass_request_body on;
            proxy_pass_request_headers on;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
     }
     server {
        listen 80;
        server_name hmtt-admin.cn.utools.club;
        location / {
            root /root/workspace/admin/dist;
            index index.html ;
        }
        location ~/service_6001/(.*) {
            proxy_pass http://heima-admin-gateway/$1;
            proxy_set_header HOST $host;
            proxy_pass_request_body on;
            proxy_pass_request_headers on;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }          
     }  
     server {
        listen 80;
        server_name hmtt-wemedia.cn.utools.club;
        location / {
            root /root/workspace/wemedia/dist;
            index index.html ;
        }
        location ~/wemedia/MEDIA/(.*) {
            proxy_pass http://heima-wemedia-gateway/$1;
            proxy_set_header HOST $host;
            proxy_pass_request_body on;
            proxy_pass_request_headers on;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }                              
     } 
}
```

配置完毕后，重启nginx

命令: `/usr/local/nginx/sbin/nginx -s reload`

输入网址访问前端工程:

![1613378750447](assets/1613378750447.png)

![1613378843939](assets/1613378843939.png)









## 7 域名设置与绑定（了解）

### 7.1 域名购买

在阿里云服务上部署完项目，你的项目就已经正式在互联网上线了
不过目前还是只能通过 外网的IP地址访问

如果要买域名 ==>  https://mi.aliyun.com/

### 7.2 域名备案

域名购买完毕后是需要备案的

如果您的网站托管在阿里云中国内地（大陆）节点服务器上，且网站的主办人和域名从未办理过备案，在网站开通服务前，您需通过阿里云ICP代备案系统完成ICP备案。

备案前您需准备备案所需的相关资料，通过PC端或App端进行备案信息填写、资料上传、真实性核验等，备案信息提交后需通过阿里云初审、短信核验和管局审核，整个备案流程预计所需时长约1~22个工作日左右，具体时长以实际操作时间为准。

http://help.aliyun.com/knowledge_detail/36922.html



### 7.3 域名绑定

域名需要和你的外网阿里云IP地址 绑定方可使用

配置域名解析地址==>  https://dns.console.aliyun.com/

![image-20210428172804434](assets/image-20210428172804434.png)

我们使用内网穿透 将地址 映射的 阿里云外网IP即可

















## 8.0 自动通知jenkins触发任务（了解）

主流的git软件都提供了webhooks功能(web钩子), 通俗点说就是git在发生某些事件的时候可以通过POST请求调用我们指定的URL路径，那在这个案例中，我们可以在push事件上指定jenkins的任务通知路径。

### 8.1 jenkins配置Gitee插件

**jenkins下载webhooks插件**

gitee插件介绍: https://gitee.com/help/articles/4193#article-header0

jenkins也支持通过url路径来启动任务，具体设置方法: 

jenkins的默认下载中仅下载了github的通知触发,我们需要先下载一个插件

(1) 下载gitee插件

系统管理-->插件管理-->可选插件-->搜索 `Gitee` 下载-->重启jenkins

![1606929689394](assets\1606929689394.png)

(2) gitee生成访问令牌

   首先，去下面网址生成gitee访问令牌

   https://gitee.com/profile/personal_access_tokens

![1606929726997](assets\1606929726997.png)

添加令牌描述，提交，弹出框输入密码

![1606929755320](assets\1606929755320.png)

复制令牌

![1606929779247](assets\1606929779247.png)

（3） jenkins中配置Gitee

系统管理 --> 系统配置 --> Gitee配置

1. 链接名: gitee
2. 域名: https://gitee.com
3. 令牌: Gitee Api 令牌   (需要点击添加按下图配置)
4. 配置好后测试连接
5. 测试成功后保存配置

![1606929817791](assets\1606929817791.png)

令牌配置: 

1. 类型选择Gitee API令牌
2. 私人令牌: 将码云中生成的令牌复制过来
3. 点击添加

![1606929845138](assets\1606929845138.png)

### 8.2 修改jenkins构建任务

**修改配置接收webhooks通知**

任务详情中点击配置来修改任务

![1606929890564](assets\1606929890564.png)

点击构建触发器页签,勾选`Gitee webhook`

![1606929947046](assets\1606929947046.png)

生成Gitee Webhook密码

![1606929980749](assets\1606929980749.png)

保存好触发路径和webhook密码，到gitee中配置webhook通知

如: 

触发路径:  http://192.168.200.151:8888/gitee-project/dockerDemo

触发密码: a591baa17f90e094500e0a11b831af9c

### 8.3 Gitee添加webhooks通知

**gitee仓库配置webhooks通知**

点击仓库页面的管理

![1606930088046](assets\1606930088046.png)

添加webhook

1. 点击webhooks菜单，然后点击添加
2. 配置jenkins通知地址
3. 填写密码
4. 点击添加

![1606930153212](assets\1606930153212.png)

但在点击添加时，提示失败 gitee中需要配置一个公有IP或域名，这里我们可以通过内网穿透来解决

![1606930181199](assets\1606930181199.png)

这个时候需要使用内网穿透来映射本地的ip和端口号

![1606930276099](assets\1606930276099.png)

在gitee中将上面的外网地址替换之前的ip和端口部分，再次添加

![1606930326225](assets\1606930326225.png)

### 8.4 测试自动构建

添加完毕后测试一下:

提交leadnews-admin的代码测试是否自动触发了jenkins中的构建任务

![1606930472587](assets\1606930472587.png)

