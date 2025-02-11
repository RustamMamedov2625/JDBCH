package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Класс для работы с подключением к базе данных
public class Util {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; //Адрес бд
    private static final String USER = "postgres";
    private static final String PASSWORD = "251005";

    //Метод для установления подключения с бд. Возвращает либо объект Connection или null в случае ошибки.
    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(SQLException e){
            e.printStackTrace(); //Выводим ошибку, если подключение не удалось.
        }
        return connection;
    }
}
