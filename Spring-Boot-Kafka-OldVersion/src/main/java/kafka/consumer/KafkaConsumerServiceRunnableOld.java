package kafka.consumer;

import kafka.message.MessageAndMetadata;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * zds
 */

public class KafkaConsumerServiceRunnableOld implements Runnable {


    /**
     * KafkaConsumer线程不安全，每个线程一个KafkaConsumer实例
     */
    private final KafkaStream<byte[], byte[]> stream;
    private final String topic;

    public KafkaConsumerServiceRunnableOld(String topicName, KafkaStream<byte[], byte[]> stream) {
//        Properties props = new Properties();
//        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
//        props.setProperty("group.id", "test_group");
//        props.setProperty("enable.auto.commit", "true");
//        props.setProperty("auto.commit.interval.ms", "1000");
//        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
//        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
//        this.consumer = new KafkaConsumer(props);
//        this.topic=topicName;
//        this.consumer.subscribe(Arrays.asList(topic));
        this.stream = stream;
        this.topic = topicName;

        System.out.println("----------" + Thread.currentThread().getName() + "----" + topicName);
    }

    @Override
    public void run() {
        System.out.println("---------开始消费---------");
        System.out.println("----------" + Thread.currentThread().getName() + "----");

        for (MessageAndMetadata<byte[], byte[]> messageAndMetadata : stream) {
            int partition = 0;
            byte[] message = null;
            long offset = 0L;
            byte[] value = messageAndMetadata.message();
            try {
                String valueStr = value != null && value.length != 0 ? new String(value, "UTF-8") : null;
                System.out.println("-------value===" + valueStr + "-----partition ===" + messageAndMetadata.partition()+"============线程=" + Thread.currentThread().getName() );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

        /**
         * ============线程=Thread-6, value = value0 offset===44 partition ===3
         * ============线程=Thread-6, value = value0 offset===46 partition ===3
         ============线程=Thread-6, value = value0 offset===45 partition ===3
         ============线程=Thread-6, value = value0 offset===47 partition ===3
         ============线程=Thread-6, value = value0 offset===48 partition ===3



         ============线程=Thread-3, value = value1 offset===24061548 partition ===0
         ============线程=Thread-3, value = value2 offset===24061549 partition ===0
         ============线程=Thread-3, value = value1 offset===24061550 partition ===0
         ============线程=Thread-3, value = value2 offset===24061551 partition ===0
         ============线程=Thread-3, value = value1 offset===24061552 partition ===0
         ============线程=Thread-3, value = value2 offset===24061553 partition ===0
         ============线程=Thread-3, value = value1 offset===24061554 partition ===0
         ============线程=Thread-3, value = value2 offset===24061555 partition ===0
         * */
    }
}