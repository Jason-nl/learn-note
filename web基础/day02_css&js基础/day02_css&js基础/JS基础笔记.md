---
typora-copy-images-to: img
---



# JS基础

```markdown
# 今日目标
1. js基础语法
2. js函数（方法）
3. js事件【重点】
4. js内置对象
```



# 一. JavaScript概述

**JavaScript作用**

JavaScript是web上一种功能强大的编程语言，用于开发交互式的web页面

**JavaScript历史**

- **起源：**上世纪末1995年时，Netscape（网景）公司推出Navigator浏览器。发布后用的人不多，这咋整啊？这家公司就想了一个好方案，不仅在浏览器实现静态HTML，还想要有动态效果，比如：在前端处理表单验证。
- **动手：**有目标就去做，网景公司大牛多，Brendan Eich（布兰登·艾奇）据说就用了10天就把JavaScript搞了出来，刚出来时叫LiveScript，为了蹭蹭当红明星Java热度，就改成JavaScript了（瞬间就火了），事实上他们两没啥关系。（雷锋和雷峰塔）
- **竞争：**看到网景公司有了js，微软感觉不行啊，我的IE要被干掉啊，同时也感到js的前途无量，于是参考JavaScript弄了个名为JScript浏览器脚本语言。
- **标准：** Netscape和微软竞争出现的js导致版本的不一致，随着业界的担心，JavaScript的标准化被提上议事日程。ECMA（欧洲计算机制造商协会）组织就去干这事，最后在1997年弄出了ECMAScript作为标准。这里ECMAscript和JavaScript可以看做表达的同一个东西。



**JavaScript特点**

1. 它不需要进行编译，而是直接嵌入在HTML页面中，**由浏览器执行**。

2. JavaScript 被设计用来向 HTML 页面添加交互行为

