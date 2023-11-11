package org.example.asyncdemo;

import java.util.concurrent.TimeUnit;

public class TakeoutExtraProcessor implements IExtraProcessor {
    MainProcessor mainProcessor;
    String label = "取快递";
    int part= 1;
    @Override
    public void process(Event event) {
        try {
            System.out.println("等待快递，排队中");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("快递排队完成，发送通知");
            event.finishPart(part);
            event.setResult("等快递");
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
