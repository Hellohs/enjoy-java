package com.dtian.java.core_java.error_analysis.this_escape.demo2;

public class ThisEscapeTest2 {
    private int id;
    private String name;

    public ThisEscapeTest2() {
        id = 1;
        new Thread(new ThisEscapeRunnable()).start();
        // 还有属性没有被初始化
        name = "un_know";
    }

    private class ThisEscapeRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("id:" + ThisEscapeTest2.this.id);
            System.out.println("name:" + ThisEscapeTest2.this.name);
        }
    }
}
