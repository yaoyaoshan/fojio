package com.example.jpastudent.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="university_class")
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "universityClass")
    List<Student> students;

    public UniversityClass(Long id, Integer grade, Integer number) {
        this.id = id;
        this.grade = grade;
        this.number = number;
    }

    public UniversityClass() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Primary ID: " + getId();
        str += "Grade: " + getGrade();
        str += "Number: " + getNumber();
        return str;
    }
}
