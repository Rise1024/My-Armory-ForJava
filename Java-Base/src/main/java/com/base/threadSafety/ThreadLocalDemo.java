package com.base.threadSafety;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author zds
 * @Description
 *
 * 修饰代码块：被修饰的代码称作同步语句块，其作用范围是大括号括起来的代码，作用于调用的对象（可以自己指定锁定对象）；如果代码块就是整个方法体，那么和修饰方式是等同的；
 *  * 修饰方法：被修饰的方法称为同步方法，作用范围是整个方法，作用于调用的对象（this）；
 *  * 修饰静态方法：作用范围是整个静态方法，作用于这个类的所有对象，本质是对 Class 对象上锁；
 *  * 修饰类：作用范围是synchronized后面的括号括起来的部分，作用于这个类的所有对象，本质是对 Class 对象上锁；
 *
 *
 * 情况一：两个线程同时访问一个对象的 synchronized 方法，同步执行；
 * 情况二：两个线程访问的是两个对象的 synchronized 方法，并行执行；
 * 情况三：两个线程同时访问一个 synchronized 静态方法，同步执行；
 * 情况四：两个线程同时访问 synchronized 方法与非 synchronized 方法，并发执行；
 * 情况五：两个线程同时访问同一个对象的不同 synchronized 方法，同步执行；
 * 情况六：两个线程同时访问静态 synchronized 方法和非静态 synchronized 方法，并行执行；
 * 情况七：方法抛异常后，会释放锁；
 * 情况八：在 synchronized 方法中调用了普通方法，就不是线程安全的了，synchronized 的作用范围只在 “{}” 内；


 * @createTime 2022/4/12 11:36
 */


public class ThreadLocalDemo {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * 将当前线程的ID存储到ThreadLocal中
     * @param id
     */
    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }
    /**
     * 在请求处理完成后，从ThreadLocal中将本次请求的相关信息清除，
     * 以免造成内存泄漏；
     */
    public static void remove() {
        requestHolder.remove();
    }

    /**
     * 线程封闭 对于stringboot使用ThreadLocal的坑：必须先set后get,对于在拦截器中 HandlerInterceptor 的 postHandle 并不总是会调用
     * 当 Controller 中出现 Exception：
     * 或者在 HandlerInterceptor 的 preHandle 中出现 Exception：
     * ThreadLocal中的值就不会被清理
     * 我们可以使用RequestContextHolder /Request Scope 的 Bean /ThreadLocalTargetSource
     * 它背后的原理也是 ThreadLocal，不过它总会被更底层的 Servlet 的 Filter 清理掉，因此不存在泄露的问题。
     *
     * tomcat相关配置
     *               #最大连接数
     * //    server.tomcat.max-connections=2000
     * //            #最大线程数
     * //    server.tomcat.max-threads=1000
     * //            #最大等待数
     * //    server.tomcat.accept-count=800
     */
    public static class SecurityContextHolder {

        private static final String SECURITY_CONTEXT_ATTRIBUTES = "SECURITY_CONTEXT";

        public static void setContext(String context) {
            RequestContextHolder.currentRequestAttributes().setAttribute(
                    SECURITY_CONTEXT_ATTRIBUTES,
                    context,
                    RequestAttributes.SCOPE_REQUEST);
        }
        public static String get() {
            return (String)RequestContextHolder.currentRequestAttributes()
                    .getAttribute(SECURITY_CONTEXT_ATTRIBUTES, RequestAttributes.SCOPE_REQUEST);
        }
    }



}
