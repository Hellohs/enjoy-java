package com.dtian.java.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NamedThreadFactory
 *     创建指定命名的线程工厂类，可用于线程池创建 Executors 中DefaultThreadFactory 的替换
 */
public class NamedThreadFactory implements ThreadFactory {
    /** 池序列号 */
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    /** 线程序列号 */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;
    protected final boolean mDaemon;
    private final ThreadGroup group;

    /** 默认构造函数 */
    public NamedThreadFactory() {
        this("pool-" + poolNumber.getAndIncrement(), false);
    }

    /**
     * 指定命名前缀的构造函数
     * @param prefix
     */
    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(boolean daemon) {
        this("pool-" + poolNumber.getAndIncrement(), daemon);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        namePrefix = prefix + "-thread-";
        mDaemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread ret = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        ret.setDaemon(mDaemon);
        return ret;
    }
}
