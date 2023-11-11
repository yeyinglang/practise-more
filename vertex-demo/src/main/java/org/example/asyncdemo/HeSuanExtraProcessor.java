package org.example.asyncdemo;

import java.util.concurrent.TimeUnit;

public class HeSuanExtraProcessor implements IExtraProcessor {
    MainProcessor mainProcessor;
    String label = "核酸检测";
    int part= 4;

    @Override
    public void process(Event event) {
        try {
            System.out.println("核酸检测，排队中");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("核酸检测排队完成，发送通知");
            event.finishPart(part);
            event.setResult("做核酸");
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
