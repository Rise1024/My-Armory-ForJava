package com.base.lock.queue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zds
 * @Description
 * @createTime 2022/1/21 15:55
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<DelayedElement>();

        //生产者
        producer(delayQueue);

        //消费者
        consumer(delayQueue);

        while (true){
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 每100毫秒创建一个对象，放入延迟队列，延迟时间5000+i毫秒
     * @param delayQueue
     */
    private static void producer(final DelayQueue<DelayedElement> delayQueue){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++i;
                    DelayedElement element = new DelayedElement(5000+i*1000,"test"+i);
                    delayQueue.offer(element);
                    break;
                }
            }
        }).start();

        /**
         * 每秒打印延迟队列中的对象个数
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date()+"---delayQueue size:"+delayQueue.size());
                }
            }
        }).start();
    }

    /**
     * 消费者，从延迟队列中获得数据,进行处理
     * @param delayQueue
     */
    private static void consumer(final DelayQueue<DelayedElement> delayQueue){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    DelayedElement element = null;
                    try {
                        element =  delayQueue.poll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (element!=null){
                        System.out.println(new Date()+"---"+element);
                    }
                }
            }
        }).start();
    }
}


class DelayedElement implements Delayed {

    private final long delay; //延迟时间
    private final long expire;  //到期时间
    private final String msg;   //数据
    private final long now; //创建时间

    public DelayedElement(long delay, String msg) {
        this.delay = delay;
        this.msg = msg;
        expire = System.currentTimeMillis() + delay;    //到期时间 = 当前时间+延迟时间
        now = System.currentTimeMillis();
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DelayedElement{");
        sb.append("delay=").append(delay);
        sb.append(", expire=").append(expire);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", now=").append(now);
        sb.append('}');
        return sb.toString();
    }

}
