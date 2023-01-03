package com.example.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaHook {

    public class KafkaConsumerRunner implements Runnable {
        private final AtomicBoolean closed = new AtomicBoolean(false);
        private final KafkaConsumer consumer;

        public KafkaConsumerRunner(KafkaConsumer consumer) {
            this.consumer = consumer;
        }
        @PostConstruct
        public void init() {
            Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        }

        @PreDestroy
        public void destroy() {
            shutdown();
        }
        @Override
        public void run() {
            try {
                consumer.subscribe(Arrays.asList("topic"));
                while (!closed.get()) {
                    ConsumerRecords records = consumer.poll(Duration.ofMillis(10000));
                    // Handle new records
                }
            } catch (WakeupException e) {
                // Ignore exception if closing
                if (!closed.get()) throw e;
            } finally {
                consumer.close();
            }
        }

        // Shutdown hook which can be called from a separate thread
        public void shutdown() {
            closed.set(true);
            consumer.wakeup();
        }
    }

}
