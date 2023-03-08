package org.example.mvc.model;

import org.example.mvc.controller.StudentController;
import org.example.mvc.service.StudentService;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.Annotation;

public class Teacher {

    public Teacher() {
    }

    public static void main(String[] args) {
        HelloDemo annotation = AnnotationUtils.getAnnotation(StudentService.class, HelloDemo.class);
        System.out.println(annotation);

    }
}
