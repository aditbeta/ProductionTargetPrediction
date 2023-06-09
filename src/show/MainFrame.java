package show;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import show.table.ProductionTableModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    private JButton inputButton;
    private JButton calculateButton;
    private JButton resultButton;
    private JPanel mainPanel;
    private JTable productionTable;
    private JButton deleteButton;

    public MainFrame() throws SQLException {
        setContentPane(mainPanel);
        setTitle("Production Input Form");
        setSize(450,300);
        setLocationRelativeTo(null);
        setVisible(true);

        recalculate();

        initTable();
        inputButton.addActionListener(e -> {
            new ProductionInput();
            dispose();
        });
        calculateButton.addActionListener(e -> {
            try {
                new RegressionProcess();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteButton.addActionListener(e -> {
            new DeleteInput();
            dispose();
        });
    }

    public void recalculate() throws SQLException {
        List<Production> productions = ProductionRepository.readAll();

        Prediction prediction = new Prediction();
        prediction.calculateTotal(productions);
        prediction.calculatePrediction();
        PredictionRepository.insert(prediction);
    }

    public void initTable() throws SQLException {
        List<Production> productions = ProductionRepository.readAll();
        productionTable.setModel(new ProductionTableModel(productions));
        productionTable.setAutoCreateRowSorter(true);
    }
}
