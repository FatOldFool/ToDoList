package com.fatoldfool.main;

import java.util.*;

public class TaskManager {

    private List<Task> tasks;
    private Scanner scanner;
    private static int number;

    TaskManager(){
        tasks = new ArrayList();
        scanner = new Scanner(System.in);
    }

    void addTask(String name, int priority){
        number++;
        tasks.add(new Task(name, priority, number));
    }

    void deleteTask(int num){
        if(num >= 0 && num <= tasks.size()){
            tasks.remove(num);
            number--;
        }else{
            System.out.println("Неверный номер!");
        }
    }

    void changeName(int num){
        String newName = scanner.nextLine();
        if(num >= 0 && num <= tasks.size()){
            tasks.get(num).setName(newName);
        }else{
            System.out.println("Неверный номер!");
        }
    }

    void changePriority(int num){
        int newPriority = scanner.nextInt();
        if(num >= 0 && num <= tasks.size()){
            tasks.get(num).setPriority(newPriority);
        }else{
            System.out.println("Неверный номер!");
        }
    }

    void changeStatus(int num){
        String newStatus = scanner.nextLine();
        if(num >= 0 && num <= tasks.size()){
            if(newStatus.equals("Выполнена") || newStatus.equals("В процессе") || newStatus.equals("Не выполнена")){
                tasks.get(num).setStatus(newStatus);
            }else{
                System.out.println("Неверный статус!");
            }
        }else{
            System.out.println("Неверный номер!");
        }
    }

    void showAllTasks(){
        for(Task task : tasks){
            System.out.println(task);
        }
    }
}
