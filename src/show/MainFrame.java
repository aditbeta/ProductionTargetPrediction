package show;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import show.table.ProductionTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JButton inputButton;
    private JButton calculateButton;
    private JButton resultButton;
    private JPanel mainPanel;
    private JTable productionTable;
    private JButton deleteButton;

    private List<Production> productions;

    public MainFrame() throws SQLException {
        setContentPane(mainPanel);
        setTitle("Production Input Form");
        setSize(450,300);
        setLocationRelativeTo(null);
        setVisible(true);

        productions = ProductionRepository.readAll();

        recalculate();
        initTable();

        inputButton.addActionListener(e -> {
            new ProductionInput(productions);
            dispose();
        });
        calculateButton.addActionListener(e -> {
            try {
                new RegressionProcess();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
        deleteButton.addActionListener(e -> {
            new DeleteInput(productions);
            dispose();
        });
        resultButton.addActionListener(e -> {
            try {
                new RegressionResult();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
    }

    public void recalculate() throws SQLException {
        Prediction prediction = new Prediction();
        prediction.calculateTotal(productions);
        prediction.calculatePrediction();

        if (PredictionRepository.read() != null) {
            PredictionRepository.update(prediction);
            return;
        }
        PredictionRepository.insert(prediction);
    }

    public void initTable() {
        productionTable.setModel(new ProductionTableModel(productions));
        productionTable.setAutoCreateRowSorter(true);
    }
}
