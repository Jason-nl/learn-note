# 第9章 传智健康权限管理

学习目标：

1. 分布式开发单点登录说明
2. 传智健康实现用户token认证和授权

## 9.0 单点登录

前景说明：早期的公司，一家公司可能只有一个Server，慢慢的Server开始变多了。

每个Server都要进行注册登录，退出的时候又要一个个退出。用户体验很不好！

你可以想象一下，上豆瓣 要登录豆瓣FM、豆瓣读书、豆瓣电影、豆瓣日记......真的会让人崩溃的。

我们想要另一种登录体验：**一家企业下的服务只要一次注册，登录的时候只要一次登录，退出的时候只要一次退出。怎么做？**

解决方案：

单点登录（**Single Sign On**），简称为 SSO，是比较流行的企业业务整合的解决方案之一。

SSO的定义是在多个[应用](https://baike.baidu.com/item/应用/3994271)系统中，用户只需要登录一次就可以**访问所有相互信任的应用系统**。

SSO 为开发人员提供了一个通用的身份验证框架，开发人员就完全不需要为身份验证操心。

![1603510229735](tps/1603510229735.png) 

说明：

采用**令牌(token)**的形式。

具体而言：

1. 当用户请求访问应用系统A的服务资源时，应用系统A的CAS服务器对用户身份进行验证，并在验证成功后生成一令牌返回给用户；

2. 当用户请求访问应用系统B的服务资源时，将接收到的令牌传递给应用系统B，应用系统B的CAS服务器通过解密该令牌以获取用户信息，从而实现用户登录。
3. 基于这种方式，采用令牌进行验证的好处是，各个服务器都通过统一的密钥对令牌进行加密解密，因此具有安全性高、稳定性好、以及性能消耗低等优点。



## 9.1 JWT学习路径

**问题： 那么如何实现单点登录呢？**

**全写JSON Web Token, 是开放的行业标准RFC7591，用来实现端到端安全验证.**

```mark
Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准
该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。

JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。
```

### 起源 ：

 说起JWT，我们应该来谈一谈基于token的认证和传统的session认证的区别。

#### 传统的session认证

我们知道，http协议本身是一种无状态的协议，而这就意味着如果用户向我们的应用提供了用户名和密码来进行用户认证，那么下一次请求时，用户还要再一次进行用户认证才行，因为根据http协议，我们并不能知道是哪个用户发出的请求，所以为了让我们的应用能识别是哪个用户发出的请求，我们只能在服务器存储一份用户登录的信息，这份登录信息会在响应时传递给浏览器，告诉其保存为cookie,以便下次请求时发送给我们的应用，这样我们的应用就能识别请求来自哪个用户了,这就是传统的基于session认证。

但是这种基于session的认证使应用本身很难得到扩展，随着不同客户端用户的增加，独立的服务器已无法承载更多的用户，而这时候基于session认证应用的问题就会暴露出来.

#### 基于session认证所显露的问题

**Session**: 每个用户经过我们的应用认证之后，我们的应用都要在服务端做一次记录，以方便用户下次请求的鉴别，通常而言session都是保存在内存中，而随着认证用户的增多，服务端的开销会明显增大。

**扩展性**: 用户认证之后，服务端做认证记录，如果认证的记录被保存在内存中的话，这意味着用户下次请求还必须要请求在这台服务器上,这样才能拿到授权的资源，

**这样在分布式的应用上，相应的限制了负载均衡器的能力。这也意味着限制了应用的扩展能力。**



### 基于token的鉴权机制

基于token的鉴权机制类似于http协议也是无状态的，它不需要在**服务端去保留用户的认证信息或者会话信息**。这就意味着基于token认证机制的应用不需要去考虑用户在哪一台服务器登录了，这就为应用的扩展提供了便利。

流程上是这样的：

- 用户使用用户名密码来请求服务器
- 服务器进行验证用户的信息
- 服务器通过验证发送给用户一个token
- 客户端存储token，并在每次请求时附送上这个token值
- 服务端验证token值，并返回数据

### JWT长什么样

JWT是由三段信息构成的，将这三段信息文本用`.`链接一起就构成了Jwt字符串。

就像这样:

```css
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```

### JWT的构成

第一部分我们称它为头部（header),第二部分我们称其为**载荷**（payload, 类似于飞机上承载的物品)，第三部分是签证（signature).

![1601555402218](tps/1601555402218.png) 

#### header

jwt的头部承载两部分信息：

- 声明类型，这里是jwt
- 声明加密的算法 通常直接使用 HMAC SHA256

完整的头部就像下面这样的JSON：

```bash
{
  'typ': 'JWT',
  'alg': 'HS256'
}
```

然后将头部进行base64加密（该加密是可以对称解密的),构成了第一部分.

```undefined
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
```

