package view;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import view.table.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.util.List;

public class PredictionFrame extends BaseFrame {

    private JPanel predictionResultPanel;
    private JLabel titleLabel;
    private JTextField equationResult;
    private JScrollPane predictionResultPane;
    private JTable predictionResultTable;
    private JButton backButton;

    public PredictionFrame() {
        setActionListener();
        setData();
        setStyle();
        setPanel(predictionResultPanel, "Prediction Result", 700, 300);
    }

    private void setData() {
        try {
            insertEquation();
            initTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void insertEquation() throws SQLException {
        Prediction prediction = PredictionRepository.read();
        assert prediction != null;

        equationResult.setText(String.format("Y = %.4f + %.4f(X1) + %.4f(X2)",
                prediction.getB0(), prediction.getB1(), prediction.getB2()));
    }

    private void initTable() throws SQLException {
        List<Production> productions = ProductionRepository.readAll();
        Prediction prediction = PredictionRepository.read();

        predictionResultTable = createTable(new PredictionResultTable(productions, prediction));
        predictionResultPane.getViewport().add(predictionResultTable);
    }

    private void setStyle() {
        titleLabel.setBorder(new EmptyBorder(0,0,10,0));
        equationResult.setBorder(new EmptyBorder(10,10,10,10));

        setTableStyle(predictionResultTable);
    }

    private void setActionListener() {
        backButton.addActionListener(e -> {
            backToMain();
        });
    }
}
