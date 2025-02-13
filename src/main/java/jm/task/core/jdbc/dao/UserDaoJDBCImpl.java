package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    // Метод для создания таблицы пользователей

    public void createUsersTable() {

    }
    // Метод для удаления таблицы пользователей
    public void dropUsersTable() {

    }

    // Метод для сохранения нового пользователя
    public void saveUser(String name, String lastName, byte age) {

    }
    // Метод для удаления пользователя по ID
    public void removeUserById(long id) {

    }

    // Метод для получения всех пользователей

    public List<User> getAllUsers() {
       return null;
    }

    // Метод для очистки таблицы пользователей

    public void cleanUsersTable() {

    }
}
