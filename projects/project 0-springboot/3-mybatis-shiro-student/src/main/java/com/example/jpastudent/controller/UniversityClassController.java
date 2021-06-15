package com.example.jpastudent.controller;

import com.example.jpastudent.model.UniversityClass;
import com.example.jpastudent.service.UniversityClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/university-class")
public class UniversityClassController {
    private UniversityClassService universityClassService;

    @Autowired
    public UniversityClassController(UniversityClassService universityClassService) {
        this.universityClassService = universityClassService;
    }

    @GetMapping
    List<UniversityClass> getAllClasses() {
        return universityClassService.getAllUniversityClasses();
    }
}
