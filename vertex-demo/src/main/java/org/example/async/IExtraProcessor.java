package org.example.async;

import java.util.concurrent.Future;

public interface IExtraProcessor {
    void register(MainProcessor mainProcessor);

    String getLabel();

    void process(Event event);
}
