# day09【Lambda表达式，Stream流，File类】

## 今日内容

* Lambda表达式
* Stream流
* File类
* 递归

## 教学目标 

- [ ] 能够说出Lambda的使用前提
- [ ] 能够说出Lambda表达式格式
- [ ] 能够说出Lambda的省略格式
- [ ] 能够使用Collection集合，Map集合或数组获取Stream流
- [ ] 能够使用Stream流的常用方法
- [ ] 能够将流中的内容收集到集合或者数组中
- [ ] 能够使用文件路径构建File对象
- [ ] 能够使用File类常用方法
- [ ] 能够辨别相对路径和绝对路径
- [ ] 能够遍历文件夹
  

```markdown
1. 函数式编程
	1). 是一类语法
	2). JDK8特性
	3). 包含
		I. lambda表达式(掌握)
		II. 方法引用(了解)
		
2. lambda表达式

3. 应用: Stream流
	对集合进行快速的处理
	
4. 新章节 : IO流	
```



# 第一章  Lambda表达式

```java
package com.itheima01.lambda;
/*
*   lambda表达式: 简洁
*       1). 语法糖:  原理相同,语法更为简洁
*
*           lambda表达式是匿名内部类的语法糖吗?  不是
*           原因是 原理不一样
*
*
*       2). 使用lambda表达式有两大前提
*           a. 有且仅有一个抽象方法(需要重写)的接口
*           b. 上下文推导
*
*       3). 感受函数式编程的思想
*
*
* */
public class Demo01 {

    public static void main(String[] args) {
//        method01();

        //1. 第一种: 定义类,创建对象, 最后传参
        class MyTask implements Runnable{
            @Override
            public void run() {
                System.out.println("a");
            }
        }
        MyTask mt = new MyTask();
        new Thread(mt).start();

        //2. 第二种:匿名内部类(创建对象和定义类合二为一)
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("b");
            }
        };
        new Thread(runnable).start();

        /*
        * 3. 在特定场景下, 我们更关注 方法 (run方法) ,而不是 对象(类的定义,对象创建)
        *
        *   函数(function)   方法(method)
        *
        *   方法:
        *       方法名  参数列表  返回值
        *
        *       因为当前接口的抽象方法只有一个,所以方法名就不重要了,省略
        *
        * */
        new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + "运行了");
                    return;
                }
        ).start();

    }

    private static void method01() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行了");
            }
        }).start();

        new Thread(()-> System.out.println(Thread.currentThread().getName() + "也执行了")).start();
    }
}

```



```java
package com.itheima01.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
*   # lambda表达式的标准语法
*   0. 函数式思想:关注方法,而不是对象
*   1. (参数列表)
*   2. -> 箭头
*   3. { 方法体}
*
*   解释: lambda的使用前提之一: 有且仅有一个抽象方法,那么这个方法叫什么无所谓
*
* */
public class Demo02 {

    public static void main(String[] args) {
        //匿名内部类 :关注的还是对象
        // Thread的参数:Runnbale接口实现类对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a");
            }
        }).start();

        //Runnbale的run无参无返回
        new Thread(
                () -> {
                    System.out.println("a");
                }
        ).start();


        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,5,3,2,1,4);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;//升序
            }
        });
        System.out.println(list);

        Collections.sort(list, (Integer o1,Integer o2) -> {
            return o2 - o1;
        });
        System.out.println(list);
    }
}

```



```java
package com.itheima01.lambda;
/*
* 使用lambda表达式有两大前提
        a. 有且仅有一个抽象方法(需要被重写)的接口
                1). 函数式接口
                2). 注解 @FunctionalInterface
                    用来检测当前接口是否是函数式接口,如果不是,编译报错
                3). Object类中存在的方法都不需要被重写


        b. 上下文推导
            如若没有上下文, lambda表达式无法推导出是哪个接口的方法重写
            lambda一定要给变量赋值(变量或者参数)
* */
public class Demo03 {

    public static void main(String[] args) {
//        method01();

        method03();

    }

    private static void method03() {
        //上文
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        new Thread(runnable).start();
        //下文
        method02(new Runnable() {
            @Override
            public void run() {

            }
        });

        //匿名内部类不需要上下文推导
        //不需要给任何变量赋值,也不需要给参数赋值, 语法可以通过
        new Runnable() {
            @Override
            public void run() {

            }
        };

        //上文推导
        Runnable task =  () -> {
               System.out.println("执行");
           };


        //下文推导
        new Thread(() -> {
            System.out.println("执行");
        }).start();
    }

    private static void method02(Runnable runnable) {

    }

    private static void method01() {
        MyInter myInter = new MyInter() {
            @Override
            public void eat() {

            }
        };

        //隐式声明: 接口中默认声明了Object的所有方法
        boolean xx = myInter.equals("xx");
    }
}
@FunctionalInterface
interface MyInter{
    public abstract void eat();

    String toString();
    static void method01(){

    }
    default void method02(){

    }
}
class A{
    public void eat(){

    }
}
//C继承A了eat方法, 此方法可以当成接口eat方法的重写
// 所以C就不用再重写接口的eat抽象方法了
class C extends A implements MyInter{

}
```



