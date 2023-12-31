# 一、日志组件概念

## 两种日志接口
- commons-logging
- slf4j

## 四种日志实现
1. logging
2. log4j1
3. log4j2
4. logback

### 1、日志接口的作用
目前，主流的日志接口是 apache 的 commons-logging 和 slf4j。它们的作用在于提供统一的接口，具体的日志实现由底层绑定的日志实现框架完成。这样一来，我们的业务系统可以灵活地更换不同的日志实现，而无需修改代码。

对于开发者来说，每种日志都有不同的写法。如果我们直接使用特定的日志框架进行编写，代码就会与该框架绑定，后续更换日志系统将变得非常困难，无法实现无缝切换。

### 2、jar包的对应
- logging：jdk 自带的日志实现，简称为 jul（java-util-logging）
- log4j1：log4j
- log4j2：log4j-api（定义的 API）、log4j-core（API 的实现）
- logback：logback-core（logback 的核心包）、logback-classic（logback 实现了 slf4j 的 API）
- commons-logging：简称为 jcl
    - commons-logging（commons-logging 的原生全部内容）
    - log4j-jcl（commons-logging 到 log4j2 的桥梁）
    - jcl-over-slf4j（commons-logging 到 slf4j 的桥梁）
- slf4j：这个框架比较复杂，在整个日志组件中起到了一个中转站的作用
    - 使用 slf4j 的 API 编程，底层使用其他具体的实现：
        - slf4j-jdk14：slf4j 到 jdk-logging 的桥梁
        - slf4j-log4j12：slf4j 到 log4j1 的桥梁
        - log4j-slf4j-impl：slf4j 到 log4j2 的桥梁
        - logback-classic：slf4j 到 logback 的桥梁
        - slf4j-jcl：slf4j 到 commons-logging 的桥梁
    - 使用 log4j 的 API 编程，但最终输出通过 logback 实现，需要先将 log4j 转交给 slf4j，再通过 slf4j 转交给 logback：
        - jul-to-slf4j：jdk-logging 到 slf4j 的桥梁
        - log4j-over-slf4j：log4j1 到 slf4j 的桥梁
        - jcl-over-slf4j：commons-logging 到 slf4j 的桥梁

# 二、组件集成
在集成这些组件时，业务代码中的日志打印都采用统一的格式。因此，在这里不介绍日志打印，而是关注不同组合之间的 jar 包依赖，主要观察 pom 文件的配置。

1. log4j <br>
![31A5DA57-9578-4AD2-A64B-B9DA6945E689.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.kKFFIy%2F31A5DA57-9578-4AD2-A64B-B9DA6945E689.png)
2. log4j2 <br>
![CD6F7663-61F1-4B5C-9A02-35C60E4360C5.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.5MNhzE%2FCD6F7663-61F1-4B5C-9A02-35C60E4360C5.png)
3. logback <br>
![DA23B00A-1063-4C92-B579-54C8AC79F9BE.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.TlsWhr%2FDA23B00A-1063-4C92-B579-54C8AC79F9BE.png)
    - 在这些配置中出现了与 slf4j 相关的 jar 包。实际上，logback 的官方使用方式是与 slf4j 集成的，Logger、LoggerFactory 都是 slf4j 的接口和类。
4. commons-logging 与 jul 集成 <br>
![7B727F5C-E73E-4BC0-A83E-5509D8DBDE1D.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.a30o09%2F7B727F5C-E73E-4BC0-A83E-5509D8DBDE1D.png)
5. commons-logging 与 log4j1 集成 <br>
![515E72C4-FC0E-416D-8D8E-EB762E6B7FF0.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.CzLckq%2F515E72C4-FC0E-416D-8D8E-EB762E6B7FF0.png)
6. commons-logging 与 log4j2 集成 <br>
![EFE5C7B5-7549-4047-B28E-F09B811EBB91.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.fPWvTH%2FEFE5C7B5-7549-4047-B28E-F09B811EBB91.png)
7. commons-logging 与 logback 集成 <br>
![28AB7C76-8D1A-4BE1-8545-212D1E636B62.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.97tfdi%2F28AB7C76-8D1A-4BE1-8545-212D1E636B62.png)
8. slf4j 与 jdk-logging 集成 <br>
![75FD18AD-F27D-4860-8E31-9461B68DBD8B.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.4b9B4I%2F75FD18AD-F27D-4860-8E31-9461B68DBD8B.png)
9. slf4j 与 log4j1 集成 <br>
![EAA94E76-E83B-4548-BB95-278CE9E6D887.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.Uquuz6%2FEAA94E76-E83B-4548-BB95-278CE9E6D887.png)
10. slf4j 与 log4j2 集成 <br>
![D3CC3F9B-8329-4015-8A32-B7E1F97C4DCD.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.YhSkJ6%2FD3CC3F9B-8329-4015-8A32-B7E1F97C4DCD.png)
11. slf4j 与 logback 集成 <br>
![8F9F1BE5-9721-4CA1-B2FB-4E492C104CE3.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fql%2Fsn38m7wd7xq22g5kb9bhfqxw0000gp%2FT%2Fcom.yinxiang.Mac%2Fcom.yinxiang.Mac%2FWebKitDnD.HFFqev%2F8F9F1BE5-9721-4CA1-B2FB-4E492C104CE3.png)

# 三、jar包冲突说明
1. jul-to-slf4j 与 slf4j-jdk14 冲突
    - jul-to-slf4j：jdk-logging 切换到 slf4j
    - slf4j-jdk14：slf4j 切换到 jdk-logging
2. log4j-over-slf4j 与 slf4j-log4j12 冲突
    - log4j-over-slf4j：log4j1 切换到 slf4j
    - slf4j-log4j12：slf4j 切换到 log4j1
3. jcl-over-slf4j 与 slf4j-jcl 冲突
    - jcl-over-slf4j：commons-logging 切换到 slf4j
    - slf4j-jcl：slf4j 切换到 commons-logging
