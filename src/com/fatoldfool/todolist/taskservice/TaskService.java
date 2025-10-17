package com.fatoldfool.todolist.taskservice;

import com.fatoldfool.todolist.task.Task;
import com.fatoldfool.todolist.userinputvalidator.UserInputValidator;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskService {

    private List<Task> tasks;
    private int nextID;
    private UserInputValidator userInputValidator;
    Scanner scanner;

    public TaskService() {
        this.tasks = new ArrayList<>();
        this.nextID = 1;
        userInputValidator = new UserInputValidator();
        scanner = new Scanner(System.in);
    }

    private String askNewTaskName() {
        String title;
        while (true) {
            System.out.print("Введите новое название задачи: ");
            title = scanner.nextLine().trim();
            if (userInputValidator.isValidTaskName(title)) {
                return title;
            }
            System.out.println("❗ Некорректное название. Попробуйте ещё раз.");
        }
    }

    private int askNewTaskPriority() {
        String input;
        while (true) {
            System.out.print("Введите новый приоритет (1–10): ");
            input = scanner.nextLine().trim();
            if (userInputValidator.isValidTaskPriority(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("❗ Приоритет должен быть числом от 1 до 10.");
        }
    }

    private Task.TaskStatus askNewTaskStatus() {
        while (true) {
            System.out.println("\nВыберите новый статус:");
            System.out.println("1 – НЕ ВЫПОЛНЕНА (NOT_COMPLETE)");
            System.out.println("2 – В ПРОЦЕССЕ (IN_PROGRESS)");
            System.out.println("3 – ВЫПОЛНЕНА (COMPLETE)");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    return Task.TaskStatus.NOT_COMPLETE;
                case "2":
                    return Task.TaskStatus.IN_PROGRESS;
                case "3":
                    return Task.TaskStatus.COMPLETE;
                default:
                    System.out.println("❗ Неправильный ввод. Выберите 1, 2 или 3.");
            }
        }
    }

    private String userInputNewTaskName() {
        String title = "";

        while (true) {
            System.out.print("Введите название задачи: ");
            title = scanner.nextLine().trim();

            if (userInputValidator.isValidTaskName(title)) {
                return title;
            }

            System.out.println("Пожалуйста, введите корректное название.");
        }
    }

    private int userInputNewTaskPriority() {
        String input = "";

        while (true) {
            System.out.print("Введите приоритет (1–10): ");
            input = scanner.nextLine().trim();

            if (userInputValidator.isValidTaskPriority(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Пожалуйста, введите корректный приоритет.");
        }
    }

    public void addTask() {
        String name = userInputNewTaskName();
        int priority = userInputNewTaskPriority();
        tasks.add(new Task(nextID++, name, priority));
        System.out.println("✅ Задача успешно добавлена!");
    }

    public void deleteTask(int id) {

        if (userInputValidator.isValidTaskId(id, tasks)) {
            Iterator<Task> it = tasks.iterator();
            while (it.hasNext()) {
                if (it.next().getId() == id) {
                    it.remove();
                    System.out.println("🗑️ Задача с id=" + id + " удалена.");
                    break;
                }
            }
        } else {
            System.out.println("❗ Задача с id=" + id + " не найдена.");
        }

    }

    public void editTask(int id) {
        if (!userInputValidator.isValidTaskId(id, tasks)) {
            System.out.println("❗ Задача с id=" + id + " не найдена.");
            return;
        }

        Task taskToEdit = null;
        for (Task t : tasks) {
            if (t.getId() == id) {
                taskToEdit = t;
                break;
            }
        }

        System.out.println("\nЧто вы хотите изменить?");
        System.out.println("1 – Название");
        System.out.println("2 – Приоритет");
        System.out.println("3 – Оба поля");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                String newName = askNewTaskName();
                taskToEdit.setTitle(newName);
                System.out.println("✅ Название задачи обновлено.");
                break;
            case "2":
                int newPriority = askNewTaskPriority();
                taskToEdit.setPriority(newPriority);
                System.out.println("✅ Приоритет задачи обновлен.");
                break;
            case "3":
                newName = askNewTaskName();
                newPriority = askNewTaskPriority();
                taskToEdit.setTitle(newName);
                taskToEdit.setPriority(newPriority);
                System.out.println("✅ Название и приоритет задачи обновлены.");
                break;
            default:
                System.out.println("❗ Неправильный ввод. Операция отменена.");
        }
    }

    public void changeTaskStatus(int id) {

        if (!userInputValidator.isValidTaskId(id, tasks)) {
            System.out.println("❗ Задача с id=" + id + " не найдена.");
            return;
        }

        Task task = null;
        for (Task t : tasks) {
            if (t.getId() == id) {
                task = t;
                break;
            }
        }

        System.out.println("\nТекущий статус задачи: " + task.getTaskStatus());
        Task.TaskStatus newStatus = askNewTaskStatus();

        task.setTaskStatus(newStatus);
        System.out.println("✅ Статус задачи изменён на " + newStatus);
    }

    public void showAllTasks() {

        if (userInputValidator.hasTasks(tasks)) {
            System.out.println("\n=== Все задачи ===");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void filterTaskByStatus() {

        if (userInputValidator.hasTasks(tasks)) {
            List<Task> completedTasks = new ArrayList<>();
            List<Task> inProgressTasks = new ArrayList<>();
            List<Task> notCompletedTasks = new ArrayList<>();

            for (Task task : tasks) {
                switch (task.getTaskStatus()) {
                    case COMPLETE:
                        completedTasks.add(task);
                        break;
                    case IN_PROGRESS:
                        inProgressTasks.add(task);
                        break;
                    case NOT_COMPLETE:
                        notCompletedTasks.add(task);
                        break;
                }
            }

            if (!completedTasks.isEmpty()) {
                System.out.println("\n✅ Выполненные задачи:");
                for (Task task : completedTasks) {
                    System.out.println(task);
                }
            }

            if (!inProgressTasks.isEmpty()) {
                System.out.println("\n⏳ В процессе:");
                for (Task task : inProgressTasks) {
                    System.out.println(task);
                }
            }

            if (!notCompletedTasks.isEmpty()) {
                System.out.println("\n❌ Не выполненные задачи:");
                for (Task task : notCompletedTasks) {
                    System.out.println(task);
                }
            }
        }
    }

    private String userInputKeyword() {
        while (true) {
            System.out.print("\nВведите слово для поиска в названиях задач: ");
            String kw = scanner.nextLine().trim();
            if (!kw.isEmpty()) {
                return kw;
            }
            System.out.println("❗ Строка поиска не может быть пустой. Попробуйте ещё раз.");
        }
    }

    private List<Task> searchByKeyword(String normalized) {
        List<Task> found = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle() != null &&
                    task.getTitle().toLowerCase().contains(normalized)) {
                found.add(task);
            }
        }
        return found;
    }

    public void findTaskByKeyWord() {

        if (!userInputValidator.hasTasks(tasks)) {
            //System.out.println("📭 Список задач пока пуст.");
            return;
        }

        String rawKeyword = userInputKeyword();
        String normalized = rawKeyword.toLowerCase();

        List<Task> found = searchByKeyword(normalized);

        if (found.isEmpty()) {
            System.out.println("🔍 По запросу \"" + rawKeyword + "\" ничего не найдено.");
        } else {
            System.out.println("\n🔎 Найдено задач: " + found.size());
            for (Task t : found) {
                System.out.println(t);
            }
        }
    }

    public void showStatistics() {
        int complete = 0;
        int inProgress = 0;
        int notComplete = 0;
        double totalPriority = 0;
        int taskCount = 0;

        for (Task task : tasks) {
            switch (task.getTaskStatus()) {
                case COMPLETE:
                    complete++;
                    break;
                case IN_PROGRESS:
                    inProgress++;
                    break;
                case NOT_COMPLETE:
                    notComplete++;
                    break;
            }
            totalPriority += task.getPriority();
            taskCount++;
        }

        System.out.println("\n📊 === Статистика задач ===");

        System.out.println("✅ Выполнено: " + complete);
        System.out.println("⏳ В процессе: " + inProgress);
        System.out.println("❌ Не выполнено: " + notComplete);

        double averagePriority = totalPriority / taskCount;
        System.out.printf("📈 Средний приоритет: %.2f\n", averagePriority);

        System.out.println("📊 Всего задач: " + taskCount);
    }

    public void exit() {
        System.out.println("Выход из приложения... Спасибо за использование TodoList!");
        System.exit(0);
    }

    public List<Task> getTask() {
        return tasks;
    }
}