```java
package com.itheima01.lambda;

public class Demo04 {

    public static void main(String[] args) {

        method01(new Cooker() {
            @Override
            public void cook(String cai) {
                System.out.println("醋溜" + cai);
            }
        });

        method01(
                (String cai) -> {
                    System.out.println("开水" + cai);
                }
        );
    }

    //有材料 白菜, 让某个厨师烧菜
    private static void method01(Cooker cooker) {
        System.out.println("准备");
        cooker.cook("白菜");
        System.out.println("结束");
        System.out.println("-----------------");
    }
}
interface Cooker{
    void cook(String cai);
}
```



```java
package com.itheima01.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
*   简略: 可推导即可省略
*       1. 如果方法体只有一句话,那么{},分号 , return都可以省略
*       2. 参数列表的类型可省略
*           (函数式接口只有一个抽象方法需要被重写, 参数列表只有一个, 可推导)
*
*       3. 如果参数列表只有一个参数,那么()可以省略
* */
public class Demo05 {

    public static void main(String[] args) {

//        method01();


        method02(new Cooker() {
            @Override
            public void cook(String cai) {
                System.out.println("醋溜" +cai);
            }
        });
        //标准语法
        method02((String cai) -> {
            System.out.println("椒盐" + cai);
            });

        //最简略
        method02( cai-> System.out.println("水煮" + cai));

    }

    private static void method02(Cooker cooker) {
        cooker.cook("白菜");
    }

    private static void method01() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("打印一句话");
            }
        }).start();
        //标准
        new Thread(
                ()->{
                    System.out.println("也是打印一句话");
                }
        ).start();

        //简略
        new Thread(
                ()-> System.out.println("又打印一句话")
        ).start();

        System.out.println("----------------");

        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,5,3,2,1,4);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);
        //标准
        Collections.sort(list, (Integer o1, Integer o2) -> {
                return o1 - o2;//升序
            }
        );
        //简略
        Collections.sort(list, (Integer o1,Integer o2) -> o2 - o1);
        //最简洁
        Collections.sort(list, (o1, o2) -> o2 - o1);
    }
}

```



```java
package com.itheima01.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Demo06 {

    public static void main(String[] args) {

//        ArrayList<Integer> list = new ArrayList<>();
//        Collections.addAll(list,5,3,2,4,1);
//
//        Collections.sort(list,(o1 , o2) -> o1 - o2);



        //匿名内部类是一个类,编译 Demo06$1.class
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        Comparator<Integer> comparator2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        //lambda表达式不是一个类
        Comparator<Integer> comparator3 = (o1 , o2) -> o1 - o2;

    }
}

```



```markdown
# lambda表达式
1. lambda表达式是函数式编程的一种语法
2. 思想: 关注方法,不关注对象
3. 目的: 简化语法
# lambda表达式使用前提
1. 基于函数式接口
	1). 有且仅有一个抽象方法需要被重写的接口
	2). 注解 : @FunctionalInterface  编译校验接口是否是函数式接口,如若不是编译报错
	3). 目前为止
		Runnable
		Comparator
2. 上下文推导
	lambda表达式必须要给变量 (参数或者变量)
	
# 标准语法
	(参数列表) -> { 方法体 }
	
	直接看成是接口抽象方法的重写方法	
# 简略语法	
1. 如果方法体只有一句话, 可以省略大括号, 分号以及return;
2. 参数列表的类型可以省略
3. 如果参数列表只有一个参数, 可以省略小括号

# lambda表达式跟匿名内部类
1. lambda表达式 (不是) 匿名内部类语法糖
	语法糖: 原理相同,语法更为简略
	原理不同: 
		1). 匿名内部类还是一个类, lambda表达式不是一个类
		2). lambda的性能要高于匿名内部类的
		
2. lambda表达式(不能)完全取代匿名内部类
	1). lambda表达式基于函数式接口的, 只有这种情况能够取代
	2). 匿名内部类可用于所有的类和接口


```



