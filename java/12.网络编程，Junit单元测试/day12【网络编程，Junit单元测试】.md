# day12【网络编程，Junit单元测试】

## 今日内容

- 网络编程三要素

- TCP通信
- 文件上传
- 模拟B/S

## 教学目标

- [ ] 能够描述网络编程的三要素及其对应作用
- [ ] 能够说出UDP协议的特点
- [ ] 能够说出TCP协议的特点
- [ ] 能够说出TCP协议构建服务端和客户端的类
- [ ] 能够使用TCP协议实现客户端和服务端字符串数据传输
- [ ] 能够理解TCP协议下文件上传案例
- [ ] 能够理解TCP协议下实现Web服务端案例
- [ ] 能够使用Junit进行程序测试
- [ ] 能够使用断言进行逻辑结果测试





```markdown
# 1. 网络编程
1. 网络 : 计算机与计算机数据交换
2. 编程 : 写程序(起码两个程序)

# 2. 软件架构
1. C/S : client to server (客户端对服务端)
	I. eg. QQ 微信 抖音 ...(对用户来说,下载客户端的)
	II. 服务端: 软件运营商计算机运行的程序,提供数据服务的
		JAVASE: java standard edition 标准版(基础)
		JAVAEE: java enterprice edition 企业版
	III. 客户端: 用户计算机运行的程序,消费数据服务的
	
2. B/S : browser to server(浏览器端会服务端)
	I. eg. 百度 网页版淘宝 (对用户来说,不用下载客户端,直接用浏览器即可访问)
	II. 浏览器
	III. 服务端
	
3. 前端/后端: 用户能直接接触到的是前端,接触不到是后端
	1). 前端: 客户端(pc端,移动端), 浏览器网页
	2). 后端: 服务端

4. B/S是未来的方向
	1). 页游(贪玩蓝月)
		I. 不好: 画质差,严重依赖网速
		II. 好: 方便, 有个浏览器就能玩
	2). 端游(LOL..)
		高频,高画质要求
```



```markdown
# 网络三要素
0. 必要的元素
1. 协议
	1). 网络协议是网络通信的规则(protocol)  
	2). 网络数据传输的两端必须遵循和支持同一套协议
	
2. ip
	1). ip(Internet protocol address) 互联网协议地址
	2). 作用: 在同一网络中,用于唯一标识一台计算机的 (ip是A计算机在网络中的身份证)
	3). 版本
		I. ipv4(ip version 4) : 用4个字节表示一个ip
			192.168.1.1(4个网段,每个网段一个字节 0~255)
			约为43亿,2011年枯竭了
		
        II. ipv6(ip version 6): 用16个字节表示一个ip
        	号称 就算地球上每一粒都能联网,都够用
	4). dos命令(cmd : command)
		I. ipconfig : 查看本机的ip地址
		II. ping ip地址 : 测试能否联通
	
    5). DNS协议 (domain name system 域名解析服务)
    	I. 域名  和 ip 建立映射关系
    	II. www.baidu.com   =  112.80.248.76
    	III. 简单:
    		用户发起请求(www.baidu.com) 
    			-> DNS(Map) 
    				-> 112.80.248.76(百度服务器)
    	
3. port(端口)
	1). 作用: 在一台计算机中,端口用于标识一个进程的(程序)
	2). 解释:
		I. 一个程序启动起码有一个进程
		II. 一个进程运行需要占用一个端口,一个端口只能被一个进程占用
		III. 我们通过端口可以在一台计算上找到这个程序
	3). 端口用2个字节表示(0~65535)	
		I. 0~1024 不要用,用于系统/知名的一些服务
		II. 其他我们的程序可以随便用
		III. 注意: 千万不要重复占用同一端口,如果重复占用,报错: BindException 
		
4. 举个例子:
	浏览器中的百度(网址)
		https://www.baidu.com/?tn=78000241_hao_pg
		协议://ip:port
		
	解释
		1).
    	https协议,默认端口是443
    		https://www.baidu.com:444(:443可以省略)
    	http协议,默认端口是80
    	2). 
    		https协议: 浏览器和百度服务器的传输规则
    		ip : 112.80.248/76 (互联网中百度服务器的地址)
    		port : 443 (百度计算机上搜索引擎程序)

5. 网络编程中如何应用这三要素
	I. 开发服务端
        1). 选择一个协议
        2). ip不用选, 是路由器动态分配的(公开)
        3). 选择自己要占用端口
    II. 开发客户端
    	1). 选择与服务端一致的协议
    	2). 选择服务端的ip
    	3). 选择服务端占用的端口
    
    III. socket(套接字) = ip + 端口
    	定位互联网中一台计算机上的某个程序
```







