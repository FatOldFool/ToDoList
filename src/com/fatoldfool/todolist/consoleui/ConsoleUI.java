package com.fatoldfool.todolist.consoleui;

import com.fatoldfool.todolist.taskservice.TaskService;
import com.fatoldfool.todolist.userinputvalidator.UserInputValidator;

import java.util.Scanner;

public class ConsoleUI {

    private TaskService taskService;
    private UserInputValidator userInputValidator;
    private boolean isRunning;
    private Scanner userInput;

    public ConsoleUI() {
        taskService = new TaskService();
        userInputValidator = new UserInputValidator();
        isRunning = true;
        userInput = new Scanner(System.in);
        run();
    }

    private void run() {

        while (true) {

            showMenu();

            String choice = requestMenuChoice(); // <-- наш метод

            switch (choice) {
                case "1" -> taskService.addTask();
                case "2" -> {
                    int id = readInt("Введите ID задачи для удаления: ");
                    taskService.deleteTask(id);
                }
                case "3" -> {
                    int id = readInt("Введите ID задачи для редактирования: ");
                    taskService.editTask(id);
                }
                case "4" -> {
                    int id = readInt("Введите ID задачи для изменения статуса: ");
                    taskService.changeTaskStatus(id);
                }
                case "5" -> taskService.showAllTasks();
                case "6" -> taskService.filterTaskByStatus();
                case "7" -> taskService.findTaskByKeyWord();
                case "8" -> taskService.showStatistics();
                case "9" -> taskService.exit();
                default -> System.out.println("❗ Неизвестный пункт.");
            }

            System.out.println("\nНажмите <Enter> для продолжения...");
            new java.util.Scanner(System.in).nextLine(); // пауза
        }
    }

    public void showMenu() {
        System.out.println("\n=== Меню TodoList ===");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Редактировать задачу");
        System.out.println("4. Изменить статус задачи");
        System.out.println("5. Показать все задачи");
        System.out.println("6. Фильтр по статусу");
        System.out.println("7. Поиск задачи по ключевому слову");
        System.out.println("8. Статистика");
        System.out.println("9. Выход");
    }

    private String requestMenuChoice() {
        System.out.println("Выберите пункт меню: ");
        String input = userInput.nextLine();
        return input;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = new java.util.Scanner(System.in).nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value < 0) {
                    System.out.println("❗ Число должно быть положительным.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("❗ Введите корректное число.");
            }
        }
    }
}
