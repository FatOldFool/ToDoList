package com.fatoldfool.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fatoldfool.todolist.taskstatus.TaskStatus;

public class Task {

    private final int id;
    private String title;
    private int priority;
    private final LocalDate creationDate;
    private LocalDateTime completionTime;
    private TaskStatus taskStatus;

    public Task(int id, String title, int priority, TaskStatus taskStatus) {
        this.id = id;
        setTitle(title);
        setPriority(priority);
        this.creationDate = LocalDate.now();
        this.completionTime = null;
        setTaskStatus(taskStatus);
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Название задачи не может быть пустым.");
        }

        String regex = "^[a-zA-Zа-яА-Я]+(\\s[a-zA-Zа-яА-Я]+)*$";

        if (!title.matches(regex)) {
            throw new IllegalArgumentException("❌ Название задачи может содержать только буквы русского и английского алфавита, \" +\r\n"
                    + "\"пробелы и не должно начинаться/заканчиваться пробелом.");
        }

        this.title = title;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 10) {
            throw new IllegalArgumentException("❌ Приоритет должен быть от 1 до 10.");
        }

        this.priority = priority;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        if (taskStatus == TaskStatus.COMPLETE) {
            this.completionTime = LocalDateTime.now();
        }

        this.taskStatus = taskStatus;
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

    @Override
    public String toString() {
        return String.format(
                "[%d] - %s | Приоритет: [%d] | Статус: [%s] | Дата создания: [%s] | Время выполнения: [%s]",
                id,
                title,
                priority,
                taskStatus.getDescription(),
                creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyy")),
                completionTime != null ? completionTime.format(DateTimeFormatter.ofPattern("dd-MM-yyy - HH:mm")) : "Отсутсвует"
        );
    }
}