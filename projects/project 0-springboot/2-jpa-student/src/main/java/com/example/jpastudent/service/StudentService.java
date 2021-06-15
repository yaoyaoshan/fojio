package com.example.jpastudent.service;

import com.example.jpastudent.dao.StudentDao;
import com.example.jpastudent.dao.UniversityClassDao;
import com.example.jpastudent.exceptions.StudentEmptyNameException;
import com.example.jpastudent.exceptions.StudentNotExistException;
import com.example.jpastudent.exceptions.UniversityClassNotExistException;
import com.example.jpastudent.model.Student;
import com.example.jpastudent.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentDao studentDao;
    private UniversityClassDao universityClassDao;

    @Autowired
    public StudentService(StudentDao studentDao, UniversityClassDao universityClassDao) {
        this.studentDao = studentDao;
        this.universityClassDao = universityClassDao;
    }

    public Student addStudent(Student student) {
        if (student.getName().isEmpty()) {
            throw new StudentEmptyNameException("student name cannot be empty");
        }
        return studentDao.save(student);
    }

    public Student updateStudent(Student student) {
        if (student.getId() == null || !studentDao.existsById(student.getId())) {
            throw new StudentNotExistException("student not found");
        }
        return studentDao.save(student);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }

    public Student assignClass(Long StudentId, Long ClassId) {
        if (!studentDao.existsById(StudentId)) {
            throw new StudentNotExistException("studentId not found: " + StudentId);
        }
        if (!universityClassDao.existsById(ClassId)) {
            throw new UniversityClassNotExistException("ClassId not found: " + ClassId);
        }

        Student student = getStudentById(StudentId).get();
        UniversityClass universityClass = universityClassDao.findById(ClassId).get();

        student.setUniversityClass(universityClass);
        return studentDao.save(student);
    }
}
