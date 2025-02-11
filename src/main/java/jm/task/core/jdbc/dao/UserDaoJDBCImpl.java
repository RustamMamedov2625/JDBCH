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
    @Override
    public void createUsersTable() {
        // SQL-запрос для создания таблицы, если она не существует
        String sql = "CREATE TABLE IF NOT EXISTS users ( " +
                     "id SERIAL PRIMARY KEY, " +
                     "name VARCHAR(50), " +
                     "lastName VARCHAR(50), " +
                     "age SMALLINT)";

        try(Connection connection = Util.getConnection(); // Устанавливаем соединение с БД
            Statement statement = connection.createStatement()){ // Создаем объект Statement для выполнения SQL-запросов
            statement.executeUpdate(sql);  //Выполняем SQL-запрос на создание таблицы
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Метод для удаления таблицы пользователей
    @Override
    public void dropUsersTable() {
        // SQL-запрос для удаления таблицы, если она существует
        String sql = "DROP TABLE IF EXISTS users";
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql); // Выполняем SQL-запрос на удаление таблицы
            System.out.println("Table was successfully dropped");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Метод для сохранения нового пользователя
    @Override
    public void saveUser(String name, String lastName, byte age) {
        // SQL-запрос для вставки нового пользователя
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate(); // Выполняем запрос на вставку пользователя
            System.out.println("User " + name + " was successfully saved");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Метод для удаления пользователя по ID
    @Override
    public void removeUserById(long id) {
        // SQL-запрос для удаления пользователя по ID
        String sql = "DELETE FROM users WHERE id = ?";
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(); // Выполняем запрос на удаление пользователя
            System.out.println("User " + id + " was successfully removed");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения всех пользователей
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(); // Список для хранения пользователей
        // SQL-запрос для получения всех пользователей
        String sql = "SELECT * FROM users";
        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){ // Выполняем запрос и получаем результат в ResultSet
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Метод для очистки таблицы пользователей
    @Override
    public void cleanUsersTable() {
        // SQL-запрос для удаления всех записей из таблицы
        String sql = "DELETE FROM users";
        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);  // Выполняем запрос на очистку таблицы
            System.out.println("Table was successfully cleaned");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