3.  JavaScript **是一种脚本语言**（脚本语言是一种轻量级的编程语言）。 

   说明：脚本语言就是一种轻量级的编程语言。一般都由相应的脚本引擎来[解释执行](https://www.baidu.com/s?wd=%E8%A7%A3%E9%87%8A%E6%89%A7%E8%A1%8C&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1Y3uhmdPjmvrH6sPjbknWT30ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHDknH0vnH01PH0YPHmknH04rf)。例如JavaScript语言就由浏览器引擎来解析和执行。

   在计算机上不需要安装其他的软件。而java语言就不是脚本语言，必须在计算机安装jdk之后才可以运行。

   脚本语言除了JavaScript 语言，还有PHP,Python等语言。

4. **重点强调一下：JavaScript** **(js)是弱类型语言，js变量声明不需要指明类型(**。**而java属于强类型语言。**

   **例如java中定义变量时会根据不同的数据类型书写不同的数据类型的关键字，而js不是。**



 **JavaScript组成**

| **组成部分**    | **作用**                                                     |
| --------------- | ------------------------------------------------------------ |
| **ECMA Script** | 构成了JS核心的语法基础                                       |
| **BOM**         | Browser Object Model 浏览器对象模型，用来操作浏览器上的对象  |
| **DOM**         | Document Object Model 文档对象模型，用来操作网页中的元素（标签） |

![image-20200817111407482](img\image-20200817111407482.png)



# 二 JavaScript入门

## 2.1  **JavaScript的引入方式** **（重点）**

我们的html文件是专门用来书写html语言的。所以我们的js代码是不能随意的书写在html文件中的。它在html中有它特别的位置。而我们接下来要学习的就是js和html结合的方式。

在一个html页面中引入js的方式有两种：

**1、** **内部js**：也就是在html源码中嵌入js代码

语法格式：

```javascript
<script type="text/javascript">
			这里写你的js代码
</script>
```

**注意：**

​	**1、<script>标签可以写在html页面中的任何地方。而且一个页面中可以有多个<script>标签。**

​	**2、type="text/javascript"可以省略**

经验： 

​	一般我们的JS是需要操作DOM元素的也就是页面上的标签，如果JS代码写在页面上面，想通过js代码获取某个标签是拿不到的。因为JS是阻塞式加载语言，就是必须是自上往下加载，有点类似单线程。当浏览器在执行js[代码](http://www.xuebuyuan.com/)时，不能同时做其它事情，即<script>每次出现都会让页面等待脚本的解析和执行，JS代码执行完成后，才继续加载页面。

​	所以这种方式引入JS我们的代码一般是写在body标签的下面的 。因为只有整个页面即html中的所有标签加载完成之后才可以使用js代码操作标签。而css属于并行加载语言，所以可以书写在head标签中。

问题：

​	假设多个页面都要用到同样的js代码的时候，我们可以在每个js页面复制一份。但是当我们需要对js代码进行修改的时候，这个时候需要在每个页面都进行修改，非常麻烦，所以上面这种方法不能提高js的复用性。接下来我们要学习的就是js与html的另一种结合方式，也就是引入外部js文件。

​		

**2、** **外部js**：也就是将js代码单独写成一个js文件(扩展名是 .js而不是 .javascript), 在html代码中引入这个封装好的js文件

语法格式：

```javascript
<script type="text/javascript" src="xxx.js"></script>
```

**注意：无论是内部js还是外部js在html中引入的位置是随意的，<head>标签中可以引入，<body>标签一样可以引入**

经验： 

因为JS是阻塞式加载（当加载JS的时候啥事都不能做，只能去加载JS）。如果JS比较多，这样我们的页面就得不到加载，可能会出现白屏现象，所以一般我们引入外部JS文件也都是写到body的下面。

## 2.2 JS三种输出方式

java可以输出到idea的控制台，而js需要浏览器来解析，所以我们想输出某个变量的值必须输出到浏览器中。

JS三种输出方式

    1. alert(输出信息);页面弹出框 
              框输出字符 (会抢夺焦点)
              (一般电脑屏幕只允许一个焦点)
    
    2. 直接输出到浏览器的控制台中：console.log(输出信息);
       说明：console表示控制台。log表示日志。
          但是运行看不到任何效果，必须在浏览器页面中按f12才可以看到。
    3. 输出html内容到页面  document.write(输出信息);
       (系统换行符 \r\n 浏览器不识别, 浏览器识别<br>标签 )

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>02-JS三种输出方式</title>

</head>
<body>
    aa <br>
    bb
     <script>
         // alert("警告:今晚有鱼~~")

        document.write("哈哈,网页输出");
        document.write("<br>");
        document.write("哈哈,网页输出2");

         console.log("浏览器控制台"); // 相当于 system.out.print()
     </script>

</body>
</html>
```



## 2.3 JS变量声明

java是强类型语言，注重变量的定义，所以在java中定义变量类型的方式如下：

```java
// 整型
int i = 1314;
// 浮点型
double d = 521.1314;
// 字符型
char c ='c';
// 字符串型
String str = "用心做教育";
//布尔型
boolean b = true;
// 常量
final Integer PI = 3.14;
```

js是弱类型语言，不注重变量的定义，所以在js中定义变量的类型方式如下：

   js : 弱类型语言，变量有类型,但是不需要在定义时声明
          js的基础语法: ECMAScript 简称ES
        1.es5 : var (variable) 变量
                   var有一些作用域问题,被淘汰了
        2.es6 :
                   **1. let  定义变量(允许改变)**

​				   2. const 定义常量(恒定不变)

  3.js代码不需要编译,直接浏览器解释运行
​                   (js有没问题,最终参考的是浏览器)

  4.      为什么idea会提示let/const 报错呢?
                因为idea默认使用es5的语法来检查js代码， 我们要换成es6
  5.      在js中没有字符类型概念。所以单引号和双引号表示的是一个意思。



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>03-JS变量声明</title>

</head>
<body>

<script>
        /*
        *   java: 强类型语言
        *       定义变量的时候,必须声明类型
        * */
    // String str = "用心做教育";
    // int i = 1314;
    // double d = 521.1314;
    // final Integer PI = 3.14;
    // boolean b = true;

       
        var str = "用心做教育"
        var i = 1314
        var PI = 3.14
        console.log(str)

        let str2 = '用心做教育'
        let i2 = 1314
        const PI2 = 3.14
        console.log(PI2)
</script>
</body>
</html>
```

说明：

1.为什么idea会提示let/const 报错呢?

![image-20200818212747735](img\image-20200818212747735.png)

因为idea默认使用es5的语法来检查js代码，我们要换成es6。

![image-20200818212933275](img\image-20200818212933275.png)

![image-20200818213039258](img\image-20200818213039258.png)

**2.在js中没有字符串类型概念，只有字符类型。所有在JS中字符串的表示使用单引号和双引号都可以。开发中使用单引号相对来说多一些。**

3.问题：在js中，由于定义任意的数据类型都使用let关键字。那么怎么去查看某个变量的数据类型呢？ 使用关键字： **typeof**。

使用格式：  **typeof 变量名 **

代码示例：

![](img\4-1575512537294-1597757942975.bmp)

实现效果：

![](img\5-1575512537294-1597757942976.bmp)

**注意：typeof 变量名，返回值都是字符串类型。**



## 2.4 JS数据类型

跟java一样，js的数据类型也分为**基本数据类型**(原始数据类型)和**引用数据类型**(复合数据类型)，而JS中一共有6种数据类型，其中5种基本数据类型和1种引用数据类型。

**1、** **基本数据类型（原始数据类型）**

​	数字类型：number       包含了小数和整数

​	布尔类型：boolean       只有两个值： true（真）| false（假）

​	字符串类型：String       包含字符和字符串，既可以使用双引号又可以使用单引号

​	未定义类型：undefined   只有一个值，即 undefined未定义

​	空类型：null             只有一个值 null，表示空对象

​	**补充**:值 undefined 实际上是从值 null 派生来的，因此 ECMAScript 把它们定义为相等的。**alert(null == undefined);  输出 "true"，**尽管这两个值相等，但它们的含义不同。

**2、** **引用数据类型(复合数据类型)**

object : 对象

与java一样，js中的对象都是引用数据类型，所以你也可以使用我们所熟知的**new**关键字来“创建对象”。

例如：

创建一个上帝对象：  **var** obj = new Object();

创建一个日期对象：  **var** date = new Date();





# 三 JavaScript基础语法【记住不同点】

## 3.1 JS运算符

在js中，JavaScript运算符与Java运算符基本一致。

​	1、赋值运算符：等号(=), 举例： var x = 5。

​	2、比较运算符： >  <  >= <=   ==  !=   ===  !== 

​		 == 和 === 的区别：

​			两者都可以用来比较两个变量的值是否相等。

​			== 先做数据类型的转换，再进行比较。比如可以将整数转换为字符串，或者将字符串转换为整数等。

​			=== 全等 严格的比较，如果符号两侧的数据类型不一致，则立刻返回false。

代码示例：

![](img\6-1597758325380.bmp)

实现效果：

![](img\7-1597758325380.bmp)

!= 和 !== 的区别：

​	!= 在表达式两边的数据类型不一致时,会隐式转换为相同数据类型,然后对值进行比较.

​	!== 不会进行类型转换,在比较时除了对值进行比较以外,还比较两边的数据类型,它是恒等运算符===的非形式.

案例代码如下：

![](img\8-1597758325380.bmp)

实现效果：

![](img\9-1597758325380.bmp)

​	3、逻辑运算符：  &&  ||   ！ 

​		&&  并且，双与：只有运算符两侧都为true，结果才是true； 一假即假。

​		||    或者，双或：只要运算符两侧有一个是true，结果就是true；一真即真。

​		!    取反： !true = false;  !false=true;

**需要注意的是**，在js中，不光boolean值能够参与逻辑运算。所有的值都能参与逻辑运算.

代码示例：

![](img\10-1597758325380.bmp)

总结一下JS中**6个假的：**

```
数字0， 空字符串‘’或者"", false,  空null,  undefined,  NaN
```

NaN:  Not a Number 不是一个数字

例如： 1 -‘abc’ -> 表示1减去字符’abc’--》不属于一个数字--》NaN

案例代码：

![](img\11-1597758325380.bmp)

​	4、算数运算符： +  -  *  /  % 

​	**注意： + 号比较特殊 因为除了可以进行算数运算，还可以进行字符串的拼接。这点和java是一样的。**

​	5、三元运算符：  条件表达式1 ? 条件表达式2 : 条件表达式3; 

​	![](img\12-1597758325381.bmp)



## 3.2 JS流程控制

跟java一样，js也存在if、if-else、switch、for、while、do-while等逻辑语句，与java使用方式一致，此处不再一一阐述



## 3.3 var和let的区别(了解)

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>05-经验值分享</title>

</head>
<body>

<script>
    // for循环举例：var声明变量全局作用域、let声明变量是局部作用域
    for (var i = 0; i < 5; i++) {
        document.write('<h3>我是var修饰遍历的内容</h3>')
    }
    console.log(i);

    for (let j = 0; j < 5; j++) {
        document.write('<h3>我是let修饰遍历的内容</h3>')
    }
    // console.log(j);

    {
        var a = 10;
        let b = 5;
    }
    console.log(a); // 可以取到
    console.log(b); // 不能取到

</script>
</body>
</html>
```





# 四 JS函数【方法】

在java中,当我们需要多次使用一段代码的功能的时候，我们可以使用方法来封装这段代码。当然，在js中我们也可以使用函数来封装一段js代码。下面一起来学习js中函数。

js的函数是js非常重要的组成部分，js最常用的函数的定义方式有两种，需要注意的是在js中，通过**function**关键字来定义函数



## 4.1 普通函数【重点】

语法格式：

```javascript
function 函数名(参数列表) {//形参类型不需要书写，直接书写变量名即可
js逻辑代码
}
函数调用：函数名(实际参数);
一定要加上小括号
```

js中函数中有一些与我们java中不一样的地方。我们**重点学习**如下这些不一样的地方。因为下面4点会涉及到我们日常的开发中。

1、 函数需要被调用才能执行。

2、 js中，如果函数需要返回值我们直接return就行了。

​		定义函数的时候不需要声明返回值的类型，因为js是弱数据类型，返回什么数据类型都是let。

3、 在js中，如果我们需要给函数传递参数，那么我们直接写变量名就行，不需要声明传入变量的类型。

4、 在js中，不存在函数重载的概念，如果2个函数名相同，后面出现的函数会覆盖前面的函数。

 

上述js函数的注意事项代码示例如下：

```html
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>

	<body>
		<script type="text/javascript">
			/*
			1、函数需要被调用才能执行。
			2、js中，如果函数需要返回值我们直接return就行了。
					定义函数的时候不需要声明返回值的类型，因为js是弱数据类型，返回什么数据类型都是let。
			3、在js中，如果我们需要给函数传递参数，那么我们直接写变量名就行，不需要声明传入变量的类型。
			4、在js中，不存在函数重载的概念，如果2个函数名相同，后面出现的函数会覆盖前面的函数。
			*/
			/*
			 * 函数定义格式：
			 * function 函数名(参数列表)
			 * {}
			 */
			//定义一个函数
			function fn()
			{
				console.log(12);
			}
			//1、函数需要被调用才能执行。
			fn();
			//2、js中，如果函数需要返回值我们直接return就行了。
			//定义函数的时候不需要声明返回值的类型，因为js是弱数据类型，返回什么数据类型都是var。
			//定义一个函数
			function fn1()
			{
				return 'hello';
			}
			//调用函数
			var result=fn1();
			console.log(result);
			//3、在js中，如果我们需要给函数传递参数，那么我们直接写变量名就行，不需要声明传入变量的类型。
			//定义一个函数
			function fn2(a)
			{
				console.log(a);
			}
//			fn2(3);
			fn2('hello');
			//4、在js中，不存在函数重载的概念，如果2个函数名相同，后面出现的函数会覆盖前面的函数。
			//定义一个函数
			function fn3()
			{
				console.log('world');
			}
			function fn3()
			{
				console.log('js');
			}
			//调用函数
			fn3();//;'js'
		</script>
	</body>
</html>
```

实现效果：

![](img\13-1597758539446.bmp)

下面这些点大家**自行学习，**因为如下的不同之处我们正常开发很少去触及。**了解一下就行**

1、 如果函数的声明带有参数，但是调用时不带参数，函数也可以正常被调用。

2、 如果函数声明时没有参数列表，调用的时候传入参数，函数也能照样执行。

3、 在JS中,可以使用arguments来获取实际传入的参数。arguments是实参的参数数组。

代码示例：

```javascript
//5、如果函数的声明带有参数，但是调用时不带参数，函数也可以正常被调用。
			//定义带有参数的函数
			function fn4(a,b)
			{
				console.log(a+'函数被执行了'+b);
			}
			//调用函数
			//注意：如果不传递任何数据，参数显示是undefined
			fn4();//undefined函数被执行了undefined
			let c;
			console.log(c);//undefined
			//6、如果函数声明时没有参数列表，调用的时候传入参数，函数也能照样执行。
			//定义函数
			function fn5()
			{
				console.log('函数被执行了');
			}
			//调用函数
			fn5(14,23);//函数被执行了
			//7.在JS中,可以使用arguments来获取实际传入的参数。arguments是实参的参数数组。
			//定义函数
			function fn6()
			{
//				console.log(arguments);
				//遍历数组
				for (var i = 0; i < arguments.length; i++) {
					console.log(arguments[i]);
				}
			}
			//调用自定义函数
			fn6(12,34);
```

实现效果：

![](img\14-1597758539446.bmp)



## 4.2 匿名函数

匿名函数也就是没有名字的函数

语法格式：

```javascript
function(参数列表){
js逻辑代码
}
```

匿名函数**没有办法直接调用**，一般情况下匿名函数有两种使用场景：

**第一种：** 将匿名函数赋值给一个变量，使用变量调用函数。

​	定义函数并赋值给变量：

```javascript
let fn = function(参数列表) {
js逻辑代码
}；
```

调用函数：fn(实际参数);

上述匿名函数代码演示如下所示：

```javascript
function fn6()
{
//				console.log(arguments);
//遍历数组
for (let i = 0; i < arguments.length; i++) {
console.log(arguments[i]);
}
}
//调用自定义函数
fn6(12,34);
/*
* 匿名函数：格式：
* function(参数列表){
* 	
* }
* 匿名函数没有名字，不能直接让匿名函数执行，让匿名函数执行，有两种方式：
* 1.将匿名函数赋值给一个变量，然后通过变量名(参数列表)方式调用
*/
//定义一个匿名函数
let fn7=function()
{
console.log('匿名函数被执行了');
}
//执行上述匿名函数
fn7();
//对于函数而言，其实函数名本身指向的就是函数。
//输出上述函数名fn6的名字
console.log(fn6);
```

匿名函数：

![](img\15-1597758592011.bmp)

说明：对于函数而言，其实函数名本身指向的就是函数。

**第二种：** 匿名函数直接作为另一个函数的实际参数传递。

​	例如：

```javascript
function xxx( 数字类型参数，字符串类型的参数，函数类型的参数 ) {

// js逻辑代码

}
```

调用该函数： xxx( 100,”abc”,function(){…} );

上述匿名函数代码演示如下所示：

```javascript
/*
			 * 让匿名函数执行方式二：匿名函数作为函数参数
			 */
			//定义一个函数,第二个参数y接收一个匿名函数
			function fn8(x,y)
			{
				//输出x和y
				console.log('x='+x);//x=100
				console.log('y='+y);//y=function(){console.log(5);}
				//调用匿名函数
				y();
			}
			//调用函数，第二个参数是匿名函数
			fn8(100,function(){
				console.log(5);
			});	
```

实现效果：

![](img\16-1597758592011.bmp)



# **五** **轮播图**

## 5.1、 **案例需求**

这里我们使用的是黑马办公网站。

思想就是时间过几秒，切换下图片，即改变下引入图片的地址。循环可以重复改变引入图片的地址，但是使用循环时间不好控制。所以在js中提供一个新的API,

叫做定时器setInterval。

```html
补充：引入图片地址：<img src="img/1.jpg" width="100%"/>。
```

![](img\23-1597758766603.bmp)

## **5.2、** **相关知识点：定时器setInterval**

语法结构：

```javascript
var timer = window.setInterval(code, millisec) 按照指定的周期（间隔）来执行函数或代码片段。
	参数1： code 必须写。  执行的函数名或执行的代码字符串。 
	参数2： millisec 必须写。时间间隔，单位：毫秒。
window可以理解为浏览器窗口。后面会讲解。
timer 返回值：一个可以传递给 window.clearInterval(定时器的返回值) 从而取消对 code 的周期性执行的值。
在关闭定时器时需要使用定时器的返回值作为参数，否则不知道关闭哪个定时器。
例如：
方式：函数名 ， 	setInterval(show, 100);  √ show 表示函数名，100表示每隔100豪秒执行这个函数。
```

**注意：**调用函数时**window.可以省略。**

window.setInterval() 等效 setInterval()。

需求：开启一个定时器，每隔1秒钟输出一次 hello world。

​	代码示例：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<script type="text/javascript">
			/*
			 * 需求：开启一个定时器，每隔1秒钟输出一次 hello world。
			   分析：
			   window.setInterval(code,millsec);
			   参数：
			   code 表示代码片段，一般都是一个函数
			   millsec : 表示时间段，每隔millsec时间执行code
			   注意：这里的window.可以省略
			 */
			//开启一个定时器
			/*
			 * 在使用函数时何时加小括号？
			 * 如果函数是自己使用的，那么就必须加小括号。
			 * 如果函数不是自己使用，那么就不用加小括号。
			 * 比如下面在使用fn函数时就不是自己使用，那么就不用加小括号。
                原因：因为只要函数名写小括号，例如fn()，那么就会立刻调用，这样setInterval()函数有可能就调用不了了
			 */
            //匿名函数
/*window.setInterval(function() {
                 console.log("hello world");
            }, 1000);*/
//			window.setInterval(fn,1000);//注意，如果这里不使用匿名函数，这里使用自定义函数时不要加()
            //window.setInterval(fn,1000) 表示开启定时器
			var timer = window.setInterval(fn,1000);//注意，如果这里不使用匿名函数，这里使用自定义函数时不要加()
			//可以将函数抽取出来
			function fn(){
				console.log("hello world");//这里的hello world是等1s之后才执行的
                //关闭定时器 书写在函数内部
				//window.clearInterval(timer);
			}
			/*
			 * 关闭定时器，根据定时器的名字来关闭
			 * 在这里关闭定时器是不可以的，因为程序是顺序执行，并且执行很快，同时由于定时器是在过1s后才输出 hello world 
* 还没有执行定时器要执行的函数，就已经将定时器给关闭了。
			 * 所以为了关闭定时器有效果，可以将这句话放到fn函数体中
			 */
//			window.clearInterval(timer);
		</script>
	</body>
</html>
```

说明：

1）在使用函数时何时加小括号？

​	如果函数是自己使用的，那么就必须加小括号。例如之前的用法，定义好函数，直接通过函数名调用，单独让函数执行。

​	 如果函数不是自己使用，那么就不用加小括号。比如上面在使用fn函数时就不是自己使用，那么就不用加小括号。

​	原因：因为只要函数名写小括号，例如fn()，那么就会立刻调用，这样setInterval()函数有可能就调用不了了

2) 上述使用定时器的地方：window.setInterval(fn,1000); 这里的fn函数是在等1s之后才执行的。

3) 定时器关闭，具体看开发中的需求需不需要关闭。

4) 关闭定时器，根据定时器的名字来关闭.关闭定时器的代码：window.clearInterval(timer); 要书写在函数fn里面。因为程序是顺序执行，并且执行很快，同时由于定时器是在过1s后才输出 hello world 。这样就会导致还没有执行定时器要执行的函数，就已经将定时器给关闭了。所以为了关闭定时器有效果，可以将这句话放到fn函数体中。

