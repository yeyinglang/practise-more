package org.example.async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MarketExtraProcessor implements IExtraProcessor {
    MainProcessor mainProcessor;
    String label = "逛超市";
    int part= 2;
    @Override
    public void process(Event event) {
        try {
            System.out.println("逛超市，排队中");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("逛超市排队完成，发送通知");
            event.finishPart(part);
            event.setResult("逛超市");
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
