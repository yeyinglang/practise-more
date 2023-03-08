package org.example.reactor.nettymodel;

import org.example.utils.PrintUtils;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class SubReactor implements Runnable {
    private Selector selector;

    SubReactor() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 由于完成对连接后的 处理工作
     */
    @Override
    public void run() {
        // 在这里睡眠，让出CPU，优先注册进来；
//    眠100ms；
//  需要让register注册在前， select在后，因为他们会有共同锁对象；select如果阻塞，会一直维持着那个锁对象。
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                int selectedNum = selector.select();
                PrintUtils.printMsg(Thread.currentThread().getName(), "get ", selectedNum, " event");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                iterator.remove();
                Runnable attachment = (Runnable) next.attachment();
                attachment.run();
            }
        }
    }

    public Selector subSelector() {
        return selector;
    }
}
