package com.example.springmybatis.proxydemo;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LoggerInterceptor2 implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before interceptor2");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after interceptor2");
        return null;
    }
}
