package com.fatoldfool.main;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Task {
    private String name;
    private int priority;
    private LocalDate creationDate;
    private String finishDateTime;
    private String status = "Невыполнена";

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.creationDate = LocalDate.now();
        finishDateTime = "Отсутсвует";

    }




//

    void filterTasksByStatus(){

    }

    void findTaskByKeyword(){

    }

    void changeTaskStatus(){

    }

    void showStatistics(){

    }

    void exit(){

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

    public String getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(String finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
