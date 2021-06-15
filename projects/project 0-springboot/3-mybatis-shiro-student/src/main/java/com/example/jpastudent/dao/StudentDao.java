package com.example.jpastudent.dao;

import com.example.jpastudent.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentDao extends CrudRepository<Student, Long> {
    List<Student> findByName(String name);
}