#### playload

载荷就是存放有效信息的地方。这个名字像是特指飞机上承载的货品，这些有效信息包含三个部分

- 标准中注册的声明
- 公共的声明
- 私有的声明

**标准中注册的声明** (建议但不强制使用) ：

-  **iss**: jwt签发者
-  **sub**: jwt所面向的用户
-  **aud**: 接收jwt的一方
-  **exp**: jwt的过期时间，这个过期时间必须要大于签发时间
-  **nbf**: 定义在什么时间之前，该jwt都是不可用的.
-  **iat**: jwt的签发时间
-  **jti**: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。

**公共的声明** ：
 公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.

**私有的声明** ：
 私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。

定义一个payload:

```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}
```

然后将其进行base64加密，得到Jwt的第二部分。

```undefined
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9
```

#### signature

jwt的第三部分是一个签证信息，这个签证信息由三部分组成：

- header (base64后的)
- payload (base64后的)
- secret

这个部分需要base64加密后的header和base64加密后的payload使用`.`连接组成的字符串，然后通过header中声明的加密方式进行加盐`secret`组合加密，然后就构成了jwt的第三部分。

```csharp
// javascript
var encodedString = base64UrlEncode(header) + '.' + base64UrlEncode(payload);

var signature = HMACSHA256(encodedString, 'secret'); // TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```

将这三部分用`.`连接成一个完整的字符串,构成了最终的jwt:

```css
  eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```

**注意：secret是保存在服务器端的，jwt的签发生成也是在服务器端的，secret就是用来进行jwt的签发和jwt的验证，所以，它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。**

### JWT如何应用

一般前端系统在请求头里加入`Authorization`，并加上`Bearer`标注：

```bash
fetch('api/user/1', {
  headers: {
    'Authorization': 'Bearer ' + token
  }
})
```

服务端会验证token，如果验证通过就会返回相应的资源。

整个流程就是这样的:

![1601532878027](tps/1601532878027.png) 



### JWT优点

- 因为json的通用性，所以JWT是可以进行跨语言支持的，像JAVA,JavaScript,NodeJS,PHP等很多语言都可以使用。
- 因为有了payload部分，所以JWT可以在自身存储一些其他业务逻辑所必要的非敏感信息。
- 便于传输，jwt的构成非常简单，字节占用很小，所以它是非常便于传输的。
- 它不需要在服务端保存会话信息, 所以它易于应用的扩展



### JWT安全相关

- 不应该在jwt的payload部分存放敏感信息，因为该部分是客户端可解密的部分。
- **保护好secret私钥，该私钥非常重要**。
- 如果可以，请使用https协议





## 9.3 公钥和私钥生成

问题：那么如何对用户认证数据进行安全性加密呢？

方案：**jwt采用RSA非对称加密对数据进行加密**。

![1601609671455](tps/1601609671455.png)

![1601555644627](tps/1601555644627.png) 

![1601609785068](tps/1601609785068.png)

实现步骤：将老师下发资料 ： 

![1601534693915](tps/1601534693915.png) 

导入common模块：

![1601534628061](tps/1601534628061.png) 

- 在自己的电脑磁盘目录下 **手动创建一个目录 keys**

![1601534735309](tps/1601534735309.png) 

- commons模块导入依赖：

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/joda-time/joda-time -->
<dependency>
    <groupId>joda-time</groupId>
    <artifactId>joda-time</artifactId>
    <version>2.9.9</version>
</dependency>
```

- 生成公钥和私钥： - 执行工具类：  main方法

![1601534209723](tps/1601534209723.png) 

```java
public static void main(String[] args) throws Exception {
    // 公钥和私钥的地址
    String publicKeyFilename = "D:/keys/id_rsa.pub";
    String privateKeyFilename = "D:/keys/id_rsa";
    // 密文
    String secret = "HelloWorld";
    // 生成公钥和私钥
     generateKey(publicKeyFilename, privateKeyFilename, secret);

    PublicKey publicKey = getPublicKey(publicKeyFilename);
    System.out.println("publicKey = " + publicKey);

    PrivateKey privateKey = getPrivateKey(privateKeyFilename);
    System.out.println("privateKey = " + privateKey);
}
```

效果：

![1601534150502](tps/1601534150502.png) 

## 9.4 JWT加密和解密

- 基于RSA，完成jwt加密和解密实现： 打开工具类 ： 执行main方法

![1601534285011](tps/1601534285011.png) 

- 对数据进行**私钥加密和公钥解密**代码：

```java
   public static void main(String[] args) throws Exception {
        // 公钥和私钥的地址
        String publicKeyFilename = "D:/keys/id_rsa.pub";
        String privateKeyFilename = "D:/keys/id_rsa";
        // 获取公钥和私钥
        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyFilename);
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyFilename);
         //  定义荷载：加密用户对象    生成token
        UserInfo userInfo = new UserInfo(2L, "Jack","a-b-c");
        String token = generateToken(userInfo, privateKey, 5);
