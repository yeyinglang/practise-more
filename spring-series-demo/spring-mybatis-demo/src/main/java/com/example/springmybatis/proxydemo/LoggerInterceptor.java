package com.example.springmybatis.proxydemo;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LoggerInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before interceptor");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after interceptor");
        return null;
    }
}
