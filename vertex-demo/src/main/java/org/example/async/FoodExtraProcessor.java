package org.example.async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class FoodExtraProcessor implements IExtraProcessor {
    MainProcessor mainProcessor;
    String label = "买零食";
    int part= 3;

    @Override
    public void process(Event event) {
        try {
            System.out.println("买零食，排队中");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("买零食排队完成，发送通知");
            event.finishPart(part);
            event.setResult("买零食");
            mainProcessor.process(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void register(MainProcessor mainProcessor) {
        this.mainProcessor = mainProcessor;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
