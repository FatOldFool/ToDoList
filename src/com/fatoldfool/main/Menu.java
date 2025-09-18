package com.fatoldfool.main;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private boolean isRunning;
    private TaskManager tskmngr;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.tskmngr = new TaskManager();
    }

    public void start() {
        while (isRunning) {
            displayMenu();
            int choice = getUserChoice();
            processChoice(choice);
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Главное меню ---");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Отредактировать задачу");
        System.out.println("4. Показать все задачи");
        System.out.println("5. Фильтровать задачи по статусу");
        System.out.println("6. Найти задачу по ключевому слову");
        System.out.println("7. Изменить статус задачи");
        System.out.println("8. Показать статистику");
        System.out.println("9. Выход");
        System.out.print("Выберите пункт меню: ");
    }

    private int getUserChoice() {
        while (true) {
            System.out.print("Введите число: ");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("Ошибка: строка пуста!");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число!");
            }
        }
    }

    private void processChoice(int choice) {
        if(choice == 1){
            System.out.println("Введите название задачи: ");
            String taskName = scanner.nextLine();
            System.out.println("Введите приоритет: ");
            int taskPriority = scanner.nextInt();
            tskmngr.addTask(taskName, taskPriority);
        } else if (choice == 2) {

        } else if (choice == 3) {

        } else if (choice == 4) {
            tskmngr.showAllTasks();
        } else if (choice == 5) {

        } else if (choice == 6) {

        } else if (choice == 7) {

        } else if (choice == 8) {

        }else {
            isRunning = false;
            System.out.println("Программа завершает работу!");
        }
    }

    private void viewInformation() {
        System.out.println("\n--- Информация ---");
        System.out.println("Здесь будет отображаться информация");
    }

    private void performAction() {
        System.out.println("\n--- Выполнение действия ---");
        System.out.println("Действие выполнено");
    }

    private void showSettings() {
        System.out.println("\n--- Настройки ---");
        System.out.println("Здесь будут настройки");
    }

    private void shutdown() {
        System.out.println("\nПрограмма завершает работу. До свидания!");
        isRunning = false;
    }


}
