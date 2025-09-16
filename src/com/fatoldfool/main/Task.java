package com.fatoldfool.main;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Task {
    private String name;
    private String priority;
    private LocalDate creationDate;
    private String finishDateTime;
    private String status;

    public Task(String name, String priority, String status) {
        this.name = name;
        this.priority = priority;
        this.creationDate = LocalDate.now();
        finishDateTime = "Отсутсвует";
        this.status = status;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
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
