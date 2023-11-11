package com.example.springmybatis.proxydemo;

import net.sf.cglib.proxy.Enhancer;

public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Logger.class);
        enhancer.setCallback(new LoggerInterceptor());
        Logger logger = (Logger) enhancer.create();
        logger.warn();

    }
}