实现效果：

![](img\24-1597758766603.bmp)

## **5.3、** **案例分析**

有的同学想在head标签里面去写JS代码，但是想要在head标签里面写，就必须等整个页面加载完成，不然是获取不到图片标签的，因为程序是自上往下加载，所以给大家介绍一个事件,当页面加载完成的时候，我们在事件后面的函数（事件函数）里面来获取元素。

```javascript
window.onload = function() {
	// 页面加载完成的时候会执行这个函数
};
```

1、 为页面设置加载事件onload

2、 给轮播图的图片设置一个id

3、 根据id来获取到轮播图的图片

4、 开启定时器，2000毫秒重新设置图片的src属性

关于window.onload=function(){};的代码解释：

需求：获取文本框输入的值并输出到浏览器中。

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			//需求：获取文本框输入的值并输出到浏览器中
			/*
			 * 由于页面是从上往下加载,所以在执行：
			 * var value=document.getElementById('txt').value;
			 * 代码时，还没有加载到下面的input标签，所以会报错：
			 * Uncaught TypeError: Cannot read property 'value' of null
			 * 所以我们一般建议书写js的代码写在页面下面。
			 * 如果想写在这里，可以通过window.onload来实现，他表示在整个页面加载完毕之后在执行这里面的代码
			 */