# 第二章 Stream

在Java 8中，得益于Lambda所带来的函数式编程，引入了一个**全新的Stream概念**，用于解决已有集合类库既有的弊端。

## 1 引言

```java
package com.ithema02.stream;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        List<String> zhangList = new ArrayList<>();
        //只保留了 "张"开头的元素
        for (String name : list) {
            if (name.startsWith("张")) {
                zhangList.add(name);
            }
        }
        List<String> shortList = new ArrayList<>();
        //只保留了长度为3的元素
        for (String name : zhangList) {
            if (name.length() == 3) {
                shortList.add(name);
            }
        }
        //最终遍历打印
        for (String name : shortList) {
            System.out.println(name);
        }
    }
}

```



```java
package com.ithema02.stream;

import java.util.ArrayList;
import java.util.List;
/*
* Stream流
*
* 1. 流式思想: 流水线的思想
*       1). 生产东西之前先构建流水线
*       2). 准备好材料,启动流水线,产品就出来了
*
* 2. 作用: 简化集合元素的操作
*
* 3. 特点 : 链式编程
* */
public class Demo02 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.add("张翠山");
        list.add("周伯通");

        list.stream()
                .filter(name -> name.startsWith("张"))
                .filter(name -> name.length() == 3)
                .forEach(name -> System.out.println(name));
    }
}

```



## 2 获取流方式

> ```java
> package com.ithema02.stream;
> 
> import java.util.ArrayList;
> import java.util.Collection;
> import java.util.HashMap;
> import java.util.Map;
> import java.util.stream.Stream;
> 
> /*
> 
>  # Stream流获取
> *  1. Collection 接口
> *       Stream<String> stream = list.stream()
> *
> *  2. Map 接口
> *            //keySet
>         Stream<String> keyStream = map.keySet().stream();
>          //entrySet
>         Stream<Map.Entry<String, String>> entryStream = map.entrySet().stream();
> *
> *  3. 数组
> *      Stream<String> arrayStream = Stream.of(array)
> * */
> public class Demo03 {
> 
>     public static void main(String[] args) {
> //        1. Collection 接口
>         Collection<String> list = new ArrayList<>();
>         Stream<String> stream = list.stream();
> 
>         //2. Map 接口
> 
>         HashMap<String, String> map = new HashMap<>();
> 
>         //keySet
>         Stream<String> keyStream = map.keySet().stream();
>         //entrySet
>         Stream<Map.Entry<String, String>> entryStream = map.entrySet().stream();
> 
>         //3. 数组
>         String[] array = {"a","b","c"};
>         Stream<String> arrayStream = Stream.of(array);
>     }
> }
> 
> ```

## 4 常用方法

流模型的操作很丰富，这里介绍一些常用的API。这些方法可以被分成两种：

- **终结方法**：返回值类型不再是`Stream`接口自身类型的方法，因此不再支持类似`StringBuilder`那样的链式调用。本小节中，终结方法包括`count`和`forEach`方法。
- **非终结方法**：返回值类型仍然是`Stream`接口自身类型的方法，因此支持链式调用。（除了终结方法外，其余方法均为非终结方法。）

### 函数拼接与终结方法

在上述介绍的各种方法中，凡是返回值仍然为`Stream`接口的为**函数拼接方法**，它们支持链式调用；而返回值不再为`Stream`接口的为**终结方法**，不再支持链式调用。如下表所示：

| 方法名  | 方法作用   | 方法种类 | 是否支持链式调用 |
| ------- | ---------- | -------- | ---------------- |
| count   | 统计个数   | 终结     | 否               |
| forEach | 逐一处理   | 终结     | 否               |
| filter  | 过滤       | 函数拼接 | 是               |
| limit   | 取用前几个 | 函数拼接 | 是               |
| skip    | 跳过前几个 | 函数拼接 | 是               |
| map     | 映射       | 函数拼接 | 是               |
| concat  | 组合       | 函数拼接 | 是               |

> 备注：本小节之外的更多方法，请自行参考API文档。

### forEach : 逐一处理

