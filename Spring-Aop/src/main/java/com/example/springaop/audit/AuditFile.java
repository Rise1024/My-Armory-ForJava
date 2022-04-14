package com.example.springaop.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zds
 * @Description
 * @createTime 2021/10/21 11:43
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface AuditFile {

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
