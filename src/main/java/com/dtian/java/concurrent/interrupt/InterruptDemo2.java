package com.dtian.java.concurrent.interrupt;

/**
 * 错误的示例
 * 需要在catch块中，增加break
 */
public class InterruptDemo2 extends Thread {
    public InterruptDemo2(String name) {
        super(name);
    }

    @Override
    public void run() {
        int i = 0;
        while (!isInterrupted()) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
                /**错误的示范，这里没有break的话，线程不会跳出循环，就算调用了线程interrupt方法，也不会跳出循环*/
                //break;
            }
            i++;
            System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
        }
    }

    public static void main(String[] args) {
        try {
            Thread demo2 = new InterruptDemo2("demo2");
            System.out.println(demo2.getName() +" ("+demo2.getState()+") is new.");
            demo2.start();
            System.out.println(demo2.getName() +" ("+demo2.getState()+") is started.");
            // 主线程休眠3000ms，然后主线程给demo2发“中断”指令。
            Thread.sleep(3000L);
            demo2.interrupt();
            System.out.println(demo2.getName() +" ("+demo2.getState()+") is interrupted.");
            // 主线程休眠3000ms，然后查看demo2的状态。
            Thread.sleep(2000L);
            System.out.println(demo2.getName() +" ("+demo2.getState()+") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
