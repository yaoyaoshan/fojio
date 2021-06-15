package com.example.jpastudent.exceptions;

public class StudentNotExistException extends RuntimeException {
    public StudentNotExistException(String message) {
        super(message);
    }
}
