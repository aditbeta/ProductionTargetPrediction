package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {

    public static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/local";
        String username = "root";
        String password = "password";

        try  {
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
