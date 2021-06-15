package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("dao1") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    public int deleteStudent(UUID id) {
        return studentDao.deleteStudentById(id);
    }
}
