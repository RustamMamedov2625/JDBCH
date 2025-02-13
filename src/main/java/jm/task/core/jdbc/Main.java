package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 30);
        userService.saveUser("Andrey", "Andreev", (byte) 20);
        userService.saveUser("Bogdan", "Bogdanov", (byte) 40);
        userService.saveUser("Cygan", "Cygankov", (byte) 50);

        System.out.println("List of users:");

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        System.out.println("Table was cleaned");

        userService.dropUsersTable();
        System.out.println("Table was dropped");
    }
}