# 第一章 网络编程入门

## 1.1软件结构

- **C/S结构** ：全称为Client/Server结构，是指客户端和服务器结构。常见程序有ＱＱ、迅雷等软件。

![1566446300784](imgs/01_CS%E6%9E%B6%E6%9E%84.png)

**B/S结构** ：全称为Browser/Server结构，是指浏览器和服务器结构。常见浏览器有谷歌、火狐等。

![1566446315067](imgs/02_BS%E6%9E%B6%E6%9E%84.png)

两种架构各有优势，但是无论哪种架构，都离不开网络的支持。**网络编程**，就是在一定的协议下，实现两台计算机的通信的程序。



## 1.2 网络通信协议

- **网络通信协议：**通信协议是计算机必须遵守的规则，只有遵守这些规则，计算机之间才能进行通信。这就好比在道路中行驶的汽车一定要遵守交通规则一样，协议中对数据的传输格式、传输速率、传输步骤等做了统一规定，通信双方必须同时遵守，最终完成数据交换。 

- **TCP/IP协议族：** 传输控制协议/因特网互联协议( Transmission Control Protocol/Internet Protocol)，是Internet最基本、最广泛的协议。它定义了计算机如何连入因特网，以及数据如何在它们之间传输的标准。它的内部包含一系列的用于处理数据通信的协议，并采用了4层的分层模型，每一层都呼叫它的下一层所提供的协议来完成自己的需求。

![1566446331855](imgs/03_%E9%80%9A%E8%AE%AF%E5%8D%8F%E8%AE%AE.png)



## 1.3 协议分类

通信的协议还是比较复杂的，`java.net` 包中包含的类和接口，它们提供低层次的通信细节。我们可以直接使用这些类和接口，来专注于网络程序开发，而不用考虑通信的细节。

`java.net` 包中提供了两种常见的网络协议的支持：

- **TCP**：传输控制协议 (Transmission Control Protocol)。TCP协议是**面向连接**的通信协议，即传输数据之前，在发送端和接收端建立逻辑连接，然后再传输数据，它提供了两台计算机之间可靠无差错的数据传输。
  - 三次握手：TCP协议中，在发送数据的准备阶段，客户端与服务器之间的三次交互，以保证连接的可靠。
    - 第一次握手，客户端向服务器端发出连接请求，等待服务器确认。服务器你死了吗？
    - 第二次握手，服务器端向客户端回送一个响应，通知客户端收到了连接请求。我活着啊！！
    - 第三次握手，客户端再次向服务器端发送确认信息，确认连接。整个交互过程如下图所示。我知道了！！

![1566446712862](imgs/04_TCP%E5%8D%8F%E8%AE%AE%E4%B8%89%E6%AC%A1%E6%8F%A1%E6%89%8B.png)

​    完成三次握手，连接建立后，客户端和服务器就可以开始进行数据传输了。由于这种面向连接的特性，TCP协议可以保证传输数据的安全，所以应用十分广泛，例如下载文件、浏览网页等。

- **UDP**：用户数据报协议(User Datagram Protocol)。UDP协议是一个**面向无连接**的协议。传输数据时，不需要建立连接，不管对方端服务是否启动，直接将数据、数据源和目的地都封装在数据包中，直接发送。每个数据包的大小限制在64k以内。它是不可靠协议，因为无连接，所以传输速度快，但是容易丢失数据。日常应用中,例如视频会议、QQ聊天等。



## 1.4 网络编程三要素

### 协议

- **协议：**计算机网络通信必须遵守的规则，已经介绍过了，不再赘述。

### IP地址

- **IP地址：指互联网协议地址（Internet Protocol Address）**，俗称IP。IP地址用来给一个网络中的计算机设备做唯一的编号。假如我们把“个人电脑”比作“一台电话”的话，那么“IP地址”就相当于“电话号码”。

**IP地址分类 **

