# ES6新语法糖

## 1 什么是ES6

编程语言JavaScript是ECMAScript的实现和扩展 。ECMAScript是由ECMA（一个类似W3C的标准组织）参与进行标准化的语法规范。ECMAScript定义了：

[语言语法](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Lexical_grammar) – 语法解析规则、关键字、语句、声明、运算符等。

[类型](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Data_structures) – 布尔型、数字、字符串、对象等。

[原型和继承](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Inheritance_and_the_prototype_chain)

内建对象和函数的[标准库](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects) – [JSON](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON)、[Math](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math)、[数组方法](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array)、[对象自省方法](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object)等。

ECMAScript标准不定义HTML或CSS的相关功能，也不定义类似DOM（文档对象模型）的[Web API](https://developer.mozilla.org/en-US/docs/Web/API)，这些都在独立的标准中进行定义。ECMAScript涵盖了各种环境中JS的使用场景，无论是浏览器环境还是类似[node.js](http://nodejs.org/)的非浏览器环境。

ECMAScript标准的历史版本分别是1、2、3、5。

那么为什么没有第4版？其实，在过去确实曾计划发布提出巨量新特性的第4版，但最终却因想法太过激进而惨遭废除（这一版标准中曾经有一个极其复杂的支持泛型和类型推断的内建静态类型系统）。

ES4饱受争议，当标准委员会最终停止开发ES4时，其成员同意发布一个相对谦和的ES5版本，随后继续制定一些更具实质性的新特性。这一明确的协商协议最终命名为“Harmony”，因此，ES5规范中包含这样两句话

> ECMAScript是一门充满活力的语言，并在不断进化中。
>
> 未来版本的规范中将持续进行重要的技术改进

2009年发布的改进版本ES5，引入了[Object.create()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/create)、[Object.defineProperty()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/defineProperty)、[getters](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/get)和[setters](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/set)、[严格模式](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Strict_mode)以及[JSON](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON)对象。

ECMAScript 6.0（以下简称ES6）是JavaScript语言的下一代标准，2015年6月正式发布。它的目标，是使得JavaScript语言可以用来编写复杂的大型应用程序，成为企业级开发语言。

可以参考：

http://es6.ruanyifeng.com/

## 2. 语法新特性

### 变量声明let

我们都是知道在ES6以前，var关键字声明变量。无论声明在何处，都会被视为声明在函数的最顶部(不在函数内即在全局作用域的最顶部)。这就是函数变量提升例如

```js
//es5
function test(bool) {
    if(bool){
        var a='Hello world!';
    }
    console.log("test方法内部执行，变量的值："+a);
}

//执行
test(true);
```

以上的代码实际上是:

```js
//es5
function test(bool) {
    //成员变量声明,变量提升
     var a;
    if(bool){
        a='Hello world!';
    }
    //此处访问test 值为undefined
    console.log("test方法内部执行，变量的值："+a);
}

```

所以不用关心bool是否为true or false。实际上，无论如何test都会被创建声明。

接下来ES6主角登场：

我们通常用let和const来声明，let表示变量、const表示常量。let和const都是块级作用域。怎么理解这个块级作用域？在一个函数内部 ，在一个代码块内部。看以下代码

```js
//es
function test(bool) {
    //成员变量声明
    // var a;
    if(bool){
        //赋值
        // var a='Hello world!';//变量泄露
       let a='Hello world!';//变量私有，类似于java的private效果
    }
    //看不到let定义在代码块里面的变量
    console.log("test方法内部执行，变量的值："+a);
}

//执行
test(true);

```



### 常量声明

const 用于声明常量，看以下代码

02_const.js

```js
//定义常量
const username='Rose'
//会报错：常量不能更换引用
// username='Jack';

console.log(username)
```

js的常量类似，相当于static final效果，只生成一个对象，不能改变引用。

应用上：引入模块，一般写成常量方式

### 模板字符串

es6模板字符简直是开发者的福音啊，解决了ES5在字符串功能上的痛点。

第一个用途，基本的字符串格式化。将表达式嵌入字符串中进行拼接。用${}来界定。

03_modlestring.js

```js
  //es5
let username='Rose'
//变量需要拼接
console.log('姓名是：'+username)
//es6
//使用反引号，可以直接打印变量的值，表达式类似于java的el表达式
console.log(`姓名是：${username}`)
```

第二个用途，在ES5时我们通过反斜杠(\)来做多行字符串或者字符串一行行拼接。ES6反引号(``)，在tab键上面的一个符号，直接搞定。

```js
  //---打印字符串格式
//es5
var msg = "Hi \
    man!"
console.log(msg);
//es6
var msg2=`
Hi,
man!
good!
`
console.log(msg2);
```

### 函数的参数默认值

ES6为参数提供了默认值。在定义函数时便初始化了这个参数，以便在参数没有被传递进去时使用。

看例子代码

04_functiondefaultvalue.js

```js
 function show(username='Jack'){
    console.log(username);
}
//传参后，使用传入的值
show('Rose');
//没有传参（undifined），自动使用默认值
show()
```

用来避免undefined



###  箭头函数

ES6很有意思的一部分就是函数的快捷写法。也就是箭头函数。

箭头函数最直观的三个特点。

1不需要function关键字来创建函数

2省略return关键字

3继承当前上下文的 this 关键字

看下面代码（ES6）

```js
 (response,message) => {
    .......
 }
```

相当于ES5代码

```js
function(response,message){
    ......
}
```

示例：

05_arrowfunction.js

ES5:

```js
//es5
var add=function(a,b){
    return a+b;
}
console.log(add(100,200))

```

ES6：

```js

//es6
var add2=(a,b)=>{
    return a+b;
}
console.log(add2(100,200))

//es6更简化写法
//如果函数只有一句话，可以省略大括号和return
var add3=(a,b)=>a+b;
console.log(add3(100,200))
```



### JSON对象初始化简写

ES5我们对于对象都是以键值对的形式书写，是有可能出现键值对重名的。例如

06_functionjsoninit.js

```js
//es5
function people (username,age){
    return {
        "username":username,
        age:age
    }
}

//打印
console.log(people('Rose',18));
```

以上代码可以简写为

```js
//es6
function people2 (username,age){
    return {
        //如果key的名字和变量参数名一致，则可以简化写
       username,
       age
    }
}
//打印
console.log(people2('Jack',88));
```



### 解构

数组和对象是JS中最常用也是最重要表示形式。为了简化提取信息，ES6新增了解构，这是将一个数据结构分解为更小的部分的过程



ES5我们提取对象中的信息形式如下

```js
    const people = {
        name: 'Rose',
        age: 18
    }
    const name = people.name
    const age = people.age
    console.log(name + ' --- ' + age)
```

在ES6之前我们一个一个获取对象信息的，一个一个获取。现在，ES6的解构能让我们从对象或者数组里取出数据存为变量，例如

07_jiegou.js

```js
//目标：从json对象中取值
const people={username:'Rose',age:18}
//es5
//好处，直观；缺点：如果你要取多个值就代码多了一些
// var username=people.username
// var age = people.age
// console.log(username + ' --- ' + age)
//es6
const {username,age}=people;
console.log(username)

 //数组
 const color = ['red', 'blue']
 //es5
//  var first=color[0];
//  console.log(first)
 //es6
 const [first,second]=color
 console.log(first)
 console.log(second)
```

### Spread Operator

ES6中另外一个好玩的特性就是Spread Operator 也是三个点儿...接下来就展示一下它的用途。 组装对象或者数组

08_spread.js

```js
//目标：扩展对象的值
//原来的对象
const peopleOld={username:'Rose',age:18}
//需要重新生成一个对象，并基于之前的对象扩展
const peopleNew={...peopleOld,address:'上海'}
console.log(peopleOld)
console.log(peopleNew)

//原来的数组
const colorOld=['red', 'yellow']
//需要重新生成一个数组，并基于之前的数组扩展
const colorNew=[...colorOld,'blue']
console.log(colorOld)
console.log(colorNew)
```



### 数组map

map()

方法可以将原数组中的所有元素通过一个函数进行处理并放入到一个新数组中并返回该新数组。

举例：有一个字符串数组，希望转为int数组

09_map.js

```js
let arr = ['1','20','-5','3']; 
console.log(arr)

arr = arr.map(s => parseInt(s));
console.log(arr)
```



### 数组reduce()

reduce(function(),初始值（可选）) ：

接收一个函数（必须）和一个初始值（可选），该函数接收两个参数：

- 第一个参数是上一次reduce处理的结果 
- 第二个参数是数组中要处理的下一个元素
  reduce() 会从左到右依次把数组中的元素用reduce处理，并把处理的结果作为下次reduce的第一个参数。如果是 第一次，会把前两个元素作为计算参数，或者把用户指定的初始值作为起始参数

10_reduce.js

```js
let arr = [1,20,-5,3]
// 没有初始值 
let a = arr.reduce((a, b) => a+b) //等价于 1+20-5+3
// 设置初始值为1 
let b =arr.reduce((a, b) => a+b, 1)//等价于 1+1+20-5+3
let c =arr.reduce((a, b) => a*b)//等价于1*20*-5*3
let d =arr.reduce((a,b) => a*b, 0)//等价于 0*1*20*-5*3
console.log(a,b,c,d);
```




