//        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywibmFtZSI6IkphY2sgTWEiLCJleHAiOjE1MTYyMzkwMjJ9.gom8PyFSPbNwkikLekYGpndgPTM8_REXvsk7IQ-ErtAY8m2vyIBAwD1k3GcA2xGCan-Orf65EtX0REbtuOtb2qlNyXrxtd-Hv-ttX94_SnPGf8rpnwiMIbul3fzdUoV4bNdhDnMP2hmhNVSmemvw_AQgfnqlgVNd1MY3IHIwHNE";
        System.out.println("token = " + token);
        //   从token中 获取用户信息， 解密操作
        UserInfo user = getInfoFromToken(token, publicKey);
        System.out.println(user);
    }
```

私钥加密：代码 

```java
/**
 * 私钥加密token
 *
 * @param userInfo      载荷中的数据
 * @param privateKey    私钥
 * @param expireMinutes 过期时间，单位秒
 * @return
 * @throws Exception
 */
public static String generateToken(UserInfo userInfo, PrivateKey privateKey, int expireMinutes) throws Exception {
    return Jwts.builder()
            .claim(JwtConstants.JWT_KEY_ID, userInfo.getId())
            .claim(JwtConstants.JWT_KEY_USER_NAME, userInfo.getUsername())
            .claim(JwtConstants.JWT_KEY_USER_ROLE, userInfo.getRoles())
            .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact();
}
```

公钥解密：代码

```java
/**
 * 获取token中的用户信息
 *
 * @param token     用户请求中的令牌
 * @param publicKey 公钥
 * @return 用户信息
 * @throws Exception
 */
public static UserInfo getInfoFromToken(String token, PublicKey publicKey) throws Exception {
    Jws<Claims> claimsJws = parserToken(token, publicKey);
    Claims body = claimsJws.getBody();
    return new UserInfo(
            ObjectUtils.toLong(body.get(JwtConstants.JWT_KEY_ID)),
            ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME)),
            ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_ROLE))
    );
}
```



## 9.5 Spring Security整合传智健康后台项目

### 准备工作

插入表数据

在资料QL脚本中，执行其中sql文件导入数据：为了不需要重新再添加数据，因此使用sql脚本将表中的数据重新初始化一下。

parentMenuId

注意：**先一个一个初始化t_menu.sql、t_orle.sql、t_user.sql、t_permisson.sql，**再初始化中间表

![](media/f399be5a034dccfe56a423208aac0483.png) 

导入脚本数据到数据库

### 第一步：前端系统打开登录页面

前端系统 打开登录页面  login.vue 

![](media/cf1b1877964c86a582f15173c659b3ba.png)

### 第二步：前端编写登录业务  

login.vue组件中，编写登录业务方法

```js
  login:function(){
      //跳转到主页
      // this.$router.replace({ name: "index" });

      this.$refs.loginForm.validate((valid) =>{
        if(valid){
          this.$http.post('api/login',this.userInfo).then(res =>{
            console.info(res.data.message)
            if(res.data.flag){
              // 用户认证成功 将用户信息和token存储起来
              window.sessionStorage.setItem("token",res.data.message)
              window.sessionStorage.setItem("username",this.userInfo.username)
              //认证成功，跳转到主页
              this.$router.replace({ name: "index" });
            }else{
              this.$message.error(res.data.message);
            }
          });
          
        }
      });
    },
```



### 第三步：前端系统存储方案：路由拦截器

将认证成功之后的token存储在一个全局对象中，便于后续发送请求，携带token 给服务器端

main.js中编写请求拦截器： 后续只要ajax发送请求，必然将token以headers形式传递给服务器端

```js
//  定义异步请求 拦截器 所有的axios请求 发送之前 都会携带 headers.authentication  token数据
axios.interceptors.request.use(config => {
  console.log(config)
  config.headers.authentication = window.sessionStorage.getItem("token")
  return config;
}, 
function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
}

)
```

如果服务端认证失败，会将信息回送给前端系统！

我们需要在前端系统添加响应处理业务：main.js中添加 响应拦截器请求：获取服务器端信息，进行路由跳转

```js
// http response 拦截器
axios.interceptors.response.use(
  response => {
     //  对响应数据 处理
      return response;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
          case 403:
              // 返回 403 转到登录页面
               //跳转到主页
               window.sessionStorage.removeItem("token")
               router.replace({ name: "login" });
      }
  }
       
      return Promise.reject(error.response.data)   // 返回接口返回的错误信息
  });