- IPv4：是一个32位的二进制数，通常被分为4个字节，表示成`a.b.c.d` 的形式，例如`192.168.65.100` 。其中a、b、c、d都是0~255之间的十进制整数，那么最多可以表示42亿个。

- IPv6：由于互联网的蓬勃发展，IP地址的需求量愈来愈大，但是网络地址资源有限，使得IP的分配越发紧张。有资料显示，全球IPv4地址在2011年2月分配完毕。

  为了扩大地址空间，拟通过IPv6重新定义地址空间，采用128位地址长度，每16个字节一组，分成8组十六进制数，表示成`ABCD:EF01:2345:6789:ABCD:EF01:2345:6789`，号称可以为全世界的每一粒沙子编上一个网址，这样就解决了网络地址资源数量不够的问题。

**常用命令**

- 查看本机IP地址，在控制台输入：

```java
ipconfig
```

- 检查网络是否连通，在控制台输入：

```java
ping 空格 IP地址
ping 220.181.57.216
ping www.baidu.com
```

**特殊的IP地址**

- 本机IP地址：`127.0.0.1`、`localhost` 。

### 端口号

网络的通信，本质上是两个进程（应用程序）的通信。每台计算机都有很多的进程，那么在网络通信时，如何区分这些进程呢？

如果说**IP地址**可以唯一标识网络中的设备，那么**端口号**就可以唯一标识设备中的进程（应用程序）了。

- **端口号：用两个字节表示的整数，它的取值范围是0~65535**。其中，0~1023之间的端口号用于一些知名的网络服务和应用，普通的应用程序需要使用1024以上的端口号。如果端口号被另外一个服务或应用所占用，会导致当前程序启动失败。

利用`协议`+`IP地址`+`端口号` 三元组合，就可以标识网络中的进程了，那么进程间的通信就可以利用这个标识与其它进程进行交互。

在Java中，可以使用java.net.InetAddress类来表示一个IP地址：

```java
/**
    InetAddress类概述
        * 一个该类的对象就代表一个IP地址对象。

    InetAddress类成员方法
        * static InetAddress getLocalHost()
            * 获得本地主机IP地址对象
        * static InetAddress getByName(String host)
            * 根据IP地址字符串或主机名获得对应的IP地址对象

        * String getHostName();获得主机名
        * String getHostAddress();获得IP地址字符串
 */
public class InetAddressDemo01 {
    public static void main(String[] args) throws Exception {
        // 获得本地主机IP地址对象
        InetAddress inet01 = InetAddress.getLocalHost();
        // pkxingdeMacBook-Pro.local/10.211.55.2
        // 主机名/ip地址字符串
        System.out.println(inet01);
        // 根据IP地址字符串或主机名获得对应的IP地址对象
        // InetAddress inet02 = InetAddress.getByName("192.168.73.97");
        InetAddress inet02 = InetAddress.getByName("baidu.com");
        System.out.println(inet02);

        // 获得主机名
        String hostName = inet01.getHostName();
        System.out.println(hostName);
        // 获得IP地址字符串
        String hostAddress = inet01.getHostAddress();
        System.out.println(hostName);
        System.out.println(hostAddress);
    }
}
```



# 第二章 TCP通信程序

```markdown
# 1. tcp/ip协议族
1). 应用层 : http协议, DNS, ftp, smtp ...
2). 传输层 : tcp , udp
3). 网络层 : ip
4). 物理层 ...

# 2. 传输层的tcp协议
1). tcp : Transmission  Control Protocol 传输控制协议
	I. 面向连接: 先连接,再传输(确保数据不会丢失,效率低)
	II. 运用场景: 大部分
	III. 保证面向连接: 三次握手
		你(客户端)  你女朋友(服务端) , tcp规定客户端先发起请求
		
		你 -> 你女朋友: 在吗,你听的到了吗?  (你发送,你女朋友接收没问题)
		你女朋友 -> 你 : 听得到,你呢?  (你女朋友发送,你接收没问题)
		你 -> 你女朋友 : 我也可以听得到,blabal 
		
	IV. 断开连接 : 四次挥手	
		
		你 -> 你女朋友 : 宝贝,我说完了,现在断开连接啦! (客户端传输完数据了,第一次挥手)
		你女朋友 -> 你 : 我知道了 (此时服务端可能还没传输数据, 第二次挥手)
        你女朋友 -> 你 : 我也说完了,我也要断开连接啦! (服务端传输完数据,第三次挥手)
        你 -> 你女朋友 : 我也知道了,晚安 (服务器立马断开,客户端稍后断开连接)
		
	
2). udp : User Datagram Protocol 用户数据报协议
	II. 面向无连接 : 不需要连接,直接传输(数据有可能丢失, 效率高)
	III. 运用场景: 直播(数据安全要求不高,即时性要求很高), 视频会议...
```



