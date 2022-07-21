package kafka.consumer;

import kafka.conf.NamedThreadFactory;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zds
 * @Description
 * @createTime 2022/6/29 11:38
 */
public class KafkaConsumerServiceOld implements InitializingBean {

    private String topic;
    private ConsumerConfig consumerConfig;


    public KafkaConsumerServiceOld(String topic) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("zookeeper.connect","127.0.0.1:2181");
        props.setProperty("group.id", "test_group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        this.consumerConfig=consumerConfig;
        this.topic=topic;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(6, new NamedThreadFactory("kafka-" + topic + "-consumer"));
        //设置4个消费着一起消费
//        for (int i = 0; i < 6; i++) {
//            executorService.submit(new KafkaConsumerServiceRunnable(topic,consumer));
//
//        }
//        for (int i = 0; i < 6; i++) {
//            new Thread(new KafkaConsumerServiceRunnable(topic)).start();
//        }

        Map<String, Integer> topicPartitionMap=new HashMap<>();
        topicPartitionMap.put(topic,6);
        ConsumerConnector javaConsumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
        Map<String, List<KafkaStream<byte[], byte[]>>> streamMap =javaConsumerConnector.createMessageStreams(topicPartitionMap);

        List<KafkaStream<byte[], byte[]>> streamListPerTopic = streamMap.get(topic);
        for (KafkaStream<byte[], byte[]> stream : streamListPerTopic) {
            executorService.submit(new KafkaConsumerServiceRunnableOld(topic, stream));
        }

    }
}
