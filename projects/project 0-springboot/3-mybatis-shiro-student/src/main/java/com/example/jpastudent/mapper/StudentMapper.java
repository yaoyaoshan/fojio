package com.example.jpastudent.mapper;

import com.example.jpastudent.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student WHERE name LIKE #{partName}")
    List<Student> getStudentsContainStrInName(@Param("partName") String str);
}
