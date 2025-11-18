package com.fatoldfool.todolist.exceptions;

public class IncorrectNewTaskStatusException extends Exception{

    public IncorrectNewTaskStatusException(String message) {
        super(message);
    }
}