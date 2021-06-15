package com.example.demo.model;

import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

public class Student {
    private final UUID id;
    private String name;

    public Student(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
