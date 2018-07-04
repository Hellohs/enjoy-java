package com.dtian.java.core_java.error_analysis.this_escape.demo1;

/**
 * this逃逸 demo
 */
public class ThisEscape {
    private int id;
    private String name;

    public ThisEscape(EventSource<EventListener> source) {
        this.id = 1;
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Object obj) {
                /** 内部类在访问的时候，可以访问到外部类的属性*/
                System.out.println("id: " + ThisEscape.this.id);
                /** 此处name 还没有被初始化，出现了this逃逸 */
                System.out.println("name: " + ThisEscape.this.name);
            }
        });
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = "nnn";
    }
}
