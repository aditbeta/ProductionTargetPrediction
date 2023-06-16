package view;

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

public class InputFrame extends BaseFrame {

    private JPanel productionInputPanel;
    private JLabel titleLabel;
    private JTextField sellField;
    private JTextField orderField;
    private JTextField targetField;
    private JComboBox monthField;
    private JButton submitButton;
    private JButton backButton;

    private List<Production> productions;

    public InputFrame(List<Production> productionList) {
        setActionListener();
        setData(productionList);
        setStyle();
        setPanel(productionInputPanel, "Production Data Form", 350, 400);
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

    private void setStyle() {
        LineBorder border = new LineBorder(Color.black, 1, false);

        sellField.setBorder(BorderFactory.createTitledBorder(new TitledBorder(border, "Actual Production")));
        orderField.setBorder(BorderFactory.createTitledBorder(new TitledBorder(border, "Actual Sales")));
        targetField.setBorder(BorderFactory.createTitledBorder(new TitledBorder(border, "Planning Production")));
    }

    private void setActionListener() {
        submitButton.addActionListener(e -> {
            String month = Objects.requireNonNull(monthField.getSelectedItem()).toString();
            String sell = sellField.getText();
            String order = orderField.getText();
            String target = targetField.getText();
            try {
                try {
                    ProductionRepository.insert(new ProductionObject(
                            month, Double.parseDouble(sell), Double.parseDouble(order), Double.parseDouble(target)));
                } catch (SQLException se) {
                    throw new RuntimeException(se);
                }
            } catch (NumberFormatException ne) {
                dispose();
                popupMessage("Invalid input. All input should be numeric.");
                return;
            }

            backToMain();
        });
        backButton.addActionListener(e -> {
            backToMain();
        });
    }
}
