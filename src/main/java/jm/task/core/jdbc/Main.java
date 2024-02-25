package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Юрий", "Ширчков", (byte) 34);
        System.out.println("User с именем – Юрий добавлен в базу данных");

        userService.saveUser("Наташа", "Юпатова", (byte) 23);
        System.out.println("User с именем – Наташа добавлен в базу данных");

        userService.saveUser("Ольга", "Мелешкина", (byte) 45);
        System.out.println("User с именем – Ольга добавлен в базу данных");

        userService.saveUser("Роман", "Беляков", (byte) 15);
        System.out.println("User с именем – Роман добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("Не удалось получить список пользователей.");
        }

//        userService.cleanUsersTable();
//
//        userService.dropUsersTable();
    }

    }



