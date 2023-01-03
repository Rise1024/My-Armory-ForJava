package com.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zds
 * @Description  每日一题
 * @createTime 2022/7/8 11:30
 */
/*
触发条件
常见的有三种情况会触发Rebalance：
1，组成员数发生变更：比如网络中断、心跳中断、有新的consumer实例加入组或者离开组、或者有实例崩溃，被踢出组。都会引起group成员数变化。
2，订阅主题数发生变更 ：比如消费topic传入的是正则表达式，则有可能匹配到新增的topic。
3，订阅主题的分区数发生变更 ：比如动态的修改topic的分区数。
4, consumer消费超时
 */
@Slf4j
public class MyConsumerRebalanceListener implements ConsumerRebalanceListener {
    private final KafkaConsumer kafkaConsumer;
    public  final Map<TopicPartition, OffsetAndMetadata> currOffsets;
    public MyConsumerRebalanceListener(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
        this.currOffsets=new HashMap<>();
    }

    // rebalance之前将记录进行保存
    //调用时机是Consumer停止拉取数据之后、Rebalance开始之前，我们可以在此方法中实现手动提交offset，避免
    //Rebalance导致的重复消费问题。
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        log.info("onPartitionsRevoked begin");
        commitOffset(partitions);
        log.info("onPartitionsRevoked end");


    }

    // rebalance之后读取之前的消费记录，继续消费
    //调用时机是Rebalance完成之后、Condumer开始拉取数据之前，我们可以在此方法中调整或自定义offset值。
    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        log.info("onPartitionsAssigned begin");
        String groupId = kafkaConsumer.groupMetadata().groupId();
        log.info("onPartitionsAssigned before get currOffsets:{}",currOffsets);
        currOffsets.clear();
        Map<TopicPartition, Long> offsetMap = kafkaConsumer.endOffsets(partitions);
        for (TopicPartition partition : partitions) {
			//定位到最近提交的offset位置继续消费
            long offset = getOffset(partition);
            if (offset==0){
                offset= offsetMap.get(partition);
                log.info("get offset from kafka offset:{}",offset);
            }
            kafkaConsumer.seek(partition,offset);
		}
        log.info("onPartitionsAssigned end");

    }

    //获取某分区的最新offset
    private static long getOffset(TopicPartition partition) {
        return 0;
    }

    //提交该消费者所有分区的offset
    private static void commitOffset(Collection<TopicPartition> partitions) {

    }

    public void putCurrOffsets(TopicPartition topicPartition, OffsetAndMetadata offsetAndMetadata ) {
        currOffsets.put(topicPartition, offsetAndMetadata);
    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrOffsets() {
        return currOffsets;
    }
}
