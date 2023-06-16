package show;

import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionRepository;
import show.table.ProductionTableModel;

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

        if (productions != null && productions.size() > 0) {
            recalculate();
        }
        initTable();
    }

    private void recalculate() throws SQLException {
        Prediction prediction = new Prediction();
        prediction.calculateTotal(productions);
        prediction.calculatePrediction();

        if (PredictionRepository.read() != null) {
            PredictionRepository.update(prediction);
            return;
        }
        PredictionRepository.insert(prediction);
    }

    private void initTable() {
        if (productions != null && productions.size() > 0) {
            productionTable.setModel(new ProductionTableModel(productions));
            productionTable.setAutoCreateRowSorter(true);
        } else {
            productionTable.setModel(new ProductionTableModel(new ArrayList<>()));
        }

        setTableStyle(productionTable);
    }

    private void setStyle() {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("icon/logo.png")));
        titleLabel.setIcon(resizeIcon(logo, getWidth() - 250, 100));

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("icon/close.png")));
        closeButton.setIcon(resizeIcon(icon, closeButton.getWidth(), closeButton.getHeight()));
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void setActionListener() {
        closeButton.addActionListener(e -> System.exit(0));
        inputButton.addActionListener(e -> {
            new ProductionInput(productions);
            dispose();
        });
        calculateButton.addActionListener(e -> {
            try {
                new Regression();
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
                new PredictionResult();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
    }
}
