package com.fatoldfool.todolist.exceptions;

public class NoTaskWithThisKeyWordInTheList extends Exception{

    public NoTaskWithThisKeyWordInTheList(String message) {
        super(message);
    }

}