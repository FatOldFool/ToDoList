package com.fatoldfool.todolist.exceptions;

public class SameTaskStatusException extends Exception {

    public SameTaskStatusException(String message) {
        super(message);
    }
}