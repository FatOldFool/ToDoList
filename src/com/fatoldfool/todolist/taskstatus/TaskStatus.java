package com.fatoldfool.todolist.taskstatus;

public enum TaskStatus {
    COMPLETE("Выполнена"), IN_PROGRESS("В процессе"), NOT_COMPLETE("Не выполнена");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}