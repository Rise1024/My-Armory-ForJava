package com.example.springaop.audit;

/**
 * @author zds
 * @Description
 * @createTime 2021/10/18 18:30
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Audit {

    /**
     * 操作名称
     * @return
     */
    String operation() default "";

    /**
     * 操作类型
     * @return
     */
    String operate_type() default "";

}
