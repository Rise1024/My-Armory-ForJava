package com.example.kafka.consumer;

import com.example.kafka.conf.NamedThreadFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.InitializingBean;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zds
 * @Description
 * @createTime 2022/6/29 11:38
 */
public class KafkaConsumerService implements InitializingBean {

    private KafkaConsumer consumer;
    private String topic;


    public KafkaConsumerService(String topic) {
//        Properties props = new Properties();
//        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
//        props.setProperty("group.id", "test_group");
//        props.setProperty("enable.auto.commit", "true");
//        props.setProperty("auto.commit.interval.ms", "1000");
//        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
//        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
//        this.consumer = new KafkaConsumer(props);
        this.topic=topic;
//        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(9, new NamedThreadFactory("kafka-" + topic + "-consumer"));
        int processors = Runtime.getRuntime().availableProcessors();
        //设置4个消费着一起消费
        for (int i = 0; i < 1; i++) {
            executorService.submit(new KafkaConsumerServiceRunnable(topic,processors));

        }
//        for (int i = 0; i < 6; i++) {
//            new Thread(new KafkaConsumerServiceRunnable(topic)).start();
//        }
    }
}
