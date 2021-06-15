package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @GetMapping
    @RequestMapping("/hello1")
    public String helloWorld1() {
        return "hello world 1";
    }

    @GetMapping
    @RequestMapping("/hello2")
    public String helloWorld2() {
        return "hello world 2";
    }

    @GetMapping
    public Student helloStudent() {
        Student s = new Student(UUID.randomUUID(), "xiuxiu");
        return s;
    }

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequestMapping("/get")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    @RequestMapping("/add")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "student added";
    }

    @PutMapping
    @RequestMapping("/update")
    public String updateStudent(@RequestBody Student student) {
        int result = studentService.updateStudent(student);
        if (result == 1) {
            return "student updated";
        }
        return "student not found";
    }

    @DeleteMapping(path = "delete/{id1}/{id2}")
    public String deleteStudent(@PathVariable("id1") UUID myId1, @PathVariable("id2") UUID myId2) {
        int result = studentService.deleteStudent(myId1);
        if (result == 1) {
            return "student deleted";
        }
        return "student not found";
    }
}
