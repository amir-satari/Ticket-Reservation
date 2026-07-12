package ir.maktabsharif.util;

import ir.maktabsharif.exception.DatabaseConnectionEcxeption;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/ticket_reservation";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Nima.s.1";

    public static Connection getConnection(){
        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            return connection;

        }catch (SQLException e){
            throw new DatabaseConnectionEcxeption("Connection failed!");
        }
    }
}
