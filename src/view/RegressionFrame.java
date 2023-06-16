package view;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import view.table.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class RegressionFrame extends BaseFrame {

    private JPanel regressionPanel;
    private JLabel titleLabel;
    private JScrollPane productionScrollPane;
    private JScrollPane predictionScrollPane;
    private JScrollPane regressionScrollPane;
    private JScrollPane totalScrollPane;
    private JTable productionTable;
    private JTable predictionTable;
    private JTable totalTable;
    private JTable regressionTable;
    private JButton backButton;

    public RegressionFrame() {
        setActionListener();
        setData();
        setStyle();
        setPanel(regressionPanel, "Regression Calculation", 1000, 800);
    }

    private void setData() {
        try {
            initTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initTable() throws SQLException {
        List<Production> productions = ProductionRepository.readAll();

        productionTable = createTable(new ProductionTableModel(productions));
        productionScrollPane.getViewport().add(productionTable);

        predictionTable = createTable(new PredictionTableModel(productions));
        predictionScrollPane.getViewport().add(predictionTable);

        Prediction prediction = PredictionRepository.read();

        assert prediction != null;

        totalTable.setModel(new TotalTableModel(prediction));
        totalTable.setAutoCreateRowSorter(true);

        regressionTable.setModel(new RegressionTableModel(prediction));
        regressionTable.setAutoCreateRowSorter(true);
    }

    private void setStyle() {
        setLabelPadding(titleLabel);

        productionScrollPane.setViewportBorder(null);

        setTableStyle(productionTable);
        setTableStyle(predictionTable);
        setCustomTableStyle(regressionTable);
        setCustomTableStyle(totalTable);
    }

    private void setCustomTableStyle(JTable table) {
        table.setTableHeader(null);
        table.setRowHeight(30);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(RED);
        renderer.setForeground(Color.WHITE);

        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
    }

    private void setActionListener() {
        backButton.addActionListener(e -> {
            backToMain();
        });
    }
}
