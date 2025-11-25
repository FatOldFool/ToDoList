package com.fatoldfool.todolist.taskservice;

import com.fatoldfool.todolist.task.Task;

import java.util.List;
import java.util.ArrayList;

import com.fatoldfool.todolist.taskstatus.TaskStatus;

public class TaskService {

    private final List<Task> tasks;
    private int nextID = 0;

    public TaskService(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String name, int priority) {
        tasks.add(new Task(++nextID, name, priority, TaskStatus.NOT_COMPLETE));
    }

    public void deleteTask(int taskID) {
        tasks.removeIf(task -> task.getId() == taskID);
    }

    public void editTask(int taskID, String newName, int newPriority) {
        for (Task task : this.tasks) {
            if (task.getId() == taskID) {
                task.setTitle(newName);
                task.setPriority(newPriority);
            }
        }
    }

    public void changeTaskStatus(int taskID, String newTaskStatus) {
        for (Task task : tasks) {
            if (task.getId() == taskID) {
                switch (newTaskStatus) {
                    case "1" -> task.setTaskStatus(TaskStatus.NOT_COMPLETE);
                    case "2" -> task.setTaskStatus(TaskStatus.IN_PROGRESS);
                    case "3" -> task.setTaskStatus(TaskStatus.COMPLETE);
                }
            }
        }
    }

    public void showAllTasks() {
        for (Task task : this.tasks) {
            System.out.println(task);
        }
    }

    public void filterTasksByStatus() {
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

    public void findTaskByKeyWord(String keyWord) {
        List<Task> found = new ArrayList<>();
        String normilized = keyWord.toLowerCase();

        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(normilized)) {
                found.add(task);
            }
        }

        for (Task task : found) {
            System.out.println(task);
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

    public List<Task> getTaskList() {
        return tasks;
    }

}