package com.fatoldfool.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    private List<String> menu;
    private List<Task> tasks;
    private int userInput;
    private Scanner scanner;

    TaskManager() {
        menu = new ArrayList<>();
        tasks = new ArrayList<>();
        addMenuToList();
        showMenu();
        userInputProcess();
    }

    void addMenuToList() {
        menu.add("1. Добавить задачу");
        menu.add("2. Удалить задачу");
        menu.add("3. Отредактировать задачу");
        menu.add("4. Показать все задачи");
        menu.add("5. Фильтровать задачи по статусу");
        menu.add("6. Найти задачу по ключевому слову");
        menu.add("7. Изменить статус задачи");
        menu.add("8. Показать статистику");
        menu.add("9. Выход");
    }

    void showMenu() {
        System.out.println("Добро пожаловать в приложение \"Управление списком задач!\"\n");

        for (String menuItem : menu) {
            System.out.println(menuItem);
        }

        System.out.print("\nВыберите действие: ");
    }

    void userInputProcess() {

        while(true){
            scanner = new Scanner(System.in);
            userInput = scanner.nextInt();
            if(userInput == 1){
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите название задачи: ");
                String taskName = scanner.nextLine();
                System.out.print("Введите приоритет (число): ");
                int priority = Integer.parseInt(scanner.nextLine());
                System.out.println("Задача добавлена!\n");

                System.out.println();
            } else if (userInput == 9) {
                System.exit(0);
            }
            showMenu();
        }


    }


    void addTask(String taskName, int priority) {
        tasks.add(new Task(taskName, priority));
    }

    void deleteTask() {
        if (userInput == 0) {
            System.out.println("Задачи с таким номером не существует!");
        } else {
            tasks.remove(userInput - 1);
        }
    }


    void editName(int number, String name) {
        Task task = tasks.get(number - 1);
        task.setName(name);
    }

    void editPriority(int number, int priority) {
        Task task = tasks.get(number - 1);
        task.setPriority(priority);
    }

    void showAllTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

}
