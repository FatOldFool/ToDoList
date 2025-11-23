package com.fatoldfool.todolist.exceptions;

public class SameTaskStatusException extends MyException {

    public SameTaskStatusException(String message) {
        super(message);
    }
}