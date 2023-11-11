package org.example.asyncdemo;

public interface IExtraProcessor {
    void register(MainProcessor mainProcessor);

    String getLabel();

    void process(Event event);
}
