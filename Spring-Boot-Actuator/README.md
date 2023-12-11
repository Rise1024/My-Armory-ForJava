## 查看应用
访问：http://localhost:8090/ds/actuator 查看所有端点

- 大多数应用程序选择通过 HTTP 公开，其中端点的 ID 和前缀/actuator映射到 URL。
例如，默认情况下，health端点映射到/actuator/health.

| ID            | 描述                                                                                               |
| ------------- | -------------------------------------------------------------------------------------------------- |
| auditevents   | 公开当前应用程序的审核事件信息。需要一颗AuditEventRepository豆子。                                  |
| beans         | 显示应用程序中所有 Spring bean 的完整列表。                                                           |
| caches        | 公开可用的缓存。                                                                                    |
| conditions    | 显示在配置和自动配置类上评估的条件以及它们匹配或不匹配的原因。                                     |
| configprops   | 显示所有的整理列表@ConfigurationProperties。                                                           |
| env           | 公开 Spring 的属性ConfigurableEnvironment。                                                           |
| flyway        | 显示已应用的任何 Flyway 数据库迁移。需要一颗或多颗Flyway豆子。                                       |
| health        | 显示应用程序健康信息。                                                                              |
| httptrace     | 显示 HTTP 跟踪信息（默认情况下，最后 100 个 HTTP 请求-响应交换）。需要一颗HttpTraceRepository豆子。 |
| info          | 显示任意应用程序信息。                                                                              |
| integrationgraph | 显示 Spring 集成图。需要依赖于spring-integration-core。                                               |
| loggers       | 显示和修改应用程序中记录器的配置。                                                                   |
| liquibase     | 显示已应用的任何 Liquibase 数据库迁移。需要一颗或多颗Liquibase豆子。                                 |
| metrics       | 显示当前应用程序的“指标”信息。                                                                     |
| mappings      | 显示所有路径的整理列表@RequestMapping。                                                             |
| quartz        | 显示有关 Quartz Scheduler 作业的信息。                                                              |
| scheduledtasks | 显示应用程序中的计划任务。                                                                          |
| sessions      | 允许从 Spring Session 支持的会话存储中检索和删除用户会话。需要一个使用 Spring Session 的基于 servlet 的 Web 应用程序。 |
| shutdown      | 让应用程序正常关闭。默认禁用。                                                                      |
| startup       | 显示由 收集的启动步骤数据ApplicationStartup。需要SpringApplication配置BufferingApplicationStartup。      |
| threaddump    | 执行线程转储。                                                                                     |


## 参考文档(springboot版本是2.6.7)
- actuator文档：https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#actuator

- 项目中除了actuator还涉及到security框架,可以在Spring-Boot-Security demo中查看