## 2.1 TCP协议概述

- TCP协议是面向连接的通信协议，即在传输数据前先在客户端和服务器端建立逻辑连接，然后再传输数据。它提供了两台计算机之间可靠无差错的数据传输。TCP通信过程如下图所示：

![1566446503937](imgs/06_TCP%E9%80%9A%E4%BF%A1%E8%BF%87%E7%A8%8B.png)

```java
TCP ==> Transmission  Control Protocol ==> 传输控制协议
TCP协议的特点
    * 面向连接的协议
    * 只能由客户端主动发送数据给服务器端，服务器端接收到数据之后，可以给客户端响应数据。
    * 通过三次握手建立连接，连接成功形成数据传输通道。
    * 通过四次挥手断开连接
    * 基于IO流进行数据传输
    * 传输数据大小没有限制
    * 因为面向连接的协议，速度慢，但是是可靠的协议。

TCP协议的使用场景
    * 文件上传和下载
    * 邮件发送和接收
    * 远程登录

TCP协议相关的类
    * Socket
        * 一个该类的对象就代表一个客户端程序。
    * ServerSocket
        * 一个该类的对象就代表一个服务器端程序。

Socket类构造方法
    * Socket(String host, int port)
        * 根据ip地址字符串和端口号创建客户端Socket对象
        * 注意事项：只要执行该方法，就会立即连接指定的服务器程序，如果连接不成功，则会抛出异常。
            如果连接成功，则表示三次握手通过。

Socket类常用方法
    * OutputStream getOutputStream(); 获得字节输出流对象
    * InputStream getInputStream();获得字节输入流对象
```



```java
package com.itheima01.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/*
* TCP协议相关的类
    * Socket
        * 一个该类的对象就代表一个客户端程序。
    * ServerSocket
        * 一个该类的对象就代表一个服务器端程序。

Socket类构造方法
    * Socket(String host, int port)
        * 根据ip地址字符串和端口号创建客户端Socket对象
        * 注意事项：只要执行该方法，就会立即连接指定的服务器程序，如果连接不成功，则会抛出异常。
            如果连接成功，则表示三次握手通过。

Socket类常用方法
    * OutputStream getOutputStream(); 获得字节输出流对象
    * InputStream getInputStream();获得字节输入流对象
*
* */
public class TcpServer {

    public static void main(String[] args) throws IOException {
        /*
        *  public ServerSocket(int port)
        *   1. port = 0~65535 (0~1024)
        *
        * # 表示:
        *   1). 服务端协议是tcp协议(ServerSocket封装)
        *   2). ip是当前程序运行计算机的ip
        *   3). port=10010, 有可能跟当前系统其它程序冲突 (BindException)
        * */
        //1. 创建服务端socket(套接字)
        ServerSocket serverSocket = new ServerSocket(9988);
        //2. 接收来自客户端的请求,创建连接通道
            //阻塞方法: 客户端如果还未发起请求,此方法一直等在这里
                    // 直到客户端发起请求,建立连接,此方法才会继续向下运行
        Socket socket = serverSocket.accept();

        //获取客户端的socket地址(ip+port)
//        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
//        System.out.println(remoteSocketAddress);

    }

}

```



```java
package com.itheima01.tcp;

import java.io.IOException;
import java.net.Socket;

/*
* tcp规定 客户端先发起请求 (服务端先启动)
*
* */
public class TcpClient {

    public static void main(String[] args) throws IOException {


        /*
         Socket(String host, int port)
        *1.  host(当ip看): 域名或ip都可以
        *       我的计算机: 192.168.133.75
        *       本机ip(本地回环地址):
        *               127.0.0.1 (localhost)
        *2. port
        *
        *   客户端指定要连接服务端的ip和port, 自动连接
        * */
//        String host = "192.168.133.75";
        String host = "127.0.0.1";
        int port = 9988;
        Socket socket = new Socket(host, port);
    }
}

```



