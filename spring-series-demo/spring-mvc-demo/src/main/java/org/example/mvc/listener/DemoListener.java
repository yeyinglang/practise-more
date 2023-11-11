package org.example.mvc.listener;

import org.example.mvc.listener.type.StudentType;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("vet?");
    }

    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(StudentType.class);
        System.out.println(resolvableType);
    }
}
