package com.fatoldfool.todolist.exceptions;

public class EmptyTaskListException extends MyException{

    public EmptyTaskListException(String message) {
        super(message);
    }
}