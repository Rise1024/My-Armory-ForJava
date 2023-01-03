package com.example.kafka.producer;

/**
 * @author zds
 * @Description
 * @createTime 2022/6/29 11:31
 */
public class KafkaProducerService {

    public KafkaProducerService(String topic) {
        KafkaProducerServiceRunnable kafkaProducerServiceRunnable = new KafkaProducerServiceRunnable(topic);
        kafkaProducerServiceRunnable.run();
    }
}
