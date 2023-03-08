package org.example.reactor.nettymodel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单写， subReactor的执行器；
 * 不可行，因为subReactor的run方法的时候，就会阻塞，select阻塞了。
 */
public class MyEventLoopSyncExecutor {
    private AtomicInteger idx = new AtomicInteger();
    private SubReactor[] subReactors ;

    // 同步会阻塞住主进程，所以subReactor的开启，一定是在子线程中；
//    所以同步不可取；
    public MyEventLoopSyncExecutor(int subReactorNum){
        subReactors = new SubReactor[subReactorNum];
//         init and run
        for (int i = 0; i < subReactorNum; i++) {
            SubReactor subReactor = new SubReactor();
            subReactors[i] = subReactor;
            subReactor.run();
        }

    }

    public SubReactor next() {
        return subReactors[(idx.getAndDecrement())%subReactors.length];
    }

}
