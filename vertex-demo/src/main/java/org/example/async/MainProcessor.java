package org.example.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 主流程： 休息；
 * 附加流程：
 * 1 取快递（排队）耗时1小时
 * 2 逛超市（排队）耗时1小时
 * 3 买零食（排队）耗时1小时
 * 4 做核酸（排队）耗时1小时
 * <p>
 * 实际上这四个流程 可以在五分钟搞定，你却因为排队，耗时整整4小时；
 * 那么我就可以将其异步处理，等他处理完成，回调我处理下一步即可。
 */
public class MainProcessor {
    public static final ThreadPoolExecutor es = new ThreadPoolExecutor(10, 10, 100, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
//        asyncInvoke();
        syncInvoke();
        System.out.println("costTime: " + (System.currentTimeMillis() - startTime));
    }

    /**
     * 阻塞式调用方式；
     */
    public static void syncInvoke() {
        {
            MainProcessor mainProcessor = new MainProcessor();
            IExtraProcessor takeout = new TakeoutExtraProcessor();
            IExtraProcessor market = new MarketExtraProcessor();
            IExtraProcessor food = new FoodExtraProcessor();
            IExtraProcessor hesuan = new HeSuanExtraProcessor();

            mainProcessor.register(takeout);
            mainProcessor.register(market);
            mainProcessor.register(food);
            mainProcessor.register(hesuan);

            Event event = new Event();
//            阻塞式进行；
            takeout.process(event);
            market.process(event);
            food.process(event);
            hesuan.process(event);
            System.out.println("终于完成了。");
        }
    }

    /**
     * 异步执行回调函数；
     * 这样所有的行为不用等待，直接等待通知就行；
     */
    private static void asyncInvoke() {
        MainProcessor mainProcessor = new MainProcessor();
        IExtraProcessor takeout = new TakeoutExtraProcessor();
        IExtraProcessor market = new MarketExtraProcessor();
        IExtraProcessor food = new FoodExtraProcessor();
        IExtraProcessor hesuan = new HeSuanExtraProcessor();

        mainProcessor.register(takeout);
        mainProcessor.register(market);
        mainProcessor.register(food);
        mainProcessor.register(hesuan);

        Event event = new Event(Thread.currentThread());
//        es.execute(()->
        es.execute(() -> takeout.process(event));
        es.execute(() -> market.process(event));
        es.execute(() -> food.process(event));
        es.execute(() -> hesuan.process(event));
        System.out.println("终于处理完成，可以休息了。" + event.get());
        es.shutdown();
    }

    public MainProcessor register(IExtraProcessor extraProcessor) {
        extraProcessor.register(this);
        return this;
    }

    public void process(IExtraProcessor extraProcessor) {
        System.out.println("接收到通知，" + extraProcessor.getLabel() + " finished");
    }
}