## 2.2 TCP通信案例	

### 2.2.1 客户端向服务器发送数据

```java
package com.itheima02.transfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) throws IOException {

        //要连接的服务端的ip和port
        String host = "127.0.0.1";// localhost 本机回环地址
        int port = 9988;
            //对象创建的同时, 底层自动三次握手
        Socket socket = new Socket(host, port);

        //客户端写: 发送
        OutputStream os = socket.getOutputStream();
        os.write("hello server".getBytes());
//        os.close(); //输出流关闭了,socket的输入流也不能使用
        socket.shutdownOutput(); // 停止输出: 标志输出结束(那么服务端就会停止接收 line29)

        //客户端读: 接收
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int length;
        while((length = is.read(buffer)) != -1){
            String str = new String(buffer, 0, length);
            System.out.println("客户端接收到数据:" + str);
        }

        //对象关闭的时候,底层自动四次挥手
        socket.close();

    }
}

```

### 2.2.2 服务器向客户端回写数据

```java
package com.itheima02.transfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
*   1. 服务端先要启动
*       1). 指定服务端占用的端口
*       2). 程序所在的计算机ip
*       3). 协议: tcp
*
*   2. 客户端连接
* */
public class TcpServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9988);
        //接收客户端连接,返回一个数据通道
        Socket socket = serverSocket.accept();

        //服务端读 : 接收
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int length;
        while((length = is.read(buffer)) != -1){
            String str = new String(buffer, 0, length);
            System.out.println("服务端接收到数据:" + str);
        }
//        is.close();

        //服务端写: 发送
        OutputStream os = socket.getOutputStream();
        os.write("hello client".getBytes());


        //服务端可以关闭,也可以不关闭
        socket.close();
        serverSocket.close();
    }
}

```



# 第三章 综合案例

## 3.1 文件上传案例

### 文件上传分析图解

1. 【客户端】输入流，从硬盘读取文件数据到程序中。
2. 【客户端】输出流，写出文件数据到服务端。
3. 【服务端】输入流，读取文件数据到服务端程序。
4. 【服务端】输出流，写出文件数据到服务器硬盘中。

5. 【服务端】获取输出流，回写数据。
6. 【客户端】获取输入流，解析回写数据。

![1566446548503](imgs/07_%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E5%9B%BE%E8%A7%A3.png)

### 案例实现

服务器端实现：

```java
package com.itheima03.upload;

import com.sun.security.ntlm.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9977);
        Socket socket = serverSocket.accept();

        //先读后写
        InputStream is = socket.getInputStream();
            //服务器硬盘的位置,保存起来
        FileOutputStream fos = new FileOutputStream("c:/test/ly.jpg");

        int length;
        byte[] buffer = new byte[1024];
        while((length = is.read(buffer)) != -1){
            fos.write(buffer,0,length);
        }

        fos.close();
        socket.close();
        serverSocket.close();
    }
}

```

客户端实现：

```java
package com.itheima03.upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        Socket socket = new Socket(host, 9977);

        //先读后写
        FileInputStream is = new FileInputStream("day12/刘亦菲.jpg");
        OutputStream os = socket.getOutputStream();

        int length;
        byte[] buffer = new byte[1024];

        while((length = is.read(buffer)) != -1){
            os.write(buffer,0,length);
        }

        is.close();
        //释放资源: 关闭与之相关的资源
        socket.close();
    }
}

```





**优化**

```java
package com.itheima03.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
/*
 * # 文件上传案例代码优化
 * 1. 文件名
 *       固定文件名 -> 动态文件名, 这样就不会覆盖了
 *
 * 2. 永不停止,并且支持并发
 *      while(true)
 *
 *      new Thread().start  读写文件的数据
 * */
public class TcpServer2 {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9977);
        while(true){
            //此方法是阻塞方法: 接收客户端连接的
            Socket socket = serverSocket.accept();

            new Thread(()->{
                try {
                    //先读后写
                    InputStream is = socket.getInputStream();
                    //服务器硬盘的位置,保存起来
                    String str = UUID.randomUUID().toString().replace("-","");
                    String name = "c:/test/" + str + ".jpg";
                    FileOutputStream fos = new FileOutputStream(name);

                    int length;
                    byte[] buffer = new byte[1024];
                    while((length = is.read(buffer)) != -1){
                        fos.write(buffer,0,length);
                    }

                    fos.close();
                    socket.close();
//            serverSocket.close(); //不能关
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }
}

```



