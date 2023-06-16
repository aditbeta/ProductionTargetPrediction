package show;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import show.table.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PredictionResult extends BaseFrame {

    private JScrollPane predictionResultPane;
    private JLabel titleLabel;
    private JTextField equationResult;
    private JPanel predictionResultPanel;
    private JTable predictionResultTable;
    private JButton backButton;

    public PredictionResult() throws SQLException {
        setStyle();
        setActionListener();

        setData();

        setPanel(predictionResultPanel, "Prediction Result", 700, 300);
    }

    private void setData() throws SQLException {
        insertEquation();
        initTable();
    }

    private void insertEquation() throws SQLException {
        Prediction prediction = PredictionRepository.read();
        assert prediction != null;

        equationResult.setText(String.format("Y = %.4f + %.4f(X1) + %.4f(X2)",
                prediction.getB0(), prediction.getB1(), prediction.getB2()));
    }

    public void initTable() throws SQLException {
        List<Production> productions = ProductionRepository.readAll();
        Prediction prediction = PredictionRepository.read();

        predictionResultTable.setModel(new RegressionResultTable(productions, prediction));
        predictionResultTable.setAutoCreateRowSorter(true);
    }

    private void setStyle() {
        titleLabel.setBorder(new EmptyBorder(0,0,10,0));
        equationResult.setBorder(new EmptyBorder(10,10,10,10));

        predictionResultTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        predictionResultTable.getTableHeader().setOpaque(false);
        predictionResultTable.getTableHeader().setBackground(new Color(32, 136, 203));
        predictionResultTable.getTableHeader().setForeground(new Color(255, 255, 255));
        predictionResultTable.setRowHeight(30);
    }

    private void setActionListener() {
        backButton.addActionListener(e -> {
            backToMain();
        });
    }
}
