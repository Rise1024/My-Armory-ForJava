package com.example.kafka.conf;


import com.example.kafka.consumer.KafkaConsumerService;
import com.example.kafka.producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zds at apache.org
 * @since 9/6/15
 */
@SuppressWarnings("unused")
@Configuration
@Slf4j
public class KafkaConfiguration {

//    @Bean
//    public KafkaProducerService kafkaProducerService() {
//        return new KafkaProducerService("test");
//    }

    @Bean
    public KafkaConsumerService kafkaConsumerService() {
        return new KafkaConsumerService("test");
    }
}
