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
    private JButton inputButton;
    private JButton calculateButton;
    private JButton resultButton;
    private JPanel mainPanel;
    private JTable productionTable;
    private JButton deleteButton;
    private JButton xButton;
    private JLabel titleLabel;

    private List<Production> productions;

    public MainFrame() throws SQLException {
        setUndecorated(true);
        setContentPane(mainPanel);
        setTitle("Production Input Form");
        setSize(700,500);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("icon/logo.png")));
        titleLabel.setIcon(resizeIcon(logo, getWidth() - 250, 100));
        buttonListener();

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("icon/close.png")));
        xButton.setIcon(resizeIcon(icon, xButton.getWidth(), xButton.getHeight()));
        xButton.setOpaque(false);
        xButton.setContentAreaFilled(false);
        xButton.setBorderPainted(false);

        productions = ProductionRepository.readAll();

        if (productions != null && productions.size() > 0) {
            recalculate();
        }
        initTable();
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
        if (productions != null && productions.size() > 0) {
            productionTable.setModel(new ProductionTableModel(productions));
            productionTable.setAutoCreateRowSorter(true);
        } else {
            productionTable.setModel(new ProductionTableModel(new ArrayList<>()));
        }

        setTableStyle(productionTable);
    }

    public void buttonListener() {
        xButton.addActionListener(e -> {
            dispose();
        });
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

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
