### 1. 安装mysql
​	a.首先创建一个network
​        `docker network create -d bridge my-net`
​        将一个已经运行的容器连接该网络
​        `docker network connect my-net mm`
​        为了测试网络，我们可以执行命令：
​        `ping mn`

​	b.准备镜像

```shell
docker pull mysql:5.7.25
```

​	c.挂载本地目录
​        1）.创建一个用户

```shell
useradd leyou
```

​        2）.设置密码

```sh
passwd leyou
```

​        3）.进入用户目录

```shell
cd /home/leyou
```

​        4）.创建mysql目录

```shell
mkdir mysql
```

​        5）.配置并运行容器

```shell
docker run \
 -p 3306:3306 \
 --name mysql \
 -v $PWD/conf:/etc/mysql/conf.d \
 -v $PWD/data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=123 \
 --privileged \
 -d \
 mysql:5.7.25
```

​		5.1). 自定义配置

```shell
vi conf/my.cnf
```

- 添加如下内容 【使用insert模式进入修改编辑】

```shell
[mysqld]
skip-name-resolve
character_set_server=utf8
datadir=/var/lib/mysql
server-id=1000
```

重启mysql容器命令

```shell
docker restart mysql
```



### 2. 安装Redis

1. 导入镜像

```shell
docker pull redis:5.0
```

2. 挂载本地文件

- 进入leyou目录

```shell
cd /home/leyou
```

- 创建一个Redis文件夹	

```shell
mkdir redis
cd redis
mkdir data
```

- 创建Redis配置文件

```shell
touch redis.conf
```

- 编辑配置的命令

```shell
vi redis.conf
```

- 添加配置

```shell
databases 1
dir /data
appendonly yes
appendfilename appendonly.aof
appendfsync everysec
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
```

3. 运行容器

```shell
docker run \
 -p 6379:6379 \
 --name redis \
 -v $PWD/redis.conf:/usr/local/etc/redis/redis.conf \
 -v $PWD/data:/data \
 --privileged \
 -d \
 redis:5.0 \
 redis-server /usr/local/etc/redis/redis.conf
```

安装完成后，可以再控制台执行命令测试：

```
docker exec -it redis redis-cli
```

可以进入Redis控制台：

![image-20200713100051057](D:/视频资料/项目二/day01-Docker入门/讲义/assets/image-20200713100051057.png)

### 3. 安装rabbitmq

1. 运行容器

```shell
docker run \
 -e RABBITMQ_DEFAULT_USER=leyou \
 -e RABBITMQ_DEFAULT_PASS=123321 \
 --name mq \
 --hostname mq1 \
 -p 15672:15672 \
 -p 5672:5672 \
 -d \
 rabbitmq:3-management
```



### 4. 安装elasticsearch

1. 关闭防火墙

```shell
# 关闭
systemctl stop firewalld
# 禁止开机启动防火墙
systemctl disable firewalld
```

2. 设置开机自启docker

```shell
systemctl enable docker
```

3. 创建并运行容器

```shell
docker run -d \
	--name es \
    -e "ES_JAVA_OPTS=-Xms1024m -Xmx1024m" \
    -e "discovery.type=single-node" \
    -v es-data:/usr/share/elasticsearch/data \
    -v es-plugins:/usr/share/elasticsearch/plugins \
    --privileged \
    --network my-net \
    -p 9200:9200 \
    -p 9300:9300 \
elasticsearch:7.4.2
```

4. 设置开机自启常用容器

```shell
docker update --restart=always es
docker update --restart=always mysql
docker update --restart=always mq
docker update --restart=always redis
```