虽然方法名字叫`forEach`，但是与for循环中的“for-each”昵称不同，该方法**并不保证元素的逐一消费动作在流中是被有序执行的**。

```java
void forEach(Consumer<? super T> action);
```

该方法接收一个`Consumer`接口函数，会将每一个流元素交给该函数进行处理。例如：

```java
package com.ithema02.stream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Demo04 {

    public static void main(String[] args) {

//        method01();

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"a","b","c");

       /* method02(list, new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });*/

       //s是集合中的每个元素
       method02(list,s -> System.out.println(s));

    }

    private static <T>void method02(ArrayList<T> list,Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    private static void method01() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"a","b","c");


        /*
            2. 调用foreach方法
            void forEach(Consumer<? super T> action)

            消费者
                @FunctionalInterface
                public interface Consumer<T> {
                     void accept(T t);
                }
                
            底层: 主要帮我们实现了遍历    

         */
      /*  stream.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });*/
        //1. 获取stream流对象
//        Stream<String> stream = list.stream();
        //s 是集合中的每个元素
//        stream.forEach(s-> System.out.println(s));
        
        //链式编程
        list.stream().forEach(s -> System.out.println(s));

    }
}

```



```java
package com.ithema02.stream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/*

   1.  void forEach(Consumer<? super T> action)
        遍历每一个元素

*  2.  long count();  统计集合的个数
*
*
*  3.  filter : 过滤
*     Stream<T> filter(Predicate<? super T> predicate);
*           public interface Predicate<T> {//推断
*               //t 集合中每个元素, 根据判断条件返回boolean
 *               // 如果结果true,此元素予以保留
*               boolean test(T t);
*           }
*
*   limit : 取用前几个
*       Stream<T> limit(long maxSize);
*
*   skip : 跳过前几个
*         Stream<T> skip(long n);
*   map : 映射
*         Stream<R> map(Function<T,R> mapper);
*
*           public interface Function<T, R> { //函数,交换
*               //t集合中的每个元素, R是返回值
*               R apply(T t);
*           }
*
*   concat : 组合
*       Stream.concat(streamA,streamB)
*       将两个流合并为一个流
*
* */
public class Demo05 {

    public static void main(String[] args) {
//        method01();

//        ArrayList<String> list = new ArrayList<>();
//        Collections.addAll(list,"张三","张三丰","李四","李四石","王五");

        //过滤
//        list.stream().filter(t -> t.startsWith("李")).forEach(t -> System.out.println(t));
        //取用前几个
//        list.stream().limit(3).forEach(t -> System.out.println(t));

        //跳过前几个
//        list.stream().skip(3).forEach(t -> System.out.println(t));

        // 映射
//       ArrayList<String> list = new ArrayList<>();
//        Collections.addAll(list,"100","200","300");
        //String -> Integer
//        list.stream().map(t -> Integer.parseInt(t)).forEach(t -> System.out.println(t + 1));
//        list.stream().map(Integer::parseInt).forEach(t -> System.out.println(t + 1));


        //组合
        Stream<Integer> streamA = Stream.of(1,2,3);
        Stream<Integer> streamB = Stream.of(4,5,6);

        Stream.concat(streamA,streamB).forEach(t -> System.out.println(t));
    }

    private static void method01() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"a","b","c");

        //统计集合的个数
        long count = list.stream().count();
        System.out.println(count);
    }
}

```



```java
package com.ithema02.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

/*
*   终结方法 : 不支持链式编程
*      1.  void forEach(Consumer<? super T> action)
            遍历每一个元素
*      2.  long count();  统计集合的个数
*
*   拼接方法: 支持链式编程
*       3.  filter : 过滤
    *     Stream<T> filter(Predicate<? super T> predicate);
    *           public interface Predicate<T> {//推断
    *               //t 集合中每个元素, 根据判断条件返回boolean
     *               // 如果结果true,此元素予以保留
    *               boolean test(T t);
    *           }
*
    *  4.  limit : 取用前几个
    *       Stream<T> limit(long maxSize);
*
       5. skip : 跳过前几个
*         Stream<T> skip(long n);
*
    *  6.  map : 映射
    *         Stream<R> map(Function<T,R> mapper);
    *
    *           public interface Function<T, R> { //函数,交换
    *               //t集合中的每个元素, R是返回值
    *               R apply(T t);
    *           }
*
*       7. concat : 组合 (Stream接口的静态方法)
*           Stream concat(streamA,streamB)
*           将两个流合并为一个流
* */
public class Demo06 {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"100","200","300");

        //String -> Integer * 10
      /*  map(list, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s) * 10;
            }
        });*/

//        map(list,s->Integer.parseInt(s) * 100);


        //String -> String
        list.stream().map(t-> t+"abc").forEach(t -> System.out.println(t));
//        list.stream().map(t-> t+"abc").forEach(System.out::println);


    }

    private static void map(ArrayList<String> list, Function<String,Integer> function) {
        for (String s : list) {
            Integer value = function.apply(s);
            System.out.println(value + 1);
        }
    }
}

```





