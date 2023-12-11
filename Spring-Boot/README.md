### springboot启动

```
public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
        return (new SpringApplication(primarySources)).run(args);
    }
```

一、第一步new了一个SpringApplication对象 

```
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
		this.resourceLoader = resourceLoader;
		Assert.notNull(primarySources, "PrimarySources must not be null");
		//1、先把主类保存起来
		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
		//2、判断运行项目的类型
		this.webApplicationType = WebApplicationType.deduceFromClasspath();
		//3、扫描当前路径下META-INF/spring.factories文件的，加载ApplicationContextInitializer接口实例
		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
		//4、扫描当前路径下META-INF/spring.factories文件的，加载ApplicationListener接口实例
		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
		this.mainApplicationClass = deduceMainApplicationClass();
	}
```
1、ApplicationContextInitializer 这个类当springboot上下文Context初始化完成后会调用

2、ApplicationListener 当springboot启动时事件change后都会触发

二、第二步调用了run()方法

```
public ConfigurableApplicationContext run(String... args) {
        <!--1、这个是一个计时器，没什么好说的-->
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
		
        <!--2、这个也不是重点，就是设置了一些环境变量-->
        configureHeadlessProperty();

        <!--3、获取事件监听器SpringApplicationRunListener类型，并且执行starting()方法-->
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
 
		try {
            <!--4、把参数args封装成DefaultApplicationArguments-->
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(
					args);
 
            <!--5、这个很重要准备环境了，并且把环境跟spring上下文绑定好，并且执行environmentPrepared()方法-->
			ConfigurableEnvironment environment = prepareEnvironment(listeners,
					applicationArguments);
 
            <!--6、判断一些环境的值，并设置一些环境的值-->
			configureIgnoreBeanInfo(environment);
 
            <!--7、打印banner-->
			Banner printedBanner = printBanner(environment);
 
 
            <!--8、创建上下文，根据项目类型创建上下文-->
			context = createApplicationContext();

 
            <!--10、准备上下文，prepareContext方法用来准备上下文，即加载上下文时需要的资源-->
			prepareContext(context, environment, listeners, applicationArguments,
					printedBanner);
 
 
            <!--11、这个是spring启动的代码了，这里就回去里面就回去扫描并且初始化单实列bean了-->
            //这个refreshContext()加载了bean，还启动了内置web容器，需要细细的去看看
			refreshContext(context);
 
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass)
						.logStarted(getApplicationLog(), stopWatch);
			}
 
    
            <!--13、执行ApplicationRunListeners中的started()方法-->
			listeners.started(context);
 
            <!--执行Runner（ApplicationRunner和CommandLineRunner）-->
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, listeners, exceptionReporters, ex);
			throw new IllegalStateException(ex);
		}
		listeners.running(context);
		return context;
	}
```
### Spring Boot在org.springframework.boot组下提供了以下应用程序启动器：

