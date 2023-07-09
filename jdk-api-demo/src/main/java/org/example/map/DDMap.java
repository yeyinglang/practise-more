package org.example.map;

import org.example.util.Utils;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 场景
 * 1. 去餐厅---点餐---等餐（看书） --- 吃饭 -- 结账
 * 分支线：
 * 服务员： 找位置---下单---厨师做菜（每个厨师 做一道，西红柿炒鸡蛋，凉拌黄瓜） --端菜--  服务员结账 ---开发票；
 */
public class DDMap {

    public static void main(String[] args) throws Exception {
        CompletableFuture.supplyAsync(()->{
            Utils.toMsg("hello owlrd.===" + Thread.currentThread().isDaemon());
            Utils.sleep(1);
            return "qqq";
        }, new ThreadPoolExecutor(1,1,11, TimeUnit.MINUTES,new ArrayBlockingQueue<>(10)));
//        demoApply();
//        demoCompose();
        Utils.toMsg("点餐");
        Utils.toMsg("守护线程：" + Thread.currentThread().isDaemon());
//        异步线程是 服务员；
        CompletableFuture completeWaiter = CompletableFuture.runAsync(() -> {
            Utils.toMsg("点西红柿成功，为您下单");
            Utils.sleep(1);
            Utils.toMsg("waiter 1 守护线程：" + Thread.currentThread().isDaemon());
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                    Utils.toMsg("厨师一在做西红柿炒鸡蛋");
                    Utils.sleep(3);
                    Utils.toMsg("西红柿炒鸡蛋做完了");
                    Utils.toMsg("chief1 守护线程：" + Thread.currentThread().isDaemon());
                    return "egg is ok";
                }),
                (x, y) -> {
                    return x + y;
                }
        ).thenAccept(x -> {
            Utils.toMsg("点黄瓜成功，为您下单");
            Utils.sleep(1);
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                    Utils.toMsg("厨师二做凉拌黄瓜");
                    Utils.sleep(1);
                    Utils.toMsg("黄瓜做完了");
                    Utils.toMsg("chief 2 守护线程：" + Thread.currentThread().isDaemon());
                    return "cucumber is ok";
                }),
                (x, y) -> {
                    return x + y;
                }
        ).whenComplete((x1, x2) -> {
            Utils.toMsg(x1 + x2);
        });
        completeWaiter.get();
        Utils.toMsg("准备吃饭");
    }

    private static void demoCompose() throws InterruptedException, ExecutionException {
        Utils.toMsg("点餐");
//        异步线程是 服务员；
        CompletableFuture completeWaiter = CompletableFuture.runAsync(() -> {
            Utils.toMsg("点西红柿成功，为您下单");
            Utils.sleep(1);
        }).thenComposeAsync(x -> {
            Utils.toMsg(x);
            return CompletableFuture.supplyAsync(() -> {
                Utils.toMsg("厨师一在做西红柿炒鸡蛋");
                Utils.sleep(3);
                Utils.toMsg("西红柿炒鸡蛋做完了");
                return "egg is ok";
            });
        }).thenAccept(x -> {
            Utils.toMsg("点黄瓜成功，为您下单");
            Utils.sleep(1);
        }).thenComposeAsync(x -> {
                    Utils.toMsg(x + "111");
                    return CompletableFuture.supplyAsync(() -> {
                        Utils.toMsg("厨师二做凉拌黄瓜");
                        Utils.sleep(1);
                        Utils.toMsg("黄瓜做完了");
                        return "cucumber is ok";
                    });
                }
        ).whenComplete((x1, x2) -> {
            Utils.toMsg(x1 + x2);
        });
        completeWaiter.get();
        Utils.toMsg("准备吃饭");
    }

    private static void demoApply() throws InterruptedException, ExecutionException {
        Utils.toMsg("点餐");
//        异步线程是 服务员；
        CompletableFuture completeWaiter = CompletableFuture.runAsync(() -> {
            Utils.toMsg("点餐成功，为您下单");
        }).thenApplyAsync(x -> {
            Utils.toMsg("厨师一在做西红柿炒鸡蛋");
            Utils.sleep(2);
            Utils.toMsg("西红柿炒鸡蛋做完了");
            return "egg is ok";
        }).handleAsync((result, throwmsg) -> {
            Utils.toMsg("厨师二做凉拌黄瓜");
            Utils.sleep(1);
            Utils.toMsg("黄瓜做完了");
            return "cucumber is ok";
        }).thenAccept(x -> {
            Utils.toMsg("上一个结果：" + x);
        });
        Object o = completeWaiter.get();
        Utils.toMsg("准备吃饭");
    }

    /**
     * 异步执行，
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void demoAsync() throws ExecutionException, InterruptedException {
        Utils.toMsg("点餐");
//        异步线程是 服务员；
        CompletableFuture completeWaiter = CompletableFuture.runAsync(() -> {
            Utils.toMsg("点餐成功，为您下单");
        }).thenApplyAsync(x -> {
            Utils.toMsg("厨师一在做西红柿炒鸡蛋");
            Utils.sleep(2);
            Utils.toMsg("西红柿炒鸡蛋做完了");
            return "egg is ok";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                    Utils.toMsg("厨师二做凉拌黄瓜");
                    Utils.sleep(1);
                    Utils.toMsg("黄瓜做完了");
                    return "cucumber is ok";
                }), (x1, x2) ->
                        x1 + " , " + x2
        ).whenComplete((x1, x2) -> {
            Utils.toMsg(x1 + x2);
        });
        completeWaiter.get();
        Utils.toMsg("准备吃饭");
    }

}
