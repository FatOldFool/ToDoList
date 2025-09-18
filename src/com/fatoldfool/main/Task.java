package com.fatoldfool.main;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private int number;
    private String name;
    private int priority;
    private LocalDate creationDate;
    private String status;
    private LocalDateTime leadTime;

    Task(String name, int priority, int number) {
        this.number = number;
        this.name = name;
        this.priority = priority;
        creationDate = LocalDate.now();
        this.status = "Невыполнена";
    }

    //[Номер]. Название задачи | Приоритет: [число] | Статус: [Статус] | Дата создания: [дата] | Время выполнения: [время/отсутствует]

    @Override
    public String toString() {
        return
                name + " | " +
                        "Приоритет: " + priority + " | " +
                        "Статус: " + status +
                        "Дата создания: " + creationDate + " | " +
                        "Время выполнения:" + leadTime + " | ";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(LocalDateTime leadTime) {
        this.leadTime = leadTime;
    }



}