| 名称                                     | 描述                                                   | Pom   |
| ---------------------------------------- | ------------------------------------------------------ | ----- |
| spring-boot-starter-thymeleaf            | 使用Thymeleaf视图构建MVC Web应用程序的启动器            | Pom   |
| spring-boot-starter-data-couchbase       | 使用Couchbase面向文档的数据库和Spring Data Couchbase的启动器 | Pom   |
| spring-boot-starter-artemis              | 使用Apache Artemis的JMS启动器                            | Pom   |
| spring-boot-starter-web-services         | Spring Web Services 启动器                              | Pom   |
| spring-boot-starter-mail                 | Java Mail和Spring Framework的电子邮件发送支持的启动器     | Pom   |
| spring-boot-starter-data-redis           | Redis key-value 数据存储与Spring Data Redis和Jedis客户端启动器  | Pom   |
| spring-boot-starter-web                  | 使用Spring MVC构建Web，包括RESTful应用程序。使用Tomcat作为默认的嵌入式容器的启动器 | Pom   |
| spring-boot-starter-data-gemfire         | 使用GemFire分布式数据存储和Spring Data GemFire的启动器   | Pom   |
| spring-boot-starter-activemq             | 使用Apache ActiveMQ的JMS启动器                          | Pom   |
| spring-boot-starter-data-elasticsearch   | 使用Elasticsearch搜索和分析引擎和Spring Data Elasticsearch的启动器 | Pom   |
| spring-boot-starter-integration          | Spring Integration 启动器                               | Pom   |
| spring-boot-starter-test                 | 使用JUnit，Hamcrest和Mockito的库测试Spring Boot应用程序的启动器 | Pom   |
| spring-boot-starter-jdbc                 | 使用JDBC与Tomcat JDBC连接池的启动器                     | Pom   |
| spring-boot-starter-mobile               | 使用Spring Mobile构建Web应用程序的启动器                | Pom   |
| spring-boot-starter-validation           | 使用Java Bean Validation 与Hibernate Validator的启动器  | Pom   |
| spring-boot-starter-hateoas              | 使用Spring MVC和Spring HATEOAS构建基于超媒体的RESTful Web应用程序的启动器 | Pom   |
| spring-boot-starter-jersey               | 使用JAX-RS和Jersey构建RESTful Web应用程序的启动器。spring-boot-starter-web的替代方案 | Pom   |
| spring-boot-starter-data-neo4j           | 使用Neo4j图数据库和Spring Data Neo4j的启动器            | Pom   |
| spring-boot-starter-data-ldap            | 使用Spring Data LDAP的启动器                            | Pom   |
| spring-boot-starter-websocket            | 使用Spring Framework的WebSocket支持构建WebSocket应用程序的启动器 | Pom   |
| spring-boot-starter-aop                  | 使用Spring AOP和AspectJ进行面向切面编程的启动器         | Pom   |
| spring-boot-starter-amqp                 | 使用Spring AMQP和Rabbit MQ的启动器                      | Pom   |
| spring-boot-starter-data-cassandra       | 使用Cassandra分布式数据库和Spring Data Cassandra的启动器 | Pom   |
| spring-boot-starter-social-facebook      | 使用Spring Social Facebook 的启动器                     | Pom   |
| spring-boot-starter-jta-atomikos         | 使用Atomikos的JTA事务的启动器                          | Pom   |
| spring-boot-starter-security             | 使用Spring Security的启动器                             | Pom   |
| spring-boot-starter-mustache             | 使用Mustache视图构建MVC Web应用程序的启动器             | Pom   |
| spring-boot-starter-data-jpa             | 使用Spring数据JPA与Hibernate的启动器                    | Pom   |
| spring-boot-starter                      | 核心启动器，包括自动配置支持，日志记录和YAML            | Pom   |
| spring-boot-starter-groovy-templates     | 使用Groovy模板视图构建MVC Web应用程序的启动器           | Pom   |
| spring-boot-starter-freemarker           | 使用FreeMarker视图构建MVC Web应用程序的启动器           | Pom   |
| spring-boot-starter-batch                | 使用Spring Batch的启动器                                | Pom   |
| spring-boot-starter-social-linkedin      | 使用Spring Social LinkedIn的启动器                     | Pom   |
| spring-boot-starter-cache                | 使用Spring Framework缓存支持的启动器                    | Pom   |
| spring-boot-starter-data-solr            | 使用Apache Solr搜索平台与Spring Data Solr的启动器       | Pom   |
| spring-boot-starter-data-mongodb         | 使用MongoDB面向文档的数据库和Spring Data MongoDB的启动器 | Pom   |
| spring-boot-starter-jooq                 | 使用jOOQ访问SQL数据库的启动器。 spring-boot-starter-data-jpa或spring-boot-starter-jdbc的替代方案 | Pom   |
| spring-boot-starter-jta-narayana         | Spring Boot Narayana JTA 启动器                         | Pom   |
| spring-boot-starter-cloud-connectors     | 使用Spring Cloud连接器，简化了与Cloud Foundry和Heroku等云平台中的服务连接的启动器 | Pom   |
| spring-boot-starter-jta-bitronix         | 使用Bitronix进行JTA 事务的启动器                        | Pom   |
| spring-boot-starter-social-twitter       | 使用Spring Social Twitter的启动器                       | Pom   |
| spring-boot-starter-data-rest            | 通过使用Spring Data REST在REST上暴露Spring数据库的启动器 | Pom   |
