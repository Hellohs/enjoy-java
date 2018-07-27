package com.dtian.java.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 i++ 的非原子性
 * 测试volatile的非原子性
 * 测试AtomicInteger的原子性
 * 测试synchronized的线程安全
 */
public class VolatileAndAtomicIntegerTest {
    private static int ORDINARY_INT = 0;
    private static volatile int VOLATILE_INT = 0;
    private static AtomicInteger ATOMIC_INT = new AtomicInteger(0);
    private static SynchronizedInt SYNCHRONIZED_INT = new SynchronizedInt();

    public static void main(String[] args) {
        CountDownLatch clatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    ++ORDINARY_INT;
                    VOLATILE_INT++;
                    ATOMIC_INT.getAndIncrement();
                    SYNCHRONIZED_INT.increment();
                }
                clatch.countDown();
            }
            ).start();
        }
        try {
            clatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ordinary ++ int value:" + ORDINARY_INT);
        System.out.println("volatile int ++ value:" + VOLATILE_INT);
        System.out.println("atomic int getAndIncrement value:" + ATOMIC_INT);
        System.out.println("synchronized ++ int value:" + SYNCHRONIZED_INT.getValue());

        //console result
        //ordinary ++ int value:9985
        //volatile int ++ value:9992
        //atomic int getAndIncrement value:10000
        //synchronized ++ int value:10000
    }
}

class SynchronizedInt {
    private int value;

    public synchronized void increment() {
        ++value;
    }

    public synchronized int getValue() {
        return value;
    }
}
