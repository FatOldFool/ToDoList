package com.fatoldfool.todolist.userinputvalidator;

import com.fatoldfool.todolist.task.Task;

import java.util.List;

public class UserInputValidator {

    public boolean isValidTaskId(int id, List<Task> tasks) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return true;
            }
        }

        return false;
    }

    public boolean isValidTaskName(String name) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ Название задачи не может быть пустым.");
            return false;
        }

        String trimmed = name.trim();

        if (!name.equals(trimmed)) {
            System.out.println("❌ Название задачи не может начинаться или заканчиваться пробелом.");
            return false;
        }

        String regex = "^[a-zA-Zа-яА-Я]+(\\s[a-zA-Zа-яА-Я]+)*$";

        if (!name.matches(regex)) {
            System.out.println("❌ Название задачи может содержать только буквы русского и английского алфавита, " +
                    "пробелы и не должно начинаться/заканчиваться пробелом.");
            return false;
        }

        return true;
    }

    public boolean isValidTaskPriority(String input) {

        if (input == null || input.trim().isEmpty()) {
            System.out.println("❌ Приоритет не может быть пустым.");
            return false;
        }

        String trimmed = input.trim();

        if (!trimmed.matches("^\\d+$")) {
            System.out.println("❌ Приоритет должен быть числом.");
            return false;
        }

        try {
            int priority = Integer.parseInt(trimmed);

            if (priority < 1 || priority > 10) {
                System.out.println("❌ Приоритет должен быть от 1 до 10.");
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            System.out.println("❌ Некорректное число приоритета.");
            return false;
        }
    }

    public boolean hasTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("📭 Нет созданных задач. Добавьте первую задачу.");
            return false;
        }
        return true;
    }
}
