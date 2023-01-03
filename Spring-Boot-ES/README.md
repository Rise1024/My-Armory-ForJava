#### 代码重构

最近在研究es的时候发现官方已经在7.15.0放弃对旧版本中的Java REST Client （High Level Rest Client (HLRC)）的支持，
从而替换为推荐使用的Java API Client 8.x

查看SpringBoot2.6.4的依赖，其中es的版本仅为7.15.2
````
  <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
  </dependency>

<elasticsearch>7.15.2</elasticsearch>
````
因此这里我就按照官方文档使用了推荐的

````
<dependency>
      <groupId>co.elastic.clients</groupId>
      <artifactId>elasticsearch-java</artifactId>
      <version>8.1.0</version>
</dependency>
````

