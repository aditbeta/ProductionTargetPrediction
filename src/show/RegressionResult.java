package show;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import show.table.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

public class RegressionResult extends JFrame {
    private JLabel equationLabel;
    private JPanel regressionResultPanel;
    private JTable regressionResultTable;
    private JScrollPane regressionResultPane;

    public RegressionResult() throws SQLException {
        setContentPane(regressionResultPanel);
        setTitle("Regression Result");
        setSize(450,300);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    new MainFrame();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        insertEquation();
        initTable();
    }

    private void insertEquation() throws SQLException {
        Prediction prediction = PredictionRepository.read();

        equationLabel.setText(String.format("%s | Y = %.4f + %.4f(X1) + %.4f(X2)",
                equationLabel.getText(), prediction.getB0(), prediction.getB1(), prediction.getB2()));
    }

    public void initTable() throws SQLException {
        List<Production> productions = ProductionRepository.readAll();
        Prediction prediction = PredictionRepository.read();

        regressionResultTable.setModel(new RegressionResultTable(productions, prediction));
        regressionResultTable.setAutoCreateRowSorter(true);
    }
}