//			var value=document.getElementById('txt').value;
//			console.log(value);
			window.onload = function()
			{
				var value=document.getElementById('txt').value;
				console.log(value);		
			};
		</script>
	</head>
	<body>
		<input type="text"  id="txt" value="哈哈" />
	</body>
</html>
```

注意：由于页面是从上往下加载,所以在执行：

var value=document.getElementById('txt').value;

​	   代码时，还没有加载到下面的input标签，所以会报错：Uncaught TypeError: Cannot read property 'value' of null

​	所以我们一般建议书写js的代码写在页面下面。

​	如果想写在这里，可以通过window.onload来实现，他表示在整个页面加载完毕之后在执行这里面的代码

## **5.4、** **案例实现**

第一步：在当前项目下新建一个 轮播图案例演示 的文件夹，然后将课前资料中img文件夹中的所有图片拷贝到轮播图案例演示 的文件夹下

![](img\25-1597758766603.bmp)

第二步：然后新建一个主页index.html。

![](img\26-1597758766603.bmp)

第三步：

将下面的代码复制到新建的页面index.html中。

```html
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>

	<body>
		<!--width="100%"表示自适应父容器body的大小，这里就是整个页面-->
		<!--由于页面上此时还会有边框，所以这里直接将边框去掉就可以，拿掉 border="1"-->
		<table cellspacing="0" width="100%" cellpadding="0">
			<tr>
				<td>
					<!--topbar : 1行3列表格-->
					<table width="100%">
						<tr>
							<!--这里的&nbsp;&nbsp;为了能够让
								黑马程序员的图片向右靠一些
							-->
							<td>&nbsp;&nbsp;<img src="img/logo2.png" /></td>
							<td><img src="img/header.jpg" /></td>
							<td align="center">
								<!--这里的&nbsp;为了能够让登录、注册和购物车之间的距离大一点-->
								<a href="javascript:;">登录</a>&nbsp;
								<a href="javascript:;">注册</a>&nbsp;
								<a href="javascript:;">购物车</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--导航条：1行2列表格-->
					<!--
						width="100%" 表示适应父标签大小
						bgcolor="black" 表示表格背景颜色是黑色
						height 表示表格高度是50
					-->
					<table width="100%" bgcolor="black" height="50">
						<tr>
							<td>
								<!--
									&nbsp;&nbsp; 表示首页字体向右靠靠
								-->
								&nbsp;&nbsp;
								<a href="javascript:;">
									<font color="white" size="5">首页</font>
								</a>&nbsp;
								<!--&nbsp; 在这里表示
									首页  手机数码  电脑办公  电脑办公  电脑办公  电脑办公距离大一些
								-->
								<a href="javascript:;">
									<font color="white">手机数码</font>
								</a>&nbsp;
								<a href="javascript:;">
									<font color="white">电脑办公</font>
								</a>&nbsp;
								<a href="javascript:;">
									<font color="white">电脑办公</font>
								</a>&nbsp;
								<a href="javascript:;">
									<font color="white">电脑办公</font>
								</a>
							</td>
							<!--
								align="right" 表示输入的文本框和提交按钮在表格最右面
							-->
							<td align="right">
								<!--placeholder 属性表示文本框中默认显示的内容-->
								<!--
									<input type="text"/> 表示输入文本框
									<input type="button"/> 表示按钮
								-->
								<input type="text" placeholder="Search" />
								<input type="button" value="Submit" />&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--轮播图：一张图片-->
					<!--轮播图暂时不书写，这里只写一张图片-->
					<img src="img/1.jpg" width="100%" />
				</td>
			</tr>
			<tr>
				<td>
					<!--热门商品：3行7列表格-->
					<table width="100%">
						<tr>
							<td colspan="7">
								<!--
								注意：在html中有种标签叫做块级标签，只能自己独立占一行。
								这样的标签有：标题标签hn、段落标签p、列表标签ul等
								但是我们这里需要让热门商品的字和图片在一行，我们可以将
								图片放到h3标题中
								-->
								<h3>热门商品&nbsp;<img src="img/title2.jpg"/></h3>
							</td>
							<!--<td colspan="6">
								&nbsp;<img src="img/title2.jpg"/>
							</td>-->
						</tr>
						<!--
							 align="center" 为了让这一行所有列都居中
						-->
						<tr align="center">
							<!--
								rowspan="2" 为了让这一列跨2行
							-->
							<td rowspan="2">
								<img src="img/big01.jpg" width="205" height="404" />
							</td>
							<!--
								colspan="3" 为了让这一列跨3列
							-->
							<td colspan="3">
								<img src="img/middle01.jpg" />
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
						<tr align="center">
							<!--<td></td>-->
							<!--由于上一行的第一列跨2行，所以这一行的第一列去掉-->
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--广告：一张图片-->
					<img src="img/ad.jpg" width="100%" />
				</td>
			</tr>
			<tr>
				<td>
					<!--热门商品：3行7列表格-->
					<table width="100%">
						<tr>
							<td colspan="7">
								<!--
								注意：在html中有种标签叫做块级标签，只能自己独立占一行。
								这样的标签有：标题标签hn、段落标签p、列表标签ul等
								但是我们这里需要让热门商品的字和图片在一行，我们可以将
								图片放到h3标题中
								-->
								<h3>热门商品&nbsp;<img src="img/title2.jpg"/></h3>
							</td>
							<!--<td colspan="6">
								&nbsp;<img src="img/title2.jpg"/>
							</td>-->
						</tr>
						<!--
							 align="center" 为了让这一行所有列都居中
						-->
						<tr align="center">
							<!--
								rowspan="2" 为了让这一列跨2行
							-->
							<td rowspan="2">
								<img src="img/big01.jpg" width="205" height="404" />
							</td>
							<!--
								colspan="3" 为了让这一列跨3列
							-->
							<td colspan="3">
								<img src="img/middle01.jpg" />
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
						<tr align="center">
							<!--<td></td>-->
							<!--由于上一行的第一列跨2行，所以这一行的第一列去掉-->
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--footer:2行1列表格-->
					<table width="100%">
						<tr>
							<td>
								<img src="img/footer.jpg" width="100%" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<a href="javascript:;">关于我们 </a>
								<a href="javascript:;">联系我们 </a>
								<a href="javascript:;">招贤纳士 </a>
								<a href="javascript:;">法律声明</a>
								<a href="javascript:;">友情链接 </a>
								<a href="javascript:;">支付方式 </a>
								<a href="javascript:;">配送方式 </a>
								<a href="javascript:;">服务声明 </a>
								<a href="javascript:;"> 广告声明 </a>
								<!--
									这里使用段落标签是因为我们观看页面发现上面和下面之间有间隙
								-->
								<p>Copyright &copy; 2005-2016 传智商城 版权所有</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