```

### 第四步：前端系统-静态资源路由拦截

当用户在浏览器直接输入后台地址，默认可以访问！

正常请求，用户未认证鉴权是不可以通过浏览器地址访问前端资源的！

判断的唯一标准就是：当前请求是否包含token 

main.js中我们需要添加静态资源：路由拦截 

```js
//  定义路由拦截
  // 语法 ：router.beforeEach（）一般用来做一些进入页面的限制。比如没有登录，就不能进入某些页面，
  // 只有登录了之后才有权限查看某些页面。  to 表示需要访问的目标router  next 表示可以访问

  router.beforeEach((to,from,next)=>{
    if(to.name != 'login'){
      if(window.sessionStorage.getItem("token")){
        next()
      }else{
        next({path:'/'})
      }
    }else{
      next()
    } 
  })
```

**router.beforeEach()方法**： 表示路由跳转之前，会被先调用！

其中： to就是访问的目标路由，next方法表示即将访问的路由组件

### 第五步：后台系统重构过滤器对象

目标： 完成用户鉴权，生成token，回传给前端！

后台重构的两个过滤器介绍：

```markdown
- 用户验证：UsernamePasswordAuthenticationFilter去进行用户账号的验证  -  认证

- 鉴权：BasicAuthenticationFilter去进行用户权限的验证  

JWTAuthenticationFilter继承于UsernamePasswordAuthenticationFilter
- 用于获取用户登录的信息，创建一个token并调用authenticationManager.authenticate()让spring-security去进行验证
- 
核心功能是在验证用户名密码正确后，生成一个token，并将token返回给客户端
该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法：
attemptAuthentication ：接收并解析用户凭证。
successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。


JWTAuthorizationFilter继承于BasicAuthenticationFilter用于权限验证，，每一次请求都需要检查该用户是否有该权限去操作该资源

- 在doFilterInternal方法中，从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。如果校验通过，就认为这是一个取得授权的合法请求

```

**认证通过后，服务器的过滤器对象JWTAuthenticationFilter生成一个token，并将token返回给客户端，**

**客户端接收token之后，以后的所有请求都需要在http头中指定该token。**

**服务器接收的请求后，JWTAuthorizationFilter会对token的合法性进行验证。验证的内容包括：JWT格式、检查签名、检查claims、检查权限**

认证和鉴权的流程图说明：

https://www.processon.com/diagraming/5f8ede4e7d9c0806f2832d58

![1604031668469](tps/1604031668469.png) 

后台代码操作具体步骤实现： 

下发的资料： 两个过滤器对象 直接导入到传智健康-web项目环境中：

![1601610388444](tps/1601610388444.png) 

- 配置 RSA 公钥和私钥文件磁盘地址： yaml文件中

```yaml
#  公钥和私钥 生成地址
health:
  jwt:
    pubKeyPath: D:/keys/id_rsa.pub # 公钥地址
    priKeyPath: D:/keys/id_rsa # 私钥地址
    expire: 30 # 过期时间,单位分钟
```

- **WebSecurityConfigurerAdapter**引入过滤器对象和配置 ： 启用我们自己的过滤器对象完成用户鉴权！

```java
/**
 * WebSecurityConfigurerAdapter  作用 ： 留给开发者  重写 框架的默认配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)//   开启权限注解配置
@EnableConfigurationProperties(JwtProperties.class)
public class HealthWebSecurityAdapter  extends WebSecurityConfigurerAdapter {

    //    spring ioc  容器 获取   UserDetailsService 接口实现类
    @Autowired
    HealthUserServiceDetailsImpl healthUserServiceDetailsImpl;
    @Autowired
    private JwtProperties properties;
```

- 配置过滤器对象生效： 重写 configure方法  --  使用addFilter方法添加自重写的两个过滤器

```java
/**
 *   重写 登录  前端一般采用 ajax请求提交用户数据
 *    配置过滤器完成用户认证和授权 
 *    以token形式返回
 */

@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().
            disable().
            addFilter(new JWTAuthenticationFilter(super.authenticationManager(),properties))
            .addFilter(new JWTAuthorizationFilter(super.authenticationManager(),properties))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session
}
```

- 用户鉴权测试： 在checkitem模块中 使用权限配置注解： 完成认证和授权测试

![1601610699929](tps/1601610699929.png) 

基于  apizza完成 用户分布式鉴权测试！

先认证：输入地址：  

![1601610788559](tps/1601610788559.png) 

再进行权限设置： 手动修改  authentication的值 可以发现：

![1601610749378](tps/1601610749378.png)

如果 authentication值一致，正确，则可以正常访问：

![1601610861800](tps/1601610861800.png) 

至此我们完成分布式用户鉴权！

# 当天小结

- 了解jwt-单点登录流程说明
- 掌握数据库版用户认证和权限业务代码
- 掌握传智健康整合spring-security 



