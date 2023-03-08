package com.example.springmybatis.dao;

import com.example.springmybatis.mapper.StudentMapper;
import com.example.springmybatis.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class StudentDao {

    @Resource
    StudentMapper studentMapper;
    public void insert(Student student) {
        studentMapper.insert(student);
    }
}
