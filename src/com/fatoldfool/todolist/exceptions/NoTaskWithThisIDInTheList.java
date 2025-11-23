package com.fatoldfool.todolist.exceptions;

public class NoTaskWithThisIDInTheList extends MyException{

    public NoTaskWithThisIDInTheList(String message) {
        super(message);
    }
}