```

第四步：

运行查看下效果：

![](img\27-1597758766605.bmp)

第五步：开始实现轮播图：

实现轮播图的HTML代码：

说明：由于我们想通过js修改img标签中的src属性值，所以必须得先获取img标签，然后在获取img标签中的src属性，这样才能够修改src的属性值。

如果想获取img标签，我们必须给img标签添加一个id属性。id=“idImg”。

![](img\28-1597758766605.bmp)

实现轮播图的JS代码：

```html
<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			//1.给页面添加一个在页面加载完成之后执行的事件
			window.onload=function()
			{
				//2.获取轮播图的img标签
				let img=document.getElementById("idImg");
				//设置定时器在2s后改变图片
				window.setInterval(changeImg,2000);
				/*
				 * 定义一个变量作为更改图片的名字
				 * 由于页面一加载就执行代码：<img src="img/1.jpg" width="100%" id="idImg"/>
				 * 当页面都加载完成之后在执行这里的js代码，所以定义n=1
				 */
				let n=1;
				//自定义函数改变img地址
				function changeImg()
				{
					/*
					 * 轮播图片初始化的地址：是img/1.jpg，过2s之后换成img/2.jpg，再过2s换成img/3.jpg
					 * 再过2s换成img/1.jpg，再过2s换成img/2.jpg。。。。。
					 * <img src="img/1.jpg" width="100%" id="idImg"/>
					 */
					//更改n
					n++;
					//判断n的大小,如果n=4，继续更改n=1
					if(n==4)
					{
						n=1;
					}
					img.src="img/"+n+".jpg";
				}
			};
		</script>
	</head>
```

# 六 **定时弹广告**

## **1、** **案例需求**

在平日浏览网页时，页面一打开可能会在几秒后显示广告，然后过几秒再隐藏广告。效果如下图所示：

![](img\29-1597845077481.bmp)

## **2、** **相关知识点**

### **2.1、** **JavaScript定时器：setTimeout** 

setTimeout() 在指定的毫秒数后调用函数或执行代码片段。和setInterval()不同的是setTimeout()只是执行一次，而setInterval()每隔指定的时间就会执行，

如果不停止计时器就会一直执行代码片段。

语法结构：

```javascript
window.setTimeout(code,millisec)
参数：
code  必需。要调用的函数。 
millisec 必需。在执行代码前需等待的毫秒数。
```

说明：setInterval() 以指定周期执行函数或代码片段。（上一个案例已经讲解）

 

代码示例：

需求：开启一个定时器 1秒之后输出Hello world，并且只输出一次。

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<script type="text/javascript">
			/*
			 * 需求：开启一个定时器 1秒之后输出Hello world，并且只输出一次。
			 * setTimeout(code,millsec) 在一定时间只执行一次
			 */
			//使用setInterval()定时器执行
//			let timer=window.setInterval(function(){
//				console.log("Hello World");
//				window.clearInterval(timer);
//			},1000);
			//使用setTimeout完成
			let timer = window.setTimeout(function(){
				console.log("Hello World");
			},1000);
		</script>
	</body>
</html>
```

实现效果：

![](img\30-1597845077481.bmp)

### **2.2、** **JavaScript样式获取或修改**

**获取或设置样式：**

想完成弹出广告案例，除了使用上述介绍的定时器，还必须知道如何获取样式的获取和修改。

语法格式：

```javascript
obj.style.样式名		 获得指定“样式”的值。样式名包括标签的宽、高、背景颜色等。
obj.style.样式名= 样式值	 给指定“样式”设置样式。
	注意：如果属性有多个单词使用“-”连接，样式名书写的时候需要将“-”删除，并将后一个单词首字母大写。
	例如：background-color 需要改成 backgroundColor
```

需求：设置页面上某一个div，然后将div的高度设置为原来的div高度的2倍。

div起始高度和宽度是100px,背景颜色是红色。

~~~html
<div id="div" style="width: 100px;height: 100px;background-color: red;">
	我是div
</div>
~~~



代码示例：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<!--需求：设置页面上某一个div，然后将div的高度设置为原来的div高度的2倍。-->
		<div id="div" style="width: 100px;height: 100px;background-color: red;">
			我是div
		</div>
		<script type="text/javascript">
			//获取div
			var div = document.getElementById("div");
			//获取div的高度
			//div.style.height 获取样式： "100px"
//			console.log(div.style.height);
			/*
			 * 由于我们希望将原来的div高度进行增加2倍，而这里获取的是
			 * 字符串"100px"所以我们必须将这里的字符串变为数字100，这样才可以增加2倍
			 */
			var height = window.parseInt(div.style.height);//这里获取的就是数字100
//			console.log(height);
			//将高度翻倍在赋值给高度，注意，这里必须加上单位"px"
			div.style.height = height*2+"px";
			//更改背景颜色
//			div.style.backgroundColor="green";
		</script>
	</body>
</html>
```

实现效果：

![](img\31-1597845077481.bmp)

补充：

将一个字符串转换成一个数字使用：

```javascript
window.praseInt(字符串);
window.parseFloat(字符串);
```

例如：

```javascript
	window.parseInt('12abc');     12
	window.parseInt('12.5abc');   12
	window.parseInt('abc');       NaN  非数字
			
	window.parseFloat('12abc');     12
	window.parseFloat('12.5abc');   12.5
	window.parseFloat('abc');        NaN  非数字
