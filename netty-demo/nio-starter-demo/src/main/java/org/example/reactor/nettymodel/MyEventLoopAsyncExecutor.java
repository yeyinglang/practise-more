package org.example.reactor.nettymodel;


import java.nio.channels.Selector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单写， subReactor的执行器；
 * 异步提交操作；
 * 注意事项：
 * 为什么不能提前启动subReactor？ 还需要在subReactor中 睡眠100ms；
 * 需要让register注册在前， select在后，因为他们会有共同锁对象；select如果阻塞，会一直维持着那个锁对象。
 */
public class MyEventLoopAsyncExecutor {
    private AtomicInteger idx = new AtomicInteger();

    private ExecutorService es ;
    private SubReactor[] subReactors;
    private byte[] runStates;

    public MyEventLoopAsyncExecutor(int subReactorNum){
        es = Executors.newFixedThreadPool(subReactorNum);
        subReactors = new SubReactor[subReactorNum];
        runStates = new byte[subReactorNum];
        for (int i = 0; i < subReactorNum; i++) {
            subReactors[i] = new SubReactor();
//            runStates[i] = 0;
        }

    }

    public Selector next() {
        int curIdx = idx.getAndIncrement() % subReactors.length;
        if(runStates[curIdx]==0) {
            es.execute(subReactors[curIdx]);
            runStates[curIdx]=1;
        }
        return subReactors[curIdx].subSelector();
    }

}
