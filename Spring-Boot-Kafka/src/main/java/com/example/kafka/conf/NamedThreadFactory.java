package com.example.kafka.conf;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger threadPoolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private static final String NAME_PATTERN = "%s-%d-thread";
    private final String threadNamePrefix;

    public NamedThreadFactory(String threadNamePrefix) {
        SecurityManager s = System.getSecurityManager();
        this.group = s != null?s.getThreadGroup():Thread.currentThread().getThreadGroup();
        this.threadNamePrefix = String.format(Locale.ROOT, "%s-%d-thread", checkPrefix(threadNamePrefix), threadPoolNumber.getAndIncrement());
    }

    private static String checkPrefix(String prefix) {
        return prefix != null && prefix.length() != 0?prefix:"kafka";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(this.group, r, String.format(Locale.ROOT, "%s-%d", this.threadNamePrefix, this.threadNumber.getAndIncrement()), 0L);
        t.setDaemon(false);
        t.setPriority(5);
        return t;
    }
}
