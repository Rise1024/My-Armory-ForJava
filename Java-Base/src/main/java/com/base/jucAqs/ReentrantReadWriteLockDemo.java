package com.base.jucAqs;

/**
 * @author zds
 * @Description
 * 其最核心要求：在没有任何读写锁的时候，才可以取得写入锁；
 * 其可以用于实现了悲观读取，如果我们执行中，进行读取时，经常可能有另一个执行要写入的需求，为了保持同步，ReentrantReadWriteLock的读取锁就可以排上用场了；
 * 如果读取执行情况很多，写入很少的情况下，ReentrantReadWriteLock可能会使写入操作遭遇饥饿，即写入进程因总有读锁的存在迟迟无法竞争到锁定，而一直处于等待状态;
 *

 * @createTime 2022/4/12 11:13
 */
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    class Data {

    }
}