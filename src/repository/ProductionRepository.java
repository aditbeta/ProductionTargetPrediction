package repository;

import entity.Production;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionRepository extends Database {

    public static Production insert(ProductionObject production) throws SQLException {
        String sql = "INSERT INTO production VALUES(null,?,?,?,?)";
        PreparedStatement preparedStmt = connect().prepareStatement(sql);
        preparedStmt.setString (1, production.getMonth());
        preparedStmt.setDouble (2, production.getSell());
        preparedStmt.setDouble (3, production.getOrder());
        preparedStmt.setDouble (4, production.getTarget());
        preparedStmt.execute();

        return new Production(production);
    }

    public static List<Production> readAll() throws SQLException {
        List<Production> productions = new ArrayList<>();
        String sql = "SELECT * FROM production";
        PreparedStatement preparedStmt = connect().prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        while(rs.next()) {
            ProductionObject production = new ProductionObject(
                    rs.getInt("id"),
                    rs.getString("month"),
                    rs.getDouble("sell"),
                    rs.getDouble("order"),
                    rs.getDouble("target"));
            productions.add(new Production(production));
        }

        return productions;
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM production WHERE id=?";
        PreparedStatement preparedStmt = connect().prepareStatement(sql);
        preparedStmt.setDouble (1, id);
        preparedStmt.execute();
    }
}
