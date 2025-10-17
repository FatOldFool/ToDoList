package com.fatoldfool.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private int id;
    private String title;
    private int priority;
    private LocalDate creationDate;
    private LocalDateTime completionTime;
    private boolean isCompleted;
    private TaskStatus taskStatus;

    public Task(int id, String title, int priority) {
        this.id = id;
        this.title = title;
        setPriority(priority);
        this.creationDate = LocalDate.now();
        this.completionTime = null;
        this.isCompleted = false;
        this.taskStatus = TaskStatus.NOT_COMPLETE;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 10) {
            throw new IllegalArgumentException("Приоритет должен быть от 1 до 10.");
        }
        this.priority = priority;
    }


    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
        this.isCompleted = taskStatus == TaskStatus.COMPLETE;
        if (taskStatus == TaskStatus.COMPLETE) {
            this.completionTime = LocalDateTime.now();
        } else {
            this.completionTime = null;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%d. [%s] Приоритет [%d] %s | Создано: %s | Время выполнения: %s | Выполнено: %s",
                id,
                taskStatus.getDescription(),
                priority,
                title,
                creationDate.toString(),
                completionTime != null ? completionTime.toString() : "Нет",
                taskStatus == TaskStatus.COMPLETE ? "Да" : "Нет"
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public enum TaskStatus {
        COMPLETE("Выполнена"),
        IN_PROGRESS("В процессе"),
        NOT_COMPLETE("Не выполнена");

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
}
