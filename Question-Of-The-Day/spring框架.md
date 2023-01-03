## spring当中bean的作用域有哪些?
singleton 单实例
protoTyle  多实例
request   请求结束就销毁
session   会话结束就销毁
globalSession  全局
## spring当中bean的生命周期
- Bean 容器找到配置⽂件中 Spring Bean 的定义。
- Bean 容器利⽤ Java Reflection API 创建⼀个Bean的实例。
- 如果涉及到⼀些属性值 利⽤ set() ⽅法设置⼀些属性值。
- 如果 Bean 实现了 BeanNameAware 接⼝，调⽤ setBeanName() ⽅法，传⼊Bean的名字。
- 如果 Bean 实现了 BeanClassLoaderAware 接⼝，调⽤setBeanClassLoader() ⽅法，传⼊ClassLoader对象的实例。
- 与上⾯的类似，如果实现了其他 *.Aware 接⼝，就调⽤相应的⽅法。
- 如果有和加载这个 Bean 的 Spring 容器相关的 BeanPostProcessor 对象，执⾏postProcessBeforeInitialization()实例化前置 ⽅法
- 如果Bean实现了 InitializingBean 接⼝，执⾏ afterPropertiesSet()初始化⽅法。
- 如果 Bean 在配置⽂件中的定义包含 init-method 属性，执⾏指定的⽅法。
- 如果有和加载这个 Bean的 Spring 容器相关的 BeanPostProcessor 对象，执⾏ postProcessAfterInitialization()实例化后置 ⽅法
- 当要销毁 Bean 的时候，如果 Bean 实现了 DisposableBean 接⼝，执⾏ destroy() ⽅
法。
- 当要销毁 Bean 的时候，如果 Bean 在配置⽂件中的定义包含 destroy-method 属性，执⾏指定
的⽅法。
## spring当中的单例,是如何保障它线程安全的？
spring是通过ThreadLocal来保障单例线程安全的


## springIOC
控制反转IoC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现IoC的一种方法
控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。
在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI

### IOC源码分析
1、调用父类构造函数创建Bean工厂；
2、创建读取注解的Bean定义读取器，其中做了如下事情：
注册一些Spring内置的原始Bean定义（解析我们配置类的处理器、处理@Autowired、@Required注解的后置处理器、处理监听方法的注解@EventListener解析器）到Bean定义Map、BeanNamesList中
3、将配置类注册到Bean定义Map、BeanNamesList中
4、主要是两个方法invokeBeanFactoryPostProcessors调用Bean工厂的后置处理器、finishBeanFactoryInitialization实例化Bean定义Map中未实例化的Bean
5、invokeBeanFactoryPostProcessors过程（扫描配置类并将Class注册为Bean定义）：

将Bean定义Map中实现了BeanDefinitionRegistryPostProcessor的类实例化并执行其实现的Bean定义注册后处理方法，此处只会有ConfigurationClassPostProcessor这个类
调用的是ConfigurationClassPostProcessor下实现的的Bean定义注册后处理方法，在此方法中会解析Bean定义
获取所有的BeanName并循环获取Bean定义进行解析
解析配置类上ComponentScans的对象集合属性进行扫描
循环包路径集合，找到包路径下找到带@Component注解的类注册到Bean定义Map中
循环上面找到的类，如果是配置类则递归扫描注册

6、finishBeanFactoryInitialization过程（Bean实例化流程）：

将Bean定义冻结，不能被修改或任何进一步的处理
获取所有的BeanName并循环获取尝试获取Bean，如果存在则返回
创建Bean实例，此时的Bean实例未设置属性不可用
处理合并Bean定义后置处理器，@AutoWired、@Value注解的预解析
填充属性，此时依赖注入完成
调用Bean后置处理器实现的初始化前的方法
调用初始化方法，如果实现了InitializingBean接口，调用afterPropertiesSet方法；如果定义了init-method方法，则调用init-method方法；
调用Bean后置处理器实现的初始化后的方法
Bean已经被准备就绪了，一直停留在应用的上下文中






