package show;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import show.table.PredictionTableModel;
import show.table.ProductionTableModel;
import show.table.RegressionTableModel;
import show.table.TotalTableModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class RegressionProcess extends JFrame {
    private JScrollPane productionScrollPane;
    private JScrollPane predictionScrollPane;
    private JTable productionTable;
    private JTable predictionTable;
    private JPanel regressionProcessPanel;
    private JTable totalTable;
    private JTable regressionTable;
    private JScrollPane totalScrollPane;
    private JScrollPane regressionScrollPane;

    public RegressionProcess() throws SQLException {
        setContentPane(regressionProcessPanel);
        setTitle("Regression Calculation");
        setSize(1000,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        initTable();
    }

    public void initTable() throws SQLException {
        List<Production> productions = ProductionRepository.readAll();

        productionTable.setModel(new ProductionTableModel(productions));
        productionTable.setAutoCreateRowSorter(true);

        predictionTable.setModel(new PredictionTableModel(productions));
        predictionTable.setAutoCreateRowSorter(true);

        Prediction prediction = PredictionRepository.read();

        totalTable.setModel(new TotalTableModel(prediction));
        totalTable.setAutoCreateRowSorter(true);

        regressionTable.setModel(new RegressionTableModel(prediction));
        regressionTable.setAutoCreateRowSorter(true);
    }
}
