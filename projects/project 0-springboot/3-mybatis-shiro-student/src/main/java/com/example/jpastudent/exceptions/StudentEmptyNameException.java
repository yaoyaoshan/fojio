package com.example.jpastudent.exceptions;

public class StudentEmptyNameException extends RuntimeException {
    public StudentEmptyNameException(String message) {
        super(message);
    }
}