```java
package com.itheima03.upload;

import java.util.UUID;

/*
*  通用唯一标识符（Universally Unique Identifier）
*   1. String s = UUID.randomUUID().toString();
*       随机产生一个16进制字符串 (当前时间,网卡信息:mac地址 ...)
*
*       一秒生成1000万个,理论保证3000多年不重复
* */
public class NameDemo {

    public static void main(String[] args) {
        /*
        * 系统当前时间毫秒值: 同一毫秒重复的情况
        * */
        long time = System.currentTimeMillis();
        System.out.println(time);

        /*
        * UUID:
        * */
        for (int i = 0; i < 10; i++) {
            String s = UUID.randomUUID().toString().replace("-","");
            System.out.println(s);
        }
    }
}

```





## 3.2 模拟B\S服务器

模拟网站服务器，使用浏览器访问自己编写的服务端程序，查看网页效果。

### 案例分析

1. 准备页面数据，web文件夹。
2. 我们模拟服务器端，ServerSocket类监听端口，使用浏览器访问，查看网页效果

### 案例实现

浏览器工作原理是遇到图片会开启一个线程进行单独的访问,因此在服务器端加入线程技术。



```java
package com.itheima04.bs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
*   浏览器访问:
*       http://localhost:9966
*
*       http://192.168.133.75:9966
* */
public class HttpServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9966);

        while(true){
            Socket socket = serverSocket.accept();

            new Thread(()->{
                try {
                    //读服务器本地网页,写到通道中,给浏览器
                    FileInputStream is = new FileInputStream("day12/index.html");
                    OutputStream os = socket.getOutputStream();
                    // 响应行, 响应头, 响应体
                    os.write("HTTP/1.1 200 OK\r\n".getBytes());
                    os.write("Content-Type:text/html\r\n".getBytes());
                    os.write("\r\n".getBytes());

                    int length;
                    byte[] buffer = new byte[1024];
                    while((length = is.read(buffer)) != -1){
                        os.write(buffer,0,length);
                    }
                    is.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();


        }


    }
}

```

**访问效果：**

![1566446578300](imgs/08_BS%E6%9C%8D%E5%8A%A1%E5%99%A8%E6%A1%88%E4%BE%8B%E6%95%88%E6%9E%9C%E5%9B%BE.png)

**图解：**

![1566446643154](imgs/09_BS%E9%80%9A%E8%AE%AF%E5%9B%BE%E8%A7%A3.png)



# 第四章 Junit单元测试

## 4.1 什么是Junit

```java
Junit是什么
    *  Junit是Java语言编写的第三方单元测试框架(工具类)
    	框架: 半成品, 有了框架提高开发效率
    *  类库 ==> 类  junit.jar

单元测试概念
    * 单元：在Java中，一个类、一个方法就是一个单元
    * 单元测试：程序员编写的一小段代码，用来对某个类中的某个方法进行功能测试或业务逻辑测试。

Junit单元测试框架的作用
    * 用来对类中的方法功能进行有目的的测试，以保证程序的正确性和稳定性。
    * 能够让方法独立运行起来。

Junit单元测试框架的使用步骤
    * 编写业务类，在业务类中编写业务方法。比如增删改查的方法
    * 编写测试类，在测试类中编写测试方法，在测试方法中编写测试代码来测试。
        * 测试类的命名规范：以Test开头，以业务类类名结尾，使用驼峰命名法
            * 每一个单词首字母大写，称为大驼峰命名法，比如类名，接口名...
            * 从第二单词开始首字母大写，称为小驼峰命名法，比如方法命名
            * 比如业务类类名：ProductDao，那么测试类类名就应该叫：TestProductDao
        * 测试方法的命名规则：以test开头，以业务方法名结尾
            * 比如业务方法名为：save，那么测试方法名就应该叫：testSave

测试方法注意事项
    * 必须是public修饰的，没有返回值，没有参数
    * 必须使用JUnit的注解@Test修饰

如何运行测试方法
    * 选中方法名 --> 右键 --> Run '测试方法名'  运行选中的测试方法
    * 选中测试类类名 --> 右键 --> Run '测试类类名'  运行测试类中所有测试方法
    * 选中模块名 --> 右键 --> Run 'All Tests'  运行模块中的所有测试类的所有测试方法

如何查看测试结果
    * 绿色：表示测试通过
    * 红色：表示测试失败，有问题
```