```

## **3、** **弹出广告**案例分析

1. 页面加载成功后触发onload()事件

2. 加载成功后，触发延迟定时器，3秒后，开始显示广告。

3. 显示广告开始后，3秒后再次隐藏广告

## **4、** **案例实现**

步骤：

第一步：新建 一个页面index.html.

![](img\32-1597845077482.bmp)

第二步：将以下的html代码复制到index.html页面中：

```html
<!--width="100%"表示自适应父容器body的大小，这里就是整个页面-->
		<!--由于页面上此时还会有边框，所以这里直接将边框去掉就可以，拿掉 border="1"-->
		<table cellspacing="0" width="100%" cellpadding="0">
			<tr>
				<td>
					<!--topbar : 1行3列表格-->
					<table width="100%">
						<tr>
							<!--这里的&nbsp;&nbsp;为了能够让
								黑马程序员的图片向右靠一些
							-->
							<td>&nbsp;&nbsp;<img src="img/logo2.png" /></td>
							<td><img src="img/header.jpg" /></td>
							<td align="center">
								<!--这里的&nbsp;为了能够让登录、注册和购物车之间的距离大一点-->
								<a href="javascript:;">登录</a>&nbsp;
								<a href="javascript:;">注册</a>&nbsp;
								<a href="javascript:;">购物车</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--导航条：1行2列表格-->
					<!--
						width="100%" 表示适应父标签大小
						bgcolor="black" 表示表格背景颜色是黑色
						height 表示表格高度是50
					-->
					<table width="100%" bgcolor="black" height="50">
						<tr>
							<td>
								<!--
									&nbsp;&nbsp; 表示首页字体向右靠靠
								-->
								&nbsp;&nbsp;
								<a href="javascript:;">
									<font color="white" size="5">首页</font>
								</a>&nbsp;
								<!--&nbsp; 在这里表示
									首页  手机数码  电脑办公  电脑办公  电脑办公  电脑办公距离大一些
								-->
								<a href="javascript:;">
									<font color="white">手机数码</font>
								</a>&nbsp;
								<a href="javascript:;">
									<font color="white">电脑办公</font>
								</a>&nbsp;
								<a href="javascript:;">
									<font color="white">电脑办公</font>
								</a>&nbsp;
								<a href="javascript:;">
									<font color="white">电脑办公</font>
								</a>
							</td>
							<!--
								align="right" 表示输入的文本框和提交按钮在表格最右面
							-->
							<td align="right">
								<!--placeholder 属性表示文本框中默认显示的内容-->
								<!--
									<input type="text"/> 表示输入文本框
									<input type="button"/> 表示按钮
								-->
								<input type="text" placeholder="Search" />
								<input type="button" value="Submit" />&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--轮播图：一张图片-->
					<!--轮播图暂时不书写，这里只写一张图片-->
					<!--给img标签添加id属性-->
					<img src="img/1.jpg" width="100%" id="idImg" />
				</td>
			</tr>
			<tr>
				<td>
					<!--热门商品：3行7列表格-->
					<table width="100%">
						<tr>
							<td colspan="7">
								<!--
								注意：在html中有种标签叫做块级标签，只能自己独立占一行。
								这样的标签有：标题标签hn、段落标签p、列表标签ul等
								但是我们这里需要让热门商品的字和图片在一行，我们可以将
								图片放到h3标题中
								-->
								<h3>热门商品&nbsp;<img src="img/title2.jpg"/></h3>
							</td>
							<!--<td colspan="6">
								&nbsp;<img src="img/title2.jpg"/>
							</td>-->
						</tr>
						<!--
							 align="center" 为了让这一行所有列都居中
						-->
						<tr align="center">
							<!--
								rowspan="2" 为了让这一列跨2行
							-->
							<td rowspan="2">
								<img src="img/big01.jpg" width="205" height="404" />
							</td>
							<!--
								colspan="3" 为了让这一列跨3列
							-->
							<td colspan="3">
								<img src="img/middle01.jpg" />
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
						<tr align="center">
							<!--<td></td>-->
							<!--由于上一行的第一列跨2行，所以这一行的第一列去掉-->
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--广告：一张图片-->
					<img src="img/ad.jpg" width="100%" />
				</td>
			</tr>
			<tr>
				<td>
					<!--热门商品：3行7列表格-->
					<table width="100%">
						<tr>
							<td colspan="7">
								<!--
								注意：在html中有种标签叫做块级标签，只能自己独立占一行。
								这样的标签有：标题标签hn、段落标签p、列表标签ul等
								但是我们这里需要让热门商品的字和图片在一行，我们可以将
								图片放到h3标题中
								-->
								<h3>热门商品&nbsp;<img src="img/title2.jpg"/></h3>
							</td>
							<!--<td colspan="6">
								&nbsp;<img src="img/title2.jpg"/>
							</td>-->
						</tr>
						<!--
							 align="center" 为了让这一行所有列都居中
						-->
						<tr align="center">
							<!--
								rowspan="2" 为了让这一列跨2行
							-->
							<td rowspan="2">
								<img src="img/big01.jpg" width="205" height="404" />
							</td>
							<!--
								colspan="3" 为了让这一列跨3列
							-->
							<td colspan="3">
								<img src="img/middle01.jpg" />
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
						<tr align="center">
							<!--<td></td>-->
							<!--由于上一行的第一列跨2行，所以这一行的第一列去掉-->
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
							<td>
								&nbsp;&nbsp;<img src="img/small03.jpg" /><br />
								<a href="javascript:;">
									<font color="black">冬瓜</font>
								</a><br />
								<font color="red">¥299.00</font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--footer:2行1列表格-->
					<table width="100%">
						<tr>
							<td>
								<img src="img/footer.jpg" width="100%" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<a href="javascript:;">关于我们 </a>
								<a href="javascript:;">联系我们 </a>
								<a href="javascript:;">招贤纳士 </a>
								<a href="javascript:;">法律声明</a>
								<a href="javascript:;">友情链接 </a>
								<a href="javascript:;">支付方式 </a>
								<a href="javascript:;">配送方式 </a>
								<a href="javascript:;">服务声明 </a>
								<a href="javascript:;"> 广告声明 </a>
								<!--
									这里使用段落标签是因为我们观看页面发现上面和下面之间有间隙
								-->
								<p>Copyright &copy; 2005-2016 传智商城 版权所有</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
```

广告图片在页面中的效果：

![](img\33-1597845077482.bmp)

第三步：给广告图片的img标签添加id属性。这样使用js就可以获取该img标签了。

并且让页面在最开始加载页面的时候隐藏广告图片，设置style="display: none;" 即可。

![](img\34-1597845077482.bmp)

第四步：书写js代码，页面加载完之后过3s钟，弹出广告，再过3s让广告消失。

```html
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			window.onload = function() {
				// 获取广告的标签
				var adImg = document.getElementById("adImg");
				//开启定时器，3s后弹出广告
				window.setTimeout(function() {
					//让广告显示就是设置display：block
					adImg.style.display = "block";
					//广告显示完成之后，在消失
					window.setTimeout(function() {
						//让广告消失，设置display：none
						adImg.style.display = "none";
					}, 3000);
				}, 3000);
			};
		</script>
	</head>
```

![](img\35-1597845077482.bmp)

**注意：定时器可以嵌套使用**



# **七** **BOM（Browser Object Model）**

BOM对象：Browser Object Model 浏览器对象模型。把整个浏览器当做一个对象来处理。

一个浏览器对象中又包含了其他的对象。我们重点介绍3个对象Window 、History、 Location 。

![](img\36-1597845225359.bmp)

![](img\37-1597845225360.bmp)

说明：

Window对象：表示浏览器整个窗口。就是上述空白的地方，不包括工具栏和地址栏。

History对象：表示浏览器中的向后和向前的左右箭头。

Location对象：表示浏览器中的地址栏。

**补充：**

**所有JavaScript全局对象、函数以及变量均自动成为window对象的成员。就是都可以使用window对象来调用，例如上述history和location对象都可以使用window对象来调用。**

**举例：window.history   、 window.location**

## **1、** **Window对象（掌握）**

**1、方法：定时器**

| **函数名**      | **描述**                                           |
| --------------- | -------------------------------------------------- |
| setInterval()   | 按照指定的周期（以毫秒计）来调用函数或计算表达式。 |
| clearInterval() | 取消由 setInterval() 设置的定时器。                |
| setTimeout()    | 在指定的毫秒数后调用函数或计算表达式。             |

**2、方法：消息框**

大家都用支付宝付过款，当我们付款的时候，支付宝经常会弹出一个框，询问我们是否确认付款。这样一个弹窗非常有必要，这样防止我们用户的误操作。或者删除某个数据的时候，为了防止误删的时候，都会弹出一个提示框，询问是否确认删除该数据。

​	1）提示框：alert(提示信息);

​	2）确认框：confirm(提示信息);

​		方法返回值：

​		确定返回true

​		取消返回false

​	3）输入框：prompt(提示信息, 默认值);

方法返回值：

​	点击确定：输入框的内容

​	点击取消：null

上述提示框的代码演示案例：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<script type="text/javascript">
			/*
			 * 确认框：confirm(提示信息)
			 * 返回值：
			 * true：点击了确认按钮
			 * false：点击了取消按钮
			 */
			/*var result = window.confirm("确认要删除这条数据吗？");
			if(result)
			{
                // window.alert('确认删除');可以省略window
				alert('确认删除');
			}else{
				alert('取消删除');
			}*/
			/*
			 * 输入框：prompt("提示信息","默认值");
			 * 点击确定：返回值是输入框中输入的内容
			 * 点击取消：返回值是null
			 */
			var result2 = window.prompt("请输入姓名","张三");
			console.log(result2);
		</script>
	</body>
</html>
```

