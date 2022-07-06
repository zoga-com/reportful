package ru.zoga_com.reportful.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection connection = null;

    private static void startConnect() { // поднять подключение
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:reports.db");
            Log.printMessage("База репортов подключена.", Database.class);
        } catch(SQLException e) { Log.printException(e.getMessage(), Database.class); }
    }

    public static void startDB() { // поднять бд в принципе
        startConnect();
        createTablesIfNotExists();
    }

    private static void createTablesIfNotExists() {
        try {
            Statement state = connection.createStatement();
            state.execute("CREATE TABLE IF NOT EXISTS reports (id integer PRIMARY KEY, message text, author text, reported text, time integer(128), channel text);");
            state.execute("CREATE TABLE IF NOT EXISTS messages (id integer PRIMARY KEY, message text, author text, time integer(128), channel text);");
        } catch(SQLException e) { Log.printException(e.getMessage(), Database.class); }
    }

    public static ResultSet getQuery(String query) { // получить значение
        try {
            PreparedStatement prepState = connection.prepareStatement(query);
            ResultSet result = prepState.executeQuery();
            return result;
        } catch(SQLException e) { 
            Log.printException(e.getMessage(), Database.class);
             return null; 
        }
    }

    public static void insertQuery(String query) { // создать значение
        try {
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.executeUpdate();
        } catch(SQLException e) { Log.printException(e.getMessage(), Database.class); }
    }
}
