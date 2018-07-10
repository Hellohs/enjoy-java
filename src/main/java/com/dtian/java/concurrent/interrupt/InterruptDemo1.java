package com.dtian.java.concurrent.interrupt;

/**
 * 线程 Interrupt Demo
 * 测试线程 interrupt方法的有效性
 */
public class InterruptDemo1 extends Thread {

    public InterruptDemo1(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            int i = 0;
            //!isInterrupted() 判断线程是否被打断
            while (!isInterrupted()) {
                //如果在sleep的是否，被打断，会抛出异常
                Thread.sleep(1000L);
                i++;
                System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
            //捕捉到异常，任务终止
        }
    }

    public static void main(String[] args) {
        try {
            Thread demo1 = new InterruptDemo1("demo1");//新建线程
            System.out.println(demo1.getName() + " (" + demo1.getState() + ") is new.");
            demo1.start();
            System.out.println(demo1.getName() + " (" + demo1.getState() + ") is started.");
            // 主线程休眠3000ms，然后主线程给demo1发“中断”指令。
            Thread.sleep(3000L);
            demo1.interrupt();
            System.out.println(demo1.getName() + " (" + demo1.getState() + ") is interrupted.");
            // 主线程休眠3000ms，然后查看demo1的状态。
            Thread.sleep(2000L);
            System.out.println(demo1.getName() + " (" + demo1.getState() + ") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
