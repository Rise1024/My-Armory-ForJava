package com.base.lock.threadSafety;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author zds
 * @Description
 * ThreadLocal是一个将在多线程中为每一个线程创建单独的变量副本的类;
 * 当使用ThreadLocal来维护变量时, ThreadLocal会为每个线程创建单独的变量副本,
 * 避免因多线程操作共享变量而导致的数据不一致的情况。
 * @createTime 2022/4/12 11:36
 */


public class ThreadLocalDemo {

    private static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * 将当前线程的ID存储到ThreadLocal中
     * 根据当前线程查询ThreadLocalMap是否存在
     * 如果不存在 new ThreadLocalMap(this, firstValue);
     * 这里ThreadLocalMap、firstValue是强引用
     * key——>this是弱引用
     * 每在一个新线程中调用一次threadLocal.set("xxx")方法，
     * 就会在堆内存中创建一个新的ThreadLocalMap实例，
     * 这个实例通过Entry的方式保存key和value，value是不同的，而key都指向同一个ThreadLocal对象
     *
     *
     *
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
