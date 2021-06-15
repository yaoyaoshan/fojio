package com.example.jpastudent.controller;

import com.example.jpastudent.exceptions.StudentEmptyNameException;
import com.example.jpastudent.exceptions.StudentNotExistException;
import com.example.jpastudent.exceptions.UniversityClassNotExistException;
import com.example.jpastudent.model.Student;
import com.example.jpastudent.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/contain-name")
    // localhost:8080/api/student/contain-name?name=Jack
    public List<Student> getStudentsContainName(@RequestParam() String name) {
        return studentService.getStudentsContainStrInName(name);
    }

    @RequestMapping("/register")
    @PostMapping
    @RequiresPermissions("student:write")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.addStudent(student);
            return ResponseEntity.ok("student registered: " + student.toString());
        } catch (StudentEmptyNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> modifyStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.updateStudent(student);
            return ResponseEntity.ok("student updated: " + student.toString());
        } catch (StudentNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "assignclass/{sid}/{cid}")
    // localhost:8080/api/student/assignclass/2/1
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId) {
        try {
            Student updatedStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("student assigned to class: " + updatedStudent.toString());
        } catch (StudentNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UniversityClassNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 另一种写法
    @PostMapping("/assign-class")
    // localhost:8080/api/student/assign-class?studentId=2&classId=1
    public ResponseEntity<String> assignClass2(@RequestParam Long studentId,
                                               @RequestParam Long classId) {
        try {
            Student updatedStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("student assigned to class: " + updatedStudent.toString());
        } catch (StudentNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UniversityClassNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
