<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
跨域问题处理

- [跨域问题处理](#%E8%B7%A8%E5%9F%9F%E9%97%AE%E9%A2%98%E5%A4%84%E7%90%86)
  - [1、nginx处理跨域](#1nginx%E5%A4%84%E7%90%86%E8%B7%A8%E5%9F%9F)
  - [2、@CrossOrigin注解处理](#2crossorigin%E6%B3%A8%E8%A7%A3%E5%A4%84%E7%90%86)
  - [3、全局MVC配置](#3%E5%85%A8%E5%B1%80mvc%E9%85%8D%E7%BD%AE)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


### 跨域问题处理

#### 1、nginx处理跨域

```
  location  / {
            add_header Access-Control-Allow-Origin '*';
            add_header Access-Control-Allow-Headers '*';
            add_header Access-Control-Allow-Methods '*';
            add_header Access-Control-Allow-Credentials 'true';
            if ($request_method = 'OPTIONS') {
                return 204;
            }
            proxy_pass  http://localhost:8080;
         }
```
- 跨域主要涉及4个响应头：
  - Access-Control-Allow-Origin 用于设置允许跨域请求源地址 （预检请求和正式请求在跨域时候都会验证）
  - Access-Control-Allow-Headers 跨域允许携带的特殊头信息字段 （只在预检请求验证）
  - Access-Control-Allow-Methods 跨域允许的请求方法或者说HTTP动词 （只在预检请求验证）
  - Access-Control-Allow-Credentials 是否允许跨域使用cookies，如果要跨域使用cookies，可以添加上此请求响应头，值设为true（设置或者不设置，都不会影响请求发送，只会影响在跨域时候是否要携带cookies，但是如果设置，预检请求和正式请求都需要设置）。

- 什么是预检请求？
当发生跨域条件时候，览器先询问服务器，当前网页所在的域名是否在服务器的许可名单之中，以及可以使用哪些HTTP动词和头信息字段。只有得到肯定答复，浏览器才会发出正式的XMLHttpRequest请求，否则就报错。 
__当请求为option请求时候，给浏览器返回一个状态码（一般是204）__

#### 2、@CrossOrigin注解处理
```java
@CrossOrigin(origins = "*",allowedHeaders="*",methods={RequestMethod.GET, RequestMethod.POST},allowCredentials = "true")
```
这里注解的参数和nginx类似也是配置
- Access-Control-Allow-Origin:origins
- Access-Control-Allow-Headers:allowedHeaders
- Access-Control-Allow-Methods:methods
- Access-Control-Allow-Credentials:allowCredentials

__不一样是 使用@CrossOrigin注解时，Spring框架会自动处理浏览器发送的预检请求 我们不需要像nginx那样设置响应预检请求__

#### 3、全局MVC配置

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOrigins("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
```
通过实现WebMvcConfigurer接口， 注册自定义的拦截器 配置需要拦截的接口