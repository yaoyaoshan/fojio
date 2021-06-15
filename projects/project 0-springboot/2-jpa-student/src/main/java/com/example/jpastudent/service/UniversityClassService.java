package com.example.jpastudent.service;

import com.example.jpastudent.dao.UniversityClassDao;
import com.example.jpastudent.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class UniversityClassService {
    private UniversityClassDao universityClassDao;

    @Autowired
    public UniversityClassService(UniversityClassDao universityClassDao) {
        this.universityClassDao = universityClassDao;
    }

    public List<UniversityClass> getAllUniversityClasses() {
        return (List<UniversityClass>) universityClassDao.findAll();
    }
}
