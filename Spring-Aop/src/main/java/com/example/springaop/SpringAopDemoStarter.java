package com.example.springaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * KefuLiteWebappStarter
 *
 * @author zds
 * @date 2018/1/23 22:44
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
/**
 * 开启AOP springboot无需@EnableAspectJAutoProxy注解 自动开启AOP
 */
//@EnableAspectJAutoProxy
public class SpringAopDemoStarter {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringAopDemoStarter.class, args);
    }

}
