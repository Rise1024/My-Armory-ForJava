package com.example.springaop.conf;

import com.example.springaop.audit.AuditAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AuditAopConf {
    /**
     * value: 获取对应property名称的值，与name不可同时使用
     * prefix: 配置项的前缀，可有可无
     * name: property完整名称或部分名称（可与prefix组合使用，组成完整的property名称），与value不可同时使用
     * havingValue: 可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同,当两个值相同返回true相同才加载配置
     * matchIfMissing: 如果配置文件中, 没有该配置项, 判断是否加载BEAN, 默认为false。
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "audit.aop.enable", havingValue = "true", matchIfMissing = false)
    public AuditAop auditAop() {
        return new AuditAop();
    }
}