## 5 Stream综合案例

现在有两个`ArrayList`集合存储队伍当中的多个成员姓名，要求使用传统的for循环（或增强for循环）**依次**进行以下若干操作步骤：

1. 第一个队伍只要名字为3个字的成员姓名；
2. 第一个队伍筛选之后只要前3个人；
3. 第二个队伍只要姓张的成员姓名；
4. 第二个队伍筛选之后不要前2个人；
5. 将两个队伍合并为一个队伍；
6. 根据姓名创建`Person`对象；
7. 打印整个队伍的Person对象信息。



```java
package com.ithema02.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Demo07 {

    public static void main(String[] args) {
        List<String> one = new ArrayList<>();
        one.add("迪丽热巴");
        one.add("宋远桥");
        one.add("苏星河");
        one.add("老子");
        one.add("庄子");
        one.add("孙子");
        one.add("洪七公");

        List<String> two = new ArrayList<>();
        two.add("古力娜扎");
        two.add("张无忌");
        two.add("张三丰");
        two.add("赵丽颖");
        two.add("张二狗");
        two.add("张天爱");
        two.add("张三");

//        1. 第一个队伍只要名字为3个字的成员姓名；
//        2. 第一个队伍筛选之后只要前3个人；
        Stream<String> streamA = one.stream().filter(s -> s.length() == 3).limit(3);
//        3. 第二个队伍只要姓张的成员姓名；
//        4. 第二个队伍筛选之后不要前2个人；
        Stream<String> streamB = two.stream().filter(s -> s.startsWith("张")).skip(2);
//        5. 将两个队伍合并为一个队伍；
//        6. 根据姓名创建Person对象；
//        7. 打印整个队伍的Person对象信息。
        Stream.concat(streamA,streamB).map(name -> new Person(name)).forEach(p -> System.out.println(p));

    }
}
class Person{
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
```



## 6 收集Stream结果

对流操作完成之后，如果需要将其结果进行收集，例如获取对应的集合、数组等，如何操作？

### 收集到集合中

Stream流提供`collect`方法，其参数需要一个`java.util.stream.Collector<T,A, R>`接口对象来指定收集到哪种集合中。幸运的是，`java.util.stream.Collectors`类提供一些方法，可以作为`Collector`接口的实例：

- `public static <T> Collector<T, ?, List<T>> toList()`：转换为`List`集合。
- `public static <T> Collector<T, ?, Set<T>> toSet()`：转换为`Set`集合。

下面是这两个方法的基本使用代码：

```java
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo15StreamCollect {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("10", "20", "30", "40", "50");
        List<String> list = stream.collect(Collectors.toList());
        Set<String> set = stream.collect(Collectors.toSet());
    }
}
```

### 收集到数组中

Stream提供`toArray`方法来将结果放到一个数组中，返回值类型是Object[]的：

```java
Object[] toArray();
```

其使用场景如：

```java
import java.util.stream.Stream;

public class Demo16StreamArray {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("10", "20", "30", "40", "50");
        Object[] objArray = stream.toArray();
    }
}
```



# 第三章 File类

```markdown
# IO流
1. 作用: 读写文件(数据)的技术
2. 前提: 首先得知道java中有什么类型表示文件 (File类)

# File类
1. 表示 : 不仅表示文件,还表示文件夹
	文件: 数据
	文件夹: 路径

2. 方法: 增删改查
```



## 1 概述

`java.io.File` 类是文件和目录路径名的抽象表示，主要用于文件和目录的创建、查找和删除等操作。



## 2 构造方法

- `public File(String pathname) ` ：通过将给定的**路径名字符串**转换为抽象路径名来创建新的 File实例。  
- `public File(String parent, String child) ` ：从**父路径名字符串和子路径名字符串**创建新的 File实例。
- `public File(File parent, String child)` ：从**父抽象路径名和子路径名字符串**创建新的 File实例。  


