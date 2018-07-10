package com.dtian.java.concurrent.interrupt;

/**
 * 增加一个额外的标记
 * 来判断是否要继续执行任务
 */
//当然，继承Thread 也可以实现runnable 接口
public class InterruptDemo3 extends Thread {
    /**
     * 标记位
     */
    private volatile boolean interrupted = false;
    
    public void stopTask(){
        this.interrupted = true;
    }
    
    public InterruptDemo3(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (this) {
            int i = 0;
            /**interrupted = true的时候，不会进入循环*/
            while (!interrupted) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
                    //break;
                }
                i++;
                System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        try {
            InterruptDemo3 demo3 = new InterruptDemo3("demo3");
            System.out.println(demo3.getName() +" ("+demo3.getState()+") is new.");
            demo3.start();
            System.out.println(demo3.getName() +" ("+demo3.getState()+") is started.");
            // 主线程休眠3000ms，然后主线程给demo3发“中断”指令。
            Thread.sleep(3000L);
            demo3.interrupt();
            demo3.stopTask();
            System.out.println(demo3.getName() +" ("+demo3.getState()+") is interrupted.");
            // 主线程休眠3000ms，然后查看demo3的状态。
            Thread.sleep(3000L);
            System.out.println(demo3.getName() +" ("+demo3.getState()+") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
