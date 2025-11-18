package com.fatoldfool.todolist.todolistrunner;

import com.fatoldfool.todolist.consoleui.ConsoleUI;
import com.fatoldfool.todolist.taskservice.TaskService;
import com.fatoldfool.todolist.userinputvalidator.UserInputValidator;

import java.util.ArrayList;

public class ToDoListRunner {

    public static void main(String[] args) {

        ConsoleUI consoleUI = new ConsoleUI(new TaskService(new ArrayList<>()), new UserInputValidator());

    }

}