- 构造举例，代码如下：

```java
package com.itheima03.file;

import java.io.File;

/*
* - public File(String pathname) ：通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
- public File(String parent, String child) ：从父路径名字符串和子路径名字符串创建新的 File实例。
- public File(File parent, String child) ：从父抽象路径名和子路径名字符串创建新的 File实例。


    路径分隔符:
        windows:  \    反斜杠
        java :  /  (unix系统) 正斜杠
                \\ 也可以 (原因是转义了转义字符)

    正则表达式 (regular expression :regex)
    1). 表达式:
        以简短的符号表示一定的内容

    2). 符号
        \t : 制表符
        \r\n : 换行
        \ : 转义

* */
public class Demo01 {

    public static void main(String[] args) {
        //创建file对象,跟C:\test\a.txt进行关联
//        File file = new File("C:\\test\\a.txt");
//        File file = new File("C:/test/a.txt");

        //parent+child = 父路径+子路径 = 完整路径
//        File file = new File("c:/test", "a.txt");

        File parent = new File("c:/test");
        File file = new File(parent, "a.txt");
        System.out.println(file);
        file.delete();


    }
}

```

> 小贴士：
>
> 1. 一个File对象代表硬盘中实际存在的一个文件或者目录。
> 2. 无论该路径下是否存在文件或者目录，都不影响File对象的创建。



## 3 常用方法

### 获取功能的方法

- `public String getAbsolutePath() ` ：返回此File的绝对路径名字符串。

- ` public String getPath() ` ：将此File转换为路径名字符串。 

- `public String getName()`  ：返回由此File表示的文件或目录的名称。  

- `public long length()`  ：返回由此File表示的文件的长度。 不能获取目录的长度。

  方法演示，代码如下：

  ```java
  package com.itheima03.file;
  
  import java.io.File;
  
  /*
  * - public String getAbsolutePath() ：返回此File的绝对路径名字符串。(很有用)
  *
  - public String getPath() ：将此File转换为路径名字符串。 (构造路径)
  
  - public String getName()  ：返回由此File表示的文件或目录的名称。
          获取此文件/目录最后一级名称
  
  - public long length()  ：返回由此File表示的文件的长度。 不能获取目录的长度。
          获取文件的大小,但是文件夹是获取不了的
  
      #路径
      1. 绝对路径 (完整路径)
          window系统  c:/test/a.txt
                      盘符开始
  
      2. 相对路径: 相对而言的路径
          相对 c:/test 而言, a.txt  这个文件就在这个路径下
  
          javase工程的默认相对路径 是 当前工程的路径
  
  
  * */
  public class Demo02 {
  
      public static void main(String[] args) {
  
          File fileA = new File("E:\\test\\class119_javase\\day09");
          //相对当前工程的路径 E:\test\class119_javase 而言
          File fileB = new File("day09/a.txt");
  //        fileB.delete();
  
  
          System.out.println(fileA.getAbsolutePath());
          System.out.println(fileB.getAbsolutePath()); //获取绝对路径
          System.out.println("-------------------");
  
          System.out.println(fileA.getPath()); //获取构造路径
          System.out.println(fileB.getPath());
  
          System.out.println("-------------------");
  
          System.out.println(fileA.getName()); // 获取最后一级名称
          System.out.println(fileB.getName());
  
          System.out.println("--------");
          System.out.println(fileA.length());
          System.out.println(fileB.length());// 获取文件长度
  
      }
  }
  
  ```

> API中说明：length()，表示文件的长度。但是File对象表示目录，则返回值未指定。

### 绝对路径和相对路径

- **绝对路径**：从盘符开始的路径，这是一个完整的路径。
- **相对路径**：相对于项目目录的路径，这是一个便捷的路径，开发中经常使用。

```java
public class FilePath {
    public static void main(String[] args) {
      	// D盘下的bbb.java文件
        File f = new File("D:\\bbb.java");
        System.out.println(f.getAbsolutePath());
      	
		// 项目下的bbb.java文件
        File f2 = new File("bbb.java");
        System.out.println(f2.getAbsolutePath());
    }
}
输出结果：
D:\bbb.java
D:\idea_project_test4\bbb.java
```

### 判断功能的方法

