package com.example.springimport.configuration;


import com.example.springimport.bean.Student;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不好用；因为没有被spring识别到；
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Import(Student.class)
public @interface DemoEnumImport {
}
