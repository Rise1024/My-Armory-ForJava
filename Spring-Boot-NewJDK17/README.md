### Java17新特性

- [一、Jdk9](/Spring-Boot-NewJDK17/src/main/java/com/jdk17/jdk9/MGPrivateInterfaceTest.java)


- [二、Jdk10](/Spring-Boot-NewJDK17/src/main/java/com/jdk17/jdk10/VarTest.java)


- [三、jdk14](/Spring-Boot-NewJDK17/src/main/java/com/jdk17/jdk14/SwitchTest.java)


- [四、jdk15](/Spring-Boot-NewJDK17/src/main/java/com/jdk17/jdk15/TxtTest.java)


- [五、jdk16](/Spring-Boot-NewJDK17/src/main/java/com/jdk17/jdk16/InstanceofTest.java)

### 视频文稿

-新版任你发,只用Java8
一直java社区开发者信奉的宗旨

但是Spring在新版本直接来了个大招，
Spring6和SpringBoot3的最低依赖就是Java17
向上兼容Java 19
放弃了java8
一些别的技术框架也开始慢慢不再支持java8
比如Kafka3.0
那为什么大家都开始纷纷弃用java8
选择java17呢?
oracle宣布从JDK17开始
提供OracleJDK的免费版本和更新

我们来看一下从Java8到java17
8年以来的努力成果

我们引入springboot3.0.1
如果我们使用Java8运行项目
我们看到项目是启动不了的
使用Jdk17可以成功运行
而且明显感知到启动速度是优于java8的

Java 8支持在接口中编写default方法，
而从Java 9 开始，可以在接口中包含私有方法
如果一个default方法体很大，
那么就可以通过新增接口私有方法来进行一个合理的拆分

在Java10之前的版本中，
当我们定义局部变量时，
需要在赋值的左侧提供显式类型，
并在赋值的右边提供实现类型：
在 Java 10 之后，
引入了局部变量类型推断var
这样编写代码就变得很简单

原来JDK自带的Http客户端非常难用
现在的HttpClient提供了标准简单的Http客户端APi
用起来也非常地方便，
再也不用去依赖第三方的包
另外增加了websocket的原生支持，
也不用使用第三方包来构建websocketclient

相信NullPointerException问题一直是大家的痛点
因为报的错误信息里面没有反馈是哪里的问题
大多数情况需要我们远程debug来查找问题
但是在Java14之后就可以清晰的从报错信息中看到是哪一个对象造成的问题

在Java 14 中对Switch的改造也成为标准功能
在之前的版本需要使用break返回,但是没有返回值
新版本引入了yield或者箭头直接返回结果
逻辑变得更加的清晰

使用JDK8编写字符串返回HTML文本
需要一堆拼接符号
在Java15返回HTML文本
只需要关注文本本身
直接使用三引号即可
有了这个功能，
定义 HTML、SQL或者 JSON，
都会更加方便

在java8使用instanceof关键字判断类型后
还需要强转的一步操作
在java16之后直接在后面声明变量就可以直接使用

另外在java8声明一个对象
需要包括一系列的类成员变量
和相应的Getter和Setter函数
但是java16引入record类型
这样写一个实体类是不是就非常的简单
可以直接调用

然后从性能上来看
垃圾收集器在各个jdk版本表现
从吞吐量、延迟、资源占用看
无论使用哪种收集器，
与旧版本相比，
JDK17的整体性能都有很大的提升

Java17带来的功能变化和性能提升是非常值得升级的
Java17是java11之后的一个LTS版本也就是长期维护的版本
可以支持到2029年9月份。
所以你们还要坚持Java8吗？