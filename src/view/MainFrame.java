package view;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import view.table.ProductionTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFrame extends BaseFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton closeButton;
    private JButton inputButton;
    private JButton calculateButton;
    private JButton resultButton;
    private JScrollPane productionScrollPane;
    private JTable productionTable;
    private JButton deleteButton;

    private List<Production> productions;

    public MainFrame() throws SQLException {
        setActionListener();
        setData();
        setPanel(mainPanel, "Main", 700, 500);
        setStyle();
    }

    private void setData() throws SQLException {
        productions = ProductionRepository.readAll();

        if (!emptyProductions()) {
            recalculate();
            initTable();
        } else {
            productionTable.setModel(new ProductionTableModel(new ArrayList<>()));
        }
    }

    private void recalculate() throws SQLException {
        Prediction prediction = new Prediction();
        prediction.setTotal(productions);
        prediction.setPrediction(productions);

        if (PredictionRepository.read() != null) {
            PredictionRepository.update(prediction);
            return;
        }
        PredictionRepository.insert(prediction);
    }

    private void initTable() {
        productionTable = createTable(new ProductionTableModel(productions));
        productionScrollPane.getViewport().add(productionTable);
    }

    private void setStyle() {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("icon/logo.png")));
        titleLabel.setIcon(resizeIcon(logo, getWidth() - 250, 105));

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("icon/close.png")));
        closeButton.setIcon(resizeIcon(icon, closeButton.getWidth(), closeButton.getHeight()));
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);

        setTableStyle(productionTable);
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }

    private void setActionListener() {
        closeButton.addActionListener(e -> System.exit(0));
        inputButton.addActionListener(e -> {
            if (productions.size() < 12) {
                new InputFrame(productions);
                dispose();
            } else {
                popupMessage("Production data for all available months has been inputted.");
            }
        });
        calculateButton.addActionListener(e -> {
            if (valid()) {
                new RegressionFrame();
                dispose();
            }
        });
        resultButton.addActionListener(e -> {
            if (valid()) {
                new PredictionFrame();
                dispose();
            }
        });
        deleteButton.addActionListener(e -> {
            if (valid()) {
                new DeleteFrame(productions);
                dispose();
            }
        });
    }

    private boolean valid() {
        if (emptyProductions()) {
            popupMessage("Please input production data first.");

            return false;
        }

        return true;
    }

    private boolean emptyProductions() {
        return productions == null || productions.size() == 0;
    }
}
