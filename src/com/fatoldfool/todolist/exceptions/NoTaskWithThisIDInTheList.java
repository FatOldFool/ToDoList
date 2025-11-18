package com.fatoldfool.todolist.exceptions;

public class NoTaskWithThisIDInTheList extends Exception{

    public NoTaskWithThisIDInTheList(String message) {
        super(message);
    }
}