package com.fatoldfool.todolist.userinputvalidator;

import java.util.List;

import com.fatoldfool.todolist.exceptions.*;
import com.fatoldfool.todolist.task.Task;
import com.fatoldfool.todolist.taskstatus.TaskStatus;

public class UserInputValidator {

    public void hasTasks(List<Task> tasks) throws EmptyTaskListException {
        if (tasks == null || tasks.isEmpty()) {
            throw new EmptyTaskListException("📭 Нет созданных задач. Добавьте первую задачу.");
        }
    }

    public boolean isMenuChoiceCorrect(String input) {

        if (input == null || input.isEmpty() || input.startsWith("0")) {
            return false;
        }

        try {
            int number = Integer.parseInt(input);
            return number >= 1 && number <= 9;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean isTheIdCorrect(String id) {

        return id != null && !id.isEmpty() && !id.startsWith("0");
    }

    public boolean isTaskNameCorrect(String input) {

        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        String trimmed = input.trim();

        if (!input.equals(trimmed)) {
            return false;
        }

        String regex = "^[a-zA-Zа-яА-Я]+(\\s[a-zA-Zа-яА-Я]+)*$";

        return input.matches(regex);
    }

    public boolean isNewTaskNameCorrect(String input, List<Task> tasks) {

        if (isTaskNameCorrect(input)) {
            for (Task task : tasks) {
                if (task.getTitle().equals(input)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isTaskPriorityCorrect(String input) {

        if (input == null || input.trim().isEmpty() || input.startsWith("0")) {
            return false;
        }

        String trimmed = input.trim();

        if (!input.equals(trimmed)) {
            return false;
        }

        try {
            int priority = Integer.parseInt(input);

            return priority >= 1 && priority <= 10;

        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean isNewTaskPriorityCorrect(int taskID, String input, List<Task> tasks) {
        if (isTaskPriorityCorrect(input)) {
            int newTaskPriority = Integer.parseInt(input);
            for (Task task : tasks) {
                if (task.getId() == taskID && task.getPriority() == newTaskPriority) {
                    return false;
                }
            }
        }

        return true;
    }

    public void isThereATaskWithThisIdInTheList(int id, List<Task> tasks) throws NoTaskWithThisIDInTheList {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return;
            }
        }
        throw new NoTaskWithThisIDInTheList("Задача с ID - " + id + ", отсутсвует в списке!");
    }

    public void isThereATaskWithThisKeyWordInTheList(String keyWord, List<Task> tasks)
            throws NoTaskWithThisKeyWordInTheList {
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(keyWord)) {
                return;
            }
        }

        throw new NoTaskWithThisKeyWordInTheList(
                "Задача с ключевым словом - \"" + keyWord + "\", отсутсвует в списке!");
    }

    public boolean isNewTaskStatusCorrect(String input) {

        if (input == null || input.trim().isEmpty() || input.startsWith("0")) {
            return false;
        }

        String trimmed = input.trim();

        if (!input.equals(trimmed)) {
            return false;
        }

        try {
            int newTaskStatus = Integer.parseInt(input);

            if (newTaskStatus < 1 || newTaskStatus > 3) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;

    }

    public void isNewTaskStatusTheSame(String input, List<Task> tasks) throws SameTaskStatusException {
        int newTaskStatus = Integer.parseInt(input);

        for (Task task : tasks) {
            if ((task.getTaskStatus() == TaskStatus.NOT_COMPLETE && newTaskStatus == 1)
                    || (task.getTaskStatus() == TaskStatus.IN_PROGRESS && newTaskStatus == 2)
                    || (task.getTaskStatus() == TaskStatus.COMPLETE && newTaskStatus == 3)) {
                throw new SameTaskStatusException("Данный статус уже установлен");
            }

        }
    }

}