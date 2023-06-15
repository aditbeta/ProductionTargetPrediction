package show;

import entity.Production;
import repository.ProductionObject;
import repository.ProductionRepository;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductionInput extends JFrame {

    private JPanel productionInputPanel;
    private JLabel titleLabel;
    private JTextField sellField;
    private JTextField orderField;
    private JTextField targetField;
    private JComboBox monthField;
    private JButton submitButton;
    private JButton cancelButton;

    private List<Production> productions;

    public ProductionInput(List<Production> productionList) {
        setStyle();
        setActionListener();

        setData(productionList);

        setPanel();
    }

    private void setData(List<Production> productionList) {
        productions = productionList;
        setDropdownMonth();
    }

    private void setDropdownMonth() {
        List<String> months = new ArrayList<>(List.of("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
        productions.forEach(data -> months.remove(data.getMonth()));
        monthField.setModel(new DefaultComboBoxModel<>(months.toArray(new String[0])));
    }

    private void setPanel() {
        setUndecorated(true);
        setContentPane(productionInputPanel);
        setTitle("Production Input Form");
        setSize(350,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setStyle() {
        LineBorder border = new LineBorder(Color.black, 1, false);

        sellField.setBorder(BorderFactory.createTitledBorder(new TitledBorder(border, "Actual Production")));
        orderField.setBorder(BorderFactory.createTitledBorder(new TitledBorder(border, "Actual Sales")));
        targetField.setBorder(BorderFactory.createTitledBorder(new TitledBorder(border, "Planning Production")));
    }

    private void setActionListener() {
        submitButton.addActionListener(e -> {
            String month = Objects.requireNonNull(monthField.getSelectedItem()).toString();
            Double sell = Double.parseDouble(sellField.getText());
            Double order = Double.parseDouble(orderField.getText());
            Double target = Double.parseDouble(targetField.getText());

            try {
                ProductionRepository.insert(new ProductionObject(month, sell, order, target));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            backToMain();
        });
        cancelButton.addActionListener(e -> {
            backToMain();
        });
    }

    private void backToMain() {
        dispose();
        try {
            new MainFrame();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