实现效果：

![](img\38-1597845225360.bmp)

输入框：

![](img\39-1597845225360.bmp)

## **2、** **Location对象(理解)**

Location表示的是当前浏览器的地址栏对象。

![](img\40-1597845225360.bmp)

例如URL:  http://127.0.0.1:8020/day03/定时弹广告/05.location.html?__hbt=150384448335#abc

| **属性**  | **对应的值**                                                 |
| --------- | ------------------------------------------------------------ |
| hash:     | #abc                                                         |
| host:     | 127.0.0.1:8020                                               |
| hostname: | 127.0.0.1                                                    |
| **href:** | http://127.0.0.1:8020/day03/定时弹广告/05.location.html?__hbt=1503844483351#abc |
| pathname: | /day03/定时弹广告/05.location.html                           |
| port:     | 8020                                                         |
| protocol: | http:                                                        |
| search:   | ?__hbt=150384448335                                          |

**需求：设置location的href值来实现窗口的跳转。**

**就是在页面加载的时候直接访问http://www.baidu.com**

代码示例：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<script type="text/javascript">
			/*
			 * 需求：设置location的href值来实现窗口的跳转。
			 * 就是在页面加载的时候直接访问http://www.baidu.com
			 */
			window.location.href="http://www.baidu.com";
//			alert(window.location.href);
		</script>
	</body>
</html>
```

## **3、** **History对象（理解）**

window.history用来访问浏览器历史浏览记录。

**前提：必须有浏览记录**.

![](img\41-1597845225360.bmp)

![](img\42-1597845225360.bmp)

说明：

对于go()函数既可以后退又可以前进。但是必须给参数。

举例：

go(1) 前进一步  go(2) 前进2步。。。。。

go(-1) 后退一步  go(-2) 后退2步。。。。

需求：如下图所示，必须有浏览记录。需要创建3个页面。

注意：可以先访问a.html，然后是b.html,最后在访问c.html。

![](img\43-1597845225361.bmp)

a页面：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<button onclick="toPageB();">→</button><br />
		<a href="b.html">去b页面</a>
		<script type="text/javascript">
			//点击按钮跳转到b页面 前进
			function toPageB(){
//				window.history.forward();
				window.history.go(1);
			}
		</script>
	</body>
</html>
```

说明：当点击button按钮的时候会触发button按钮的onclick事件。在onclick事件中可以绑定js函数。

b页面：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<button onclick="toPageA();">←</button>
		<button onclick="toPageC();">→</button><br />
		<a href="c.html">去c页面</a>
		<script type="text/javascript">
			//后退
			function toPageA()
			{
//				window.history.back();
				window.history.go(-1);
			}
			//前进
			function toPageC()
			{
//				window.history.forward();
				window.history.go(1);
			}
		</script>
	</body>
</html>
```

c页面：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<button onclick="toPageB();">←</button>
		<script type="text/javascript">
			//后退
			function toPageB()
			{
//				window.history.back();
//				window.history.go(-1);
				//从c页面直接跳转到a页面
				window.history.go(-2);
			}
		</script>
	</body>
</html>
```



##  4综合案例_会动的时钟

### 目标

​	页面上有两个按钮，一个开始按钮，一个暂停按钮。点开始按钮时间开始走动，点暂停按钮，时间不动。再点开始按钮，时间继续走动。

![1552053500102](img\1552053500102.png)

### 步骤

~~~javascript
  1.在页面上创建一个h1标签，用于显示时钟，设置颜色和大小。
  2.一开始暂停按钮不可用，设置disabled属性，disabled=true表示不可用。
  3.创建全局的变量，用于保存计时器
  4.为了防止多次点开始按钮出现bug，点开始按钮以后开始按钮不可用，暂停按钮可用。点暂停按钮以后，暂停按钮不可用，开始按钮可用。设置disabled=true。
  5.点开始按钮，在方法内部每过1秒中调用匿名函数，在匿名函数内部得到现在的时间，并将得到的时间显示在h1标签内部。
  6.暂停的按钮在方法内部清除clearInterval()的计时器。
~~~

### 代码

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会动的时钟</title>
</head>
<body>
<h1 style="color: darkgreen" id="clock">现在的时间</h1>
<hr/>
<input type="button" value="开始" id="btnStart">
<input type="button" value="暂停" id="btnPause" disabled="disabled">

<script type="text/javascript">
    var timer;
    //开始按钮点击事件
    document.getElementById("btnStart").onclick = function () {
        //将开始按钮不可用，暂停按钮可用
        document.getElementById("btnStart").disabled = true;  //boolean类型的属性
        document.getElementById("btnPause").disabled = false;

        //得到现在的时间并转换为字符串
        var now = new Date().toLocaleString();

        //将时间显示在h1中
        document.getElementById("clock").innerHTML = now;

        //开始计时：每过1秒再在h1显示一次时间。变量名如果前面没有var，默认表示全局变量
        timer = setInterval(function () {
            document.getElementById("clock").innerHTML = new Date().toLocaleString();
        }, 1000);
    };

    //暂停按钮
    document.getElementById("btnPause").onclick = function () {
        document.getElementById("btnStart").disabled = false;  //boolean类型的属性
        document.getElementById("btnPause").disabled = true;
        //清除计时器
        clearInterval(timer);
    };
</script>
</body>
</html>
```

### 小结

1. 清除计时器的方法: clearInterval(计时器)

2. 让元素不可用的属性是：disabled=true/false

3. toLocaleString() 据本地时间格式，把 Date 对象转换为字符串。

   ~~~javascript
   var d=new Date();
   var n=d.toLocaleString(); 
   结果：
   2019年11月13日 12:13:09 
   ~~~

   