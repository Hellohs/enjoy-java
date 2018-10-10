package com.dtian.java.nio.socket.reactor.multiple_reactor;

public class NIOSubReactorPool {
    private NIOSubReactor[] subReactors;
    private volatile int nextReactor;

    /**
     * 初始化 subReactor pool，并且启动subReactor
     * @param name
     * @param poolSize
     */
    public NIOSubReactorPool(String name, int poolSize) {
        subReactors = new NIOSubReactor[poolSize];
        for(int i=0; i< poolSize; i++){
            NIOSubReactor subReactor = new NIOSubReactor(name + "-" + i);
            subReactors[i] = subReactor;
            subReactor.startup();
        }
    }

    /**
     * 获取下一个 subReactor
     * @return
     */
    public NIOSubReactor getNextSubReactor() {
        int i = ++nextReactor;
        if (i >= subReactors.length) {
            i = nextReactor = 0;
        }
        return subReactors[i];
    }
}