- `public boolean exists()` ：此File表示的文件或目录是否实际存在。
- `public boolean isDirectory()` ：此File表示的是否为目录。
- `public boolean isFile()` ：此File表示的是否为文件。

方法演示，代码如下：

```java
package com.itheima03.file;

import java.io.File;

/*
- public boolean exists() ：此File表示的文件或目录是否实际存在。
- public boolean isDirectory() ：此File表示的是否为目录。
- public boolean isFile() ：此File表示的是否为文件。

    系统中任何存在的东西,不是文件就是文件夹
* */
public class Demo03 {

    public static void main(String[] args) {

        File file = new File("day09/a.txt");
        //true表示存在,false表示不存在
        System.out.println(file.exists());
        //true是文件夹,false不是
        System.out.println(file.isDirectory());
        //true是文件,false不是
        System.out.println(file.isFile());
    }
}

```

### 创建删除功能的方法

- `public boolean createNewFile()` ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。 
- `public boolean delete()` ：删除由此File表示的文件或目录。  
- `public boolean mkdir()` ：创建由此File表示的目录。
- `public boolean mkdirs()` ：创建由此File表示的目录，包括任何必需但不存在的父目录。

方法演示，代码如下：

```java
package com.itheima03.file;

import java.io.File;
import java.io.IOException;

/*
- public boolean delete() ：删除由此File表示的文件或目录。
    1). 此方法别乱用,它不走回收站
    2). 此方法可以删除文件和空文件夹,但是删除不了非空文件夹

    非空文件夹的删除逻辑: 先删除此文件夹下的所有文件,然后自删

* - public boolean createNewFile() ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。
    创建新文件

- public boolean mkdir() ：创建由此File表示的目录。
    make directory 创建单级目录

- public boolean mkdirs() ：创建由此File表示的目录，包括任何必需但不存在的父目录。
    make directorys 创建多级目录

文件后缀名 是给操作系统看的(后缀名是文件名的一部分)
    txt -> windows默认用记事本打开
    avi -> 默认用视频播放器打开..
    jpg -> 图片查看器打开
* */
public class Demo04 {

    public static void main(String[] args) throws IOException {

        File file = new File("c:/test/bb/a");
//        file.delete();

        /*
            如果文件不存在则创建,返回true
            如果文件存在则不创建,返回false
            如果文件的父路径不存在, 抛出 IOException
        * */
//        boolean result = file.createNewFile();
//        System.out.println(result);

//        boolean mkdir = file.mkdir();
//        System.out.println(mkdir);

        boolean mkdir = file.mkdirs();
        System.out.println(mkdir);


    }
}

```

> API中说明：delete方法，如果此File表示目录，则目录必须为空才能删除。



## 4 目录的遍历

- `public String[] list()` ：返回一个String数组，表示该File目录中的所有子文件或目录。


- `public File[] listFiles()` ：返回一个File数组，表示该File目录中的所有的子文件或目录。  

```java
package com.itheima03.file;

import java.io.File;

/*
* - public String[] list() ：返回一个String数组，表示该File目录中的所有子文件或目录。

- public File[] listFiles() ：返回一个File数组，表示该File目录中的所有的子文件或目录。

* */
public class Demo05 {

    public static void main(String[] args) {

        File dir = new File("c:/test/filedoc");
        //列出此目录下的所有子文件或子文件夹
        /*String[] list = dir.list();
        for (String name : list) {
            System.out.println(name);
        }*/


        File[] files = dir.listFiles();
        for (File sonFile : files) {
            System.out.println(sonFile.getAbsolutePath());
        }
    }
}

```

> 小贴士：
>
> 调用listFiles方法的File对象，表示的必须是实际存在的目录，否则返回null，无法进行遍历。



```java
public class DiGuiDemo3 {
    public static void main(String[] args) {
        // 创建File对象
        File dir  = new File("D:\\aaa");
      	// 调用打印目录方法
        printDir(dir);
    }

    public static void printDir(File dir) {
      	// 获取子文件和目录
        File[] files = dir.listFiles();
      	
      	// 循环打印
        for (File file : files) {
            if (file.isFile()) {
              	// 是文件，判断文件名并输出文件绝对路径
                if (file.getName().endsWith(".java")) {
                    System.out.println("文件名:" + file.getAbsolutePath());
                }
            } else {
                // 是目录，继续遍历,形成递归
                printDir(file);
            }
        }
    }
}
```
