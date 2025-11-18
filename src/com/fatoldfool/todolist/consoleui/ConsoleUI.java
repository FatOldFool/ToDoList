package com.fatoldfool.todolist.consoleui;

import com.fatoldfool.todolist.task.Task;
import com.fatoldfool.todolist.taskservice.TaskService;
import com.fatoldfool.todolist.userinputvalidator.UserInputValidator;
import com.fatoldfool.todolist.exceptions.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private TaskService taskService;
    private UserInputValidator userInputValidator;
    private Scanner scanner;
    private static final String OPERATION_COMPLETED_SUCCESSFULY = "Операция завершена успешно!";
    private boolean isRunning;

    public ConsoleUI(TaskService taskService, UserInputValidator userInputValidator) {
        this.taskService = taskService;
        this.userInputValidator = userInputValidator;
        scanner = new Scanner(System.in);
        isRunning = true;
        run();
    }

    private void run() {
        while (isRunning) {
            showMenu();

            String choice = null;

            try {
                choice = requestMenuChoice();
            } catch (IncorrectMenuChoiceException e) {
                System.out.println(e.getMessage());
                enter();
                run();
            }

            switch (choice) {

                case "1" -> add();
                case "2" -> delete();
                case "3" -> changeTaskNameAndPriority();
                case "4" -> changeTaskStatus();
                case "5" -> showAll();
                case "6" -> filter();
                case "7" -> findByKeyWord();
                case "8" -> statistics();
                case "9" -> exit();

            }

        }

    }

    private void add() {
        String taskName = null;
        int taskPriority = 0;
        boolean isTaskNameCorrect = false;
        boolean isTaskPriorityCorrect = false;

        while (!isTaskNameCorrect) {
            try {
                taskName = requestTaskName();
                isTaskNameCorrect = true;
            } catch (IncorrectTaskNameException e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        while (!isTaskPriorityCorrect) {
            try {
                taskPriority = Integer.parseInt(requestTaskPriority());
                isTaskPriorityCorrect = true;
            } catch (IncorrectTaskPriorityException e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        taskService.addTask(taskName, taskPriority);
        operationCompletedSuccessfully();
    }

    private void delete() {
        int taskID = 0;
        boolean isTaskIdCorrect = false;
        boolean isListContainsTaskWithThisID = false;

        try {
            userInputValidator.hasTasks(taskService.getTaskList());
        } catch (EmptyTaskListException e) {
            System.out.println(e.getMessage());
            enter();
            run();
        }

        while (!isTaskIdCorrect) {
            try {
                taskID = Integer.parseInt(requestID());
                isTaskIdCorrect = true;
            } catch (IncorrectIDException e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        while (!isListContainsTaskWithThisID) {
            try {
                userInputValidator.isThereAtaskWithThisIdInTheList(taskID, taskService.getTaskList());
                isListContainsTaskWithThisID = true;
            } catch (NoTaskWithThisIDInTheList e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        taskService.deleteTask(taskID);
        operationCompletedSuccessfully();
    }

    private void changeTaskNameAndPriority() {
        int taskID = 0;
        String newTaskName = null;
        int newTaskPriority = 0;

        boolean isTaskIdCorrect = false;
        boolean isThereATaskInTheList = false;

        boolean isNewTaskNameCorrect = false;
        boolean isNewTaskPriorityCorrect = false;

        try {
            userInputValidator.hasTasks(taskService.getTaskList());
        } catch (EmptyTaskListException e) {
            System.out.println(e.getMessage());
            enter();
            run();
        }

        while (!isTaskIdCorrect) {
            try {
                taskID = Integer.parseInt(requestID());
                isTaskIdCorrect = true;
            } catch (IncorrectIDException e) {
                System.out.println(e.getMessage());
                enter();
            }

        }

        while (!isThereATaskInTheList) {
            try {
                userInputValidator.isThereAtaskWithThisIdInTheList(taskID, taskService.getTaskList());
                isThereATaskInTheList = true;
            } catch (NoTaskWithThisIDInTheList e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        while (!isNewTaskNameCorrect) {
            try {
                newTaskName = requestNewTaskName();
                isNewTaskNameCorrect = true;
            } catch (IncorrectTaskNameException e) {
                System.out.println(e.getMessage());
                enter();
            } catch (IncorrectNewTaskNameException e) {
                System.out.println(e.getMessage());
                enter();
            }

        }

        while (!isNewTaskPriorityCorrect) {
            try {
                newTaskPriority = Integer.parseInt(requestNewTaskPriority(taskID));
                isNewTaskPriorityCorrect = true;
            } catch (IncorrectTaskPriorityException e) {
                System.out.println(e.getMessage());
                enter();
            } catch (IncorrectNewTaskPriorityException e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        taskService.editTask(taskID, newTaskName, newTaskPriority);
        operationCompletedSuccessfully();

    }

    private void changeTaskStatus() {
        int taskID = 0;
        boolean isTaskIdCorrect = false;
        boolean isListContainsTaskWithThisID = false;
        String newTaskStatus = null;
        boolean isNewTaskStatusCorrect = false;
        boolean isNewTaskStatusTheSame = false;

        try {
            userInputValidator.hasTasks(taskService.getTaskList());
        } catch (EmptyTaskListException e) {
            System.out.println(e.getMessage());
            enter();
            run();
        }

        while (!isTaskIdCorrect) {
            try {
                taskID = Integer.parseInt(requestID());
                isTaskIdCorrect = true;
            } catch (IncorrectIDException e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        while (!isListContainsTaskWithThisID) {
            try {
                userInputValidator.isThereAtaskWithThisIdInTheList(taskID, taskService.getTaskList());
                isListContainsTaskWithThisID = true;
            } catch (NoTaskWithThisIDInTheList e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        while (!isNewTaskStatusCorrect || !isNewTaskStatusTheSame) {
            try {
                newTaskStatus = requestNewTaskStatus();
                userInputValidator.isNewTaskStatusCorrect(newTaskStatus, taskService.getTaskList());
                isNewTaskStatusCorrect = true;

                userInputValidator.isNewTaskStatusTheSame(newTaskStatus, taskService.getTaskList());
                isNewTaskStatusTheSame = true;
            } catch (IncorrectNewTaskStatusException e) {
                System.out.println(e.getMessage());
                enter();
            } catch (SameTaskStatusException e) {
                System.out.println(e.getMessage());
                enter();
            }
        }

        taskService.changeTaskStatus(taskID, newTaskStatus);
        operationCompletedSuccessfully();
    }

    private void showAll() {
        try {
            userInputValidator.hasTasks(taskService.getTaskList());
            taskService.showAllTasks();
            operationCompletedSuccessfully();
        } catch (EmptyTaskListException e) {
            System.out.println(e.getMessage());
            enter();
        } finally {
            run();
        }
    }

    private void filter() {
        try {
            userInputValidator.hasTasks(taskService.getTaskList());
            taskService.filterTasksByStatus();
            operationCompletedSuccessfully();
        } catch (EmptyTaskListException e) {
            System.out.println(e.getMessage());
            enter();
        } finally {
            run();
        }
    }

    private void findByKeyWord() {
        String keyWord = null;
        boolean isListContainsTaskWhitThisKeyWord = false;

        try {
            userInputValidator.hasTasks(taskService.getTaskList());
        } catch (EmptyTaskListException e) {
            System.out.println(e.getMessage());
            enter();
            run();
        }

        keyWord = requestKeyWord();

        while (!isListContainsTaskWhitThisKeyWord) {
            try {
                userInputValidator.isThereATaskWithThisKeyWordInTheList(keyWord, taskService.getTaskList());
                isListContainsTaskWhitThisKeyWord = true;
            } catch (NoTaskWithThisKeyWordInTheList e) {
                System.out.println(e.getMessage());
                enter();
                run();
            }
        }

        taskService.findTaskByKeyWord(keyWord);
        operationCompletedSuccessfully();
    }

    private void statistics() {
        try {
            userInputValidator.hasTasks(taskService.getTaskList());
            taskService.showStatistics();
            operationCompletedSuccessfully();
        } catch (EmptyTaskListException e) {
            System.out.println(e.getMessage());
            enter();
        } finally {
            run();
        }
    }

    private void exit() {
        System.out.println("Выход из приложения... Спасибо за использование TodoList!");
        isRunning = false;
        System.exit(0);
    }

    private String requestMenuChoice() throws IncorrectMenuChoiceException {
        System.out.println("Выберите пункт меню: ");
        String input = scanner.nextLine();

        if (!userInputValidator.isMenuChoiceCorrect(input)) {
            throw new IncorrectMenuChoiceException("Неверный пункт меню!");
        }

        return input;
    }

    private String requestID() throws IncorrectIDException {
        System.out.println("Введите ID задачи: ");
        String input = scanner.nextLine();

        if (!userInputValidator.isTheIdCorrect(input, taskService.getTaskList())) {
            throw new IncorrectIDException("Неверный ID!");
        }
        return input;
    }

    private String requestTaskName() throws IncorrectTaskNameException {
        System.out.println("Введите название задачи: ");
        String input = scanner.nextLine();

        if (!userInputValidator.isTaskNameCorrect(input)) {
            throw new IncorrectTaskNameException("❗ Некорректное название. Попробуйте ещё раз.");
        }
        return input;
    }

    private String requestNewTaskName() throws IncorrectTaskNameException, IncorrectNewTaskNameException {

        System.out.println("Введите новое название задачи: ");
        String newTaskName = scanner.nextLine();
        List<Task> tasks = taskService.getTaskList();

        if (!userInputValidator.isTaskNameCorrect(newTaskName)) {
            throw new IncorrectTaskNameException("❗ Некорректное название. Попробуйте ещё раз.");
        }

        if (!userInputValidator.isNewTaskNameCorrect(newTaskName, tasks)) {
            throw new IncorrectNewTaskNameException("Задача с таким названием уже существует!");
        }
        return newTaskName;
    }

    private String requestTaskPriority() throws IncorrectTaskPriorityException {
        System.out.println("Введите приоритет задачи: ");
        String taskPriority = scanner.nextLine();

        if (!userInputValidator.isTaskPriorityCorrect(taskPriority)) {
            throw new IncorrectTaskPriorityException("Некорректный приоритет!");
        }
        return taskPriority;
    }

    private String requestNewTaskPriority(int taskID)
            throws IncorrectTaskPriorityException, IncorrectNewTaskPriorityException {
        System.out.println("Введите новый приоритет задачи (1 - 10): ");
        String newTaskPriority = scanner.nextLine();
        List<Task> tasks = taskService.getTaskList();

        if (!userInputValidator.isTaskPriorityCorrect(newTaskPriority)) {
            throw new IncorrectTaskPriorityException("Некорректный приоритет!");
        }

        if (!userInputValidator.isNewTaskPriorityCorrect(taskID, newTaskPriority, tasks)) {
            throw new IncorrectNewTaskPriorityException(
                    "Приоритет: " + newTaskPriority + " уже установлен в задаче № " + taskID);
        }
        return newTaskPriority;
    }

    private String requestNewTaskStatus() throws IncorrectNewTaskStatusException {
        System.out.println("Введите новый статус задачи: ");
        System.out.println("1 – НЕ ВЫПОЛНЕНА (NOT_COMPLETE)");
        System.out.println("2 – В ПРОЦЕССЕ (IN_PROGRESS)");
        System.out.println("3 – ВЫПОЛНЕНА (COMPLETE)");
        System.out.print("Ваш выбор: ");
        String input = scanner.nextLine();

        if (!userInputValidator.isNewTaskStatusCorrect(input, taskService.getTaskList())) {
            throw new IncorrectNewTaskStatusException("Некорректный статус");
        }
        return input;
    }

    private String requestKeyWord() {
        System.out.println("Введите ключевое слово для поиска: ");
        String keyWord = scanner.nextLine();

        return keyWord;
    }

    private void showMenu() {

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

    private void operationCompletedSuccessfully() {
        System.out.println();
        System.out.println(OPERATION_COMPLETED_SUCCESSFULY);
        System.out.println();
        enter();
    }

    private void enter() {
        System.out.println("\nНажмите <Enter> для продолжения...");
        new java.util.Scanner(System.in).nextLine();
    }

}