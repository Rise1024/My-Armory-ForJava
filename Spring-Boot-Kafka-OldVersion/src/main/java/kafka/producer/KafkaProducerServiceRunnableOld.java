package kafka.producer;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;


public class KafkaProducerServiceRunnableOld implements Runnable {

    private final KafkaProducer<byte[], byte[]> producer;

    private final String topic;

    public KafkaProducerServiceRunnableOld(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
//        props.put("security.protocol", "SASL_PLAINTEXT");
//        props.put("sasl.mechanism", "PLAIN");
//        props.put("sasl.jaas.config","org.apache.kafka.common.security.plain.PlainLoginModule required username=\"admin\" password=\"admin\";");
//        props.put("java.security.auth.login.config","/Users/mac_1/resources/kafka_2.12-2.6.0/config/kafka_client_jaas.conf");
         this.producer=new KafkaProducer<byte[], byte[]>(props);
        this.topic = topic;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 2000; i++) {
                Random random = new Random(10);
                String key = "test"+i%2001;
                String value ="--"+i%2001;
                /**
                 * ProducerRecord创建发送的消息发送键值对其中包括topic、分区（partion）、消息key、消息
                 * 如果不指定分区（partion）则根据消息key来判断分区具体判断方式
                 * Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;(取key hash 和分区总数取余)
                 * key一样的进入同一个分区
                 */
                ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(topic,null, key.getBytes(), value.getBytes());
                Future<RecordMetadata> send = producer.send(record,
                        new Callback() {
                            public void onCompletion(RecordMetadata metadata, Exception e) {
                                if (e != null) {

                                    e.printStackTrace();
                                } else {
                                    System.out.println("The offset of the record we just sent is: " + metadata.offset() + "msg is" + value);
                                }
                            }
                        });
                RecordMetadata recordMetadata = send.get();
                System.out.println("-------key====="+key+"-----partition=="+recordMetadata.partition());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       new  KafkaProducerServiceRunnableOld("test").run();

    }
}