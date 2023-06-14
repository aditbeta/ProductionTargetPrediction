package repository;

import entity.Production;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductionRepository extends BaseRepository {

    public static Production insert(ProductionObject production) throws SQLException {
        String sql = "INSERT INTO production VALUES(null,?,?,?,?,?)";
        PreparedStatement preparedStmt = connect().prepareStatement(sql);
        preparedStmt.setString (1, production.getMonth());
        preparedStmt.setDouble (2, production.getSell());
        preparedStmt.setDouble (3, production.getOrder());
        preparedStmt.setDouble (4, production.getTarget());
        preparedStmt.setDouble (5, monthNum(production.getMonth()));
        preparedStmt.execute();

        return new Production(production);
    }

    public static List<Production> readAll() throws SQLException {
        List<Production> productions = new ArrayList<>();
        String sql = "SELECT * FROM production ORDER BY monthNum";
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

    public static void delete(String month) throws SQLException {
        String sql = "DELETE FROM production WHERE month=?";
        PreparedStatement preparedStmt = connect().prepareStatement(sql);
        preparedStmt.setString (1, month);
        preparedStmt.execute();
    }

    private static int monthNum(String month) {
         return (new HashMap<String, Integer>() {{
             put("Januari", 1);
             put("Februari", 2);
             put("Maret", 3);
             put("April", 4);
             put("Mei", 5);
             put("Juni", 6);
             put("Juli", 7);
             put("Agustus", 8);
             put("September", 9);
             put("Oktober", 10);
             put("November", 11);
             put("Desember", 12);
        }}).get(month);
    }
}
