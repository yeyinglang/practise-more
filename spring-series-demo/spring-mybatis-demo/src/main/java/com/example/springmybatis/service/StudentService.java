package com.example.springmybatis.service;

import com.example.springmybatis.dao.StudentDao;
import com.example.springmybatis.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StudentService {
    @Resource
    private StudentDao studentDao;

    public void insertStudent(){
        Student student = new Student();
        student.setName("123");
        studentDao.insert(student);

    }

    @Transactional
    public void queryStudent() {
        System.out.println("hello");
    }
}
