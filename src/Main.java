import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionObject;
import repository.ProductionRepository;
import show.MainFrame;
import show.ProductionInput;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        setUIFont (new javax.swing.plaf.FontUIResource("Segoe UI", Font.PLAIN,16));

        new MainFrame();
    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
}
