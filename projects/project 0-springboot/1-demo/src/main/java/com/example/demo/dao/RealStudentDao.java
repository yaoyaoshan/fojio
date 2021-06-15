package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dao1")
public class RealStudentDao implements StudentDao {
    private static List<Student> Database = new ArrayList<>();

    @Override
    public Optional<Student> findStudentById(UUID id) {
        for (Student s : Database) {
            if (s.getId().equals(id)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Student> selectAllStudents() {
        return Database;
    }

    @Override
    public int insertStudent(Student student) {
        UUID id = UUID.randomUUID();
        Database.add(new Student(id, student.getName()));
        return 1;
    }

    @Override
    public int updateStudent(Student student) {
        Optional<Student> optionalStudent = findStudentById(student.getId());
        if (!optionalStudent.isPresent()) {
            return 0;
        }

        for (int i = 0; i < Database.size(); ++i) {
            if (student.getId().equals(Database.get(i).getId())) {
                Database.set(i, student);
                break;
            }
        }

        return 1;
    }

    @Override
    public int deleteStudentById(UUID id) {
        Optional<Student> optionalStudent = findStudentById(id);
        if (!optionalStudent.isPresent()) {
            return 0;
        }

        Database.remove(optionalStudent);

        return 1;
    }
}
