package com.example.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * zds
 *
 * kafka消费规则 一个线程可以消费多个partition，但是一个partition只能由一个线程消费
 *
 *
 * 消费设计方案一：
 * 多个consumer实例，多个线程
 * 按照partition设置consumer数量，consumer数量<=partition数量，要不然会造成consumer数量浪费，增加tcp开销
 *
 * 消费设计方案一升级
 *
 * 多个consumer实例，多个work线程
 */

public class KafkaConsumerServiceRunnable implements Runnable{


    /**
     * KafkaConsumer线程不安全，每个线程一个KafkaConsumer实例
     */
    private final KafkaConsumer consumer;
    private final String topic;
    private final Map<String,ArrayBlockingQueue<String>> queueMap;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final ThreadPoolExecutor executors;
    private final MyConsumerRebalanceListener myConsumerRebalanceListener;
//    private final Map<Integer, List<String>> valueMap=new LinkedHashMap<>();
    private final Set<String> set0=new HashSet<>();
    private final Set<String> set1=new HashSet<>();
    private final Set<String> set2=new HashSet<>();
    private final Set<String> set3=new HashSet<>();
    private final Set<String> set4=new HashSet<>();
    private final Set<String> set5=new HashSet<>();
    private final AtomicBoolean sign = new AtomicBoolean(false);






    /** 多个实例 多个线程拉取消息*/

    public KafkaConsumerServiceRunnable(String topicName,int cnt) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("group.id", "test1_group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        this.consumer = new KafkaConsumer(props);
        this.topic=topicName;
        this.consumer.subscribe(Arrays.asList(topic));
         this.queueMap=new ConcurrentHashMap<>();
        this.executors = new ThreadPoolExecutor(
                cnt,
                cnt,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                new ThreadPoolExecutor.CallerRunsPolicy());
        this.myConsumerRebalanceListener=new MyConsumerRebalanceListener(consumer);

        System.out.println("----------"+Thread.currentThread().getName()+"----"+topicName);
    }

     /** 单个实例 一个线程拉取消息*/
//    public KafkaConsumerServiceRunnable(String topicName,KafkaConsumer consumer) {
//
//        this.consumer=consumer;
//        this.topic=topicName;
//        System.out.println("----------"+Thread.currentThread().getName()+"----"+topicName);
//    }

    @Override
    public void run() {
        System.out.println("---------开始消费---------");
        System.out.println("----------" + Thread.currentThread().getName() + "----");
        try {
            while (!closed.get()) {
                ConsumerRecords<byte[], byte[]> msgList = consumer.poll(Duration.ofMillis(1000));
                if (null != msgList && msgList.count() > 0) {
                    for (ConsumerRecord<byte[], byte[]> record : msgList) {
                        String topic = record.topic();
                        byte[] value = record.value();
                        int partition = record.partition();
                        byte[] keys = record.key();
                        long offset = record.offset();
                        try {
                            myConsumerRebalanceListener.putCurrOffsets(new TopicPartition(record.topic(), record.partition()),
                                    new OffsetAndMetadata(record.offset() + 1, "no meta"));
                            String valueStr = value != null && value.length != 0 ? new String(value, "UTF-8") : null;
                            if (keys != null && keys.length > 0) {
                                String keyValue = new String(value, "UTF-8");
                                int processors = Runtime.getRuntime().availableProcessors();
                                String hash = getHsah(16 * processors, keyValue);
                                System.out.println("value===" + value +" offset====== "+offset+ " partition ===" + record.partition());
                                ArrayBlockingQueue<String> queue = queueMap.get(hash);
                                if (queue == null) {
                                    queue = new ArrayBlockingQueue<>(64);
                                    queueMap.put(hash, queue);
                                    new Thread(new MessageWorkConsumer( queueMap, closed, 1000, hash,partition)).start();
//                                    executors.submit(new MessageWorkConsumer( queueMap, closed, 1000, hash,partition));
                                }
                                boolean success = false;
                                while (!success) {
                                    success = queue.offer(valueStr, 1000, TimeUnit.MILLISECONDS);
                                }
                            } else {
                            }
                            consumer.commitAsync(myConsumerRebalanceListener.getCurrOffsets(), new OffsetCommitCallback() {
                                @Override
                                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                                    if (e!=null){
                                    }
                                }
                            });
                        } catch (Exception e) {

                        }finally {
                            Thread.sleep(3000);
                            Map<TopicPartition, OffsetAndMetadata> currOffsets = myConsumerRebalanceListener.getCurrOffsets();
                            currOffsets.keySet().stream().forEach(e-> System.out.println(consumer.endOffsets(Arrays.asList(e))));
                            currOffsets.keySet().stream().forEach(e-> System.out.println(consumer.position(e)));
                        }
                    }
                } else {

                }
            }
        }catch (Exception e){
            consumer.close();
      }
  }

    public static String getHsah(int count,String str){
        int sum = 0;
        for(char data : str.toCharArray()){
            sum += Character.getNumericValue(data);
        }
        return "hash_key_" + sum % count;
    }

    private static class MessageWorkConsumer implements Runnable {

        private final Map<String,ArrayBlockingQueue<String>> queueMap;
        private final AtomicBoolean runtimeStatus;
        private final long pollTimeOut;
        private final String key;
        private final int partition;
        private final Map<Integer, String> valueMap=new HashMap();

        public MessageWorkConsumer( Map<String,ArrayBlockingQueue<String>> queueMap,
                                       AtomicBoolean runtimeStatus,long pollTimeOut,String key,int partition) {
            this.queueMap = queueMap;
            this.runtimeStatus = runtimeStatus;
            this.pollTimeOut = pollTimeOut;
            this.key = key;
            this.partition=partition;
        }

        @Override
        public void run() {
            while(!runtimeStatus.get()){
                try {
                    ArrayBlockingQueue<String> queue = queueMap.get(key);
                    if(queue == null){
                        continue;
                    }
                    String source = queue.poll(pollTimeOut, TimeUnit.MILLISECONDS);
                    if (StringUtils.isEmpty(source)) {
                        continue;
                    }
//                    valueMap.put(partition,source);
//                    System.out.println("-------value===" + source + "-----partition ===" + partition+"============线程=" + Thread.currentThread().getName() );
                }catch (Exception e){

                }
            }
        }
    }

    /**
     * ============线程=Thread-6, value = value0 offset===44 partition ===3
     *  ============线程=Thread-6, value = value0 offset===46 partition ===3
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


    /**
     一共由5个partition 5个线程拉取
     ============线程=kafka-test-consumer-1-thread-4 partition ===3
     ============线程=Thread-23value===== value---0---1656577927958

     ============线程=kafka-test-consumer-1-thread-1 partition ===0

     ============线程=Thread-24value===== value---1---1656577928567

     ============线程=kafka-test-consumer-1-thread-1 partition ===0

     ============线程=Thread-25value===== value---2---1656577928581
     ============线程=kafka-test-consumer-1-thread-3 partition ===2
     ============线程=Thread-26value===== value---3---1656577928585
     ============线程=kafka-test-consumer-1-thread-3 partition ===2
     ============线程=Thread-27value===== value---4---1656577928591
     ============线程=kafka-test-consumer-1-thread-1 partition ===0
     ============线程=Thread-28value===== value---5---1656577928595
     ============线程=kafka-test-consumer-1-thread-5 partition ===4
     ============线程=Thread-29value===== value---6---1656577928599
     ============线程=kafka-test-consumer-1-thread-2 partition ===1
     ============线程=Thread-30value===== value---7---1656577928604
     ============线程=kafka-test-consumer-1-thread-2 partition ===1
     ============线程=Thread-31value===== value---8---1656577928608
     *
     */
}