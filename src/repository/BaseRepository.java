package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {

    public static Connection connect() {
        String database = "predictor";
        String url = "jdbc:mysql://localhost:3306/" + database;
        String username = "root";
        String password = "password";

        try  {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
