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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

public class Regression extends BaseFrame {

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

    public Regression() throws SQLException {
        setActionListener();
        setData();
        setStyle();
        setPanel(regressionPanel, "Regression Calculation", 1000, 800);
    }

    private void setData() throws SQLException {
        initTable();
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
        renderer.setBackground(new Color(232, 57, 95));
        renderer.setForeground(new Color(255, 255, 255));

        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
    }

    private void setActionListener() {
        backButton.addActionListener(e -> {
            backToMain();
        });
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
