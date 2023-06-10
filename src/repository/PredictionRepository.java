package repository;

import entity.Prediction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PredictionRepository extends BaseRepository {

    public static void insert(Prediction prediction) throws SQLException {
        String sql = "INSERT INTO prediction VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connect().prepareStatement(sql);
        statement.setDouble (1, prediction.getTotalX1());
        statement.setDouble (2, prediction.getTotalX2());
        statement.setDouble (3, prediction.getTotalY());
        statement.setDouble (4, prediction.getTotalX1X1());
        statement.setDouble (5, prediction.getTotalX2X2());
        statement.setDouble (6, prediction.getTotalYY());
        statement.setDouble (7, prediction.getTotalX1Y());
        statement.setDouble (8, prediction.getTotalX2Y());
        statement.setDouble (9, prediction.getTotalX1X2());
        statement.setDouble (10, prediction.getB0());
        statement.setDouble (11, prediction.getB1());
        statement.setDouble (12, prediction.getB2());
        prediction.setId(statement.executeUpdate());
    }

    public static void update(Prediction prediction) throws SQLException {
        String sql = "UPDATE prediction SET totalX1 = ?, totalX2 = ?, totalY = ?, totalX1X1 = ?, totalX2X2 = ?, totalYY = ?, totalX1Y = ?, totalX2Y = ?, totalX1X2 = ?, b0 = ?, b1 = ?, b2 = ?";
        PreparedStatement statement = connect().prepareStatement(sql);
        statement.setDouble (1, prediction.getTotalX1());
        statement.setDouble (2, prediction.getTotalX2());
        statement.setDouble (3, prediction.getTotalY());
        statement.setDouble (4, prediction.getTotalX1X1());
        statement.setDouble (5, prediction.getTotalX2X2());
        statement.setDouble (6, prediction.getTotalYY());
        statement.setDouble (7, prediction.getTotalX1Y());
        statement.setDouble (8, prediction.getTotalX2Y());
        statement.setDouble (9, prediction.getTotalX1X2());
        statement.setDouble (10, prediction.getB0());
        statement.setDouble (11, prediction.getB1());
        statement.setDouble (12, prediction.getB2());
        prediction.setId(statement.executeUpdate());
    }

    public static Prediction read() throws SQLException {
        String sql = "SELECT * FROM prediction";
        PreparedStatement preparedStmt = connect().prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        if(rs.next()) {
            return new Prediction(
                    rs.getInt("id"),
                    rs.getDouble("totalX1"),
                    rs.getDouble("totalX2"),
                    rs.getDouble("totalY"),
                    rs.getDouble("totalX1X1"),
                    rs.getDouble("totalX2X2"),
                    rs.getDouble("totalYY"),
                    rs.getDouble("totalX1Y"),
                    rs.getDouble("totalX2Y"),
                    rs.getDouble("totalX1X2"),
                    rs.getDouble("b0"),
                    rs.getDouble("b1"),
                    rs.getDouble("b2"));
        }

        return null;
    }
}