## 4.2 Junit常用注解(Junit4.x版本)

    * @Before：用来修饰方法，该方法会在每一个测试方法执行之前执行一次。
    * @After：用来修饰方法，该方法会在每一个测试方法执行之后执行一次。
    * @BeforeClass：用来静态修饰方法，该方法会在所有测试方法之前执行一次，而且只执行一次。
    * @AfterClass：用来静态修饰方法，该方法会在所有测试方法之后执行一次，而且只执行一次。

## 4.3 Junit常用注解(Junit5.x版本)

     * @BeforeEach：用来修饰方法，该方法会在每一个测试方法执行之前执行一次。
     * @AfterEach：用来修饰方法，该方法会在每一个测试方法执行之后执行一次。
     * @BeforeAll：用来静态修饰方法，该方法会在所有测试方法之前执行一次。
     * @AfterAll：用来静态修饰方法，该方法会在所有测试方法之后执行一次。


- 示例代码(JUnit4.x)

  ```java
  package com.itheima05.junit;
  /*
  *  单元测试概念(程序员自己做)
   *      单元：在Java中，一个类、一个方法就是一个单元
   *      单元测试：程序员编写的一小段代码，用来对某个类中的某个方法进行功能测试或业务逻辑测试。
   *
   * 集成测试: 该单元集成到项目中, 整个项目运行测试 (后端程序)
   *
   * 联合测试(联调)
  * */
  public class MyMath {
  
      public static int add(int a,int b){
  //        int i = 1/0;
          return a+b;
      }
  
      public static int divide(int a,int b){
          return a/b;
      }
  }
  
  ```


```java
package com.itheima05.junit;

import org.junit.*;

/*
*  # 单元测试的软性规范
*   1. 类名大驼峰, 测试类+Test
*   2. 测试方法小驼峰, test +方法名
*
*  # junit单元测试框架的使用硬性规范
*   1. 测试方法必须要加测试注解(比如@Test)
*   2. 方法必须public修饰,必须无参,必须无返回值
*
*   使用小技巧:
*       1. 鼠标放在方法内,只运行当前测试方法
*       2. 鼠标放在类中方法外,运行当前测试类所有测试方法
*
*
*  junit4.0
*   @Before：用来修饰方法，该方法会在每一个测试方法执行之前执行一次。
*       每个步骤的初始化工作
 *  @After：用来修饰方法，该方法会在每一个测试方法执行之后执行一次。
 *      每个步骤的善后工作: 释放资源,序列化数据
 *
 *  @BeforeClass：用来静态修饰方法，该方法会在所有测试方法之前执行一次，而且只执行一次。
 *
  * @AfterClass：用来静态修饰方法，该方法会在所有测试方法之后执行一次，而且只执行一次。
* */
public class MyMathTest {
    @BeforeClass
    public static void beforeClassTest(){
        System.out.println("在@Test方法之前运行仅一次");
    }
    @AfterClass
    public static void afterClassTest(){
        System.out.println("在@Test方法之后运行仅一次");
    }
    @Before
    public void beforeTest(){
        System.out.println("在每一个@Test方法之前都会运行一次");
    }
    @After
    public void afterTest(){
        System.out.println("在每一个@Test方法之后都会运行一次");
    }
    @Test
    public void testAdd(){
        int sum = MyMath.add(1, 2);
        /*
        *   assertEquals(String message,expected,actual)
        *   断言 : 预言
        *   1). message : 如果实际不符合预期,报错,提示这个message
        *   2). expected : 预期
        *   3). actual : 实际
         * */
        Assert.assertEquals("你的结果不符合我的预期",4,sum);

        System.out.println(sum);
    }

    @Test
    public void testDivide(){
        int result = MyMath.divide(4,2);
        System.out.println(result);
    }
}

```



示例代码(JUnit5.x)