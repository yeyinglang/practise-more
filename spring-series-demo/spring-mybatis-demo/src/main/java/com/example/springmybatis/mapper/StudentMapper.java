package com.example.springmybatis.mapper;

import com.example.springmybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    void insert(@Param("student") Student student);


}
