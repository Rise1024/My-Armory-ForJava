package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.example.client.TransportClientFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: senlinyang
 * @Date: 23-11-27 11:35
 * @Description:
 */
@Configuration
@Slf4j
public class ESConfig {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Bean
    public Client transportClient() throws Exception {
        TransportClientFactoryBean factoryBean = new TransportClientFactoryBean();
        factoryBean.setClusterNodes(elasticsearchHost);
        factoryBean.setClusterName(clusterName);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}
