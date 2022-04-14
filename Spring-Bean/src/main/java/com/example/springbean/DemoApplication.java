package com.example.springbean;


import com.example.springbean.lifeaycle.config.WebConfigTestTwo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);

//组建注册测试
        /*ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

        User user = context.getBean(User.class);
        System.out.println(user);
        String[] beanNames = context.getBeanNamesForType(User.class);
        Arrays.stream(beanNames).forEach(System.out::println);

        // 查看基于注解的 IOC容器中所有组件名称
        String[] beanNames1 = context.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(System.out::println);

        // 组件的作用域
        *//**
         * 在Spring中我们可以使用@Scope注解来改变组件的作用域：
         *
         * 默认情况下，在Spring的IOC容器中每个组件都是单例的，即无论在任何地方注入多少次，这些对象都是同一个。
         *
         * singleton：单实例（默认）,在Spring IOC容器启动的时候会调用方法创建对象然后纳入到IOC容器中，以后每次获取都是直接从IOC容器中获取（map.get()）；
         *
         * prototype：多实例，IOC容器启动的时候并不会去创建对象，而是在每次获取的时候才会去调用方法创建对象；
         *
         * request：一个请求对应一个实例；
         *
         * session：同一个session对应一个实例。
         *//*
        Object user1 = context.getBean("user");
        Object user2 = context.getBean("user");
        System.out.println(user1 == user2);

        // 测试懒加载
        *//**
         * @Lazy
         * 懒加载的功能是，在单例模式中，IOC容器创建的时候不会马上去调用方法创建对象并注册，只有当组件第一次被使用的时候才会调用方法创建对象并加入到容器中。
         *//*
        Object user3 = context.getBean("user");
        Object user4 = context.getBean("user");

        // FactoryBean测试
        Object cherry = context.getBean("cherryFactoryBean");
        System.out.println(cherry.getClass());

        Object cherryFactoryBean = context.getBean("&cherryFactoryBean");
        System.out.println(cherryFactoryBean.getClass());*/


//bean生命周期
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfigTestTwo.class);

        context.close();

    }
}

