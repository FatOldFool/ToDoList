package com.fatoldfool.todolist.consoleui;

import com.fatoldfool.todolist.task.Task;
import com.fatoldfool.todolist.taskservice.TaskService;
import com.fatoldfool.todolist.userinputvalidator.UserInputValidator;
import com.fatoldfool.todolist.exceptions.*;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ConsoleUI {

    private final TaskService taskService;
    private final UserInputValidator userInputValidator;
    private final Scanner scanner;
    private static final String OPERATION_COMPLETED_SUCCESSFULLY = "Операция завершена успешно!";
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

            try {
                showMenu();
                String choice = requestMenuChoice();

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
            } catch (MyException e) {
                System.out.println(e.getMessage());
                enter();
            }
        }
    }

    private void add() {
        String taskName = repeatUntilEnteredCorrectly(this::requestTaskName);
        int taskPriority = repeatUntilEnteredCorrectly(() -> Integer.parseInt(requestTaskPriority()));
        taskService.addTask(taskName, taskPriority);
        operationCompletedSuccessfully();
    }

    private void delete() throws IncorrectIDException, EmptyTaskListException, NoTaskWithThisIDInTheList {
        userInputValidator.hasTasks(taskService.getTaskList());
        int taskID = repeatUntilEnteredCorrectly(() -> Integer.parseInt(requestID()));
        userInputValidator.isThereATaskWithThisIdInTheList(taskID, taskService.getTaskList());
        taskService.deleteTask(taskID);
        operationCompletedSuccessfully();
    }

    private void changeTaskNameAndPriority() throws IncorrectIDException, EmptyTaskListException, NoTaskWithThisIDInTheList,
            IncorrectNewTaskNameException, IncorrectNewTaskPriorityException {

        userInputValidator.hasTasks(taskService.getTaskList());

        int taskID = repeatUntilEnteredCorrectly(() -> Integer.parseInt(requestID()));
        userInputValidator.isThereATaskWithThisIdInTheList(taskID, taskService.getTaskList());

        String newTaskName = repeatUntilEnteredCorrectly(this::requestNewTaskName);
        int newTaskPriority = repeatUntilEnteredCorrectly(() -> Integer.parseInt(requestNewTaskPriority(taskID)));

        taskService.editTask(taskID, newTaskName, newTaskPriority);
        operationCompletedSuccessfully();

    }

    private void changeTaskStatus() throws IncorrectIDException, EmptyTaskListException, NoTaskWithThisIDInTheList,
            IncorrectNewTaskStatusException, SameTaskStatusException {

        userInputValidator.hasTasks(taskService.getTaskList());

        int taskID = repeatUntilEnteredCorrectly(() -> Integer.parseInt(requestID()));
        userInputValidator.isThereATaskWithThisIdInTheList(taskID, taskService.getTaskList());

        String newTaskStatus = repeatUntilEnteredCorrectly(this::requestNewTaskStatus);
        userInputValidator.isNewTaskStatusCorrect(newTaskStatus);
        userInputValidator.isNewTaskStatusTheSame(newTaskStatus, taskService.getTaskList());

        taskService.changeTaskStatus(taskID, newTaskStatus);
        operationCompletedSuccessfully();
    }

    private void showAll() throws EmptyTaskListException {
        userInputValidator.hasTasks(taskService.getTaskList());
        taskService.showAllTasks();
        operationCompletedSuccessfully();
    }

    private void filter() throws EmptyTaskListException {
        userInputValidator.hasTasks(taskService.getTaskList());
        taskService.filterTasksByStatus();
        operationCompletedSuccessfully();

    }

    private void findByKeyWord() throws EmptyTaskListException, NoTaskWithThisKeyWordInTheList {
        userInputValidator.hasTasks(taskService.getTaskList());

        String keyWord = requestKeyWord();
        userInputValidator.isThereATaskWithThisKeyWordInTheList(keyWord, taskService.getTaskList());

        taskService.findTaskByKeyWord(keyWord);
        operationCompletedSuccessfully();
    }

    private void statistics() throws EmptyTaskListException {
        userInputValidator.hasTasks(taskService.getTaskList());
        taskService.showStatistics();
        operationCompletedSuccessfully();
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

        if (!userInputValidator.isTheIdCorrect(input)) {
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

    private String requestNewTaskPriority(int taskID) throws IncorrectTaskPriorityException,
            IncorrectNewTaskPriorityException {

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

        if (!userInputValidator.isNewTaskStatusCorrect(input)) {
            throw new IncorrectNewTaskStatusException("Некорректный статус");
        }

        return input;
    }

    private String requestKeyWord() {
        System.out.println("Введите ключевое слово для поиска: ");

        return scanner.nextLine();
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
        System.out.println(OPERATION_COMPLETED_SUCCESSFULLY);
        System.out.println();
        enter();
    }

    private void enter() {
        System.out.println("\nНажмите <Enter> для продолжения...");
        scanner.nextLine();
    }

    private <T> T repeatUntilEnteredCorrectly(Callable<T> action) {
        while (true) {
            try {
                return action.call();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
