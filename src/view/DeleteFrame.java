package view;

import entity.Production;
import repository.ProductionRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteFrame extends BaseFrame {
    private JPanel deletePanel;
    private JLabel titleLabel;
    private JTextField monthDeleteField;
    private JButton deleteButton;
    private JComboBox monthDropdown;
    private JButton backButton;

    private List<Production> productions;

    public DeleteFrame(List<Production> productionList) {
        setActionListener();
        setData(productionList);
        setStyle();
        setPanel(deletePanel, "Delete Production Input by Month", 300, 150);
    }

    private void setData(List<Production> productionList) {
        productions = productionList;

        setDropdownValue();
    }

    private void setDropdownValue() {
        List<String> monthsOption = new ArrayList<>();
        productions.forEach(data -> monthsOption.add(data.getMonth()));
        monthDropdown.setModel(new DefaultComboBoxModel<>(monthsOption.toArray(new String[0])));
    }

    private void setStyle() {
        titleLabel.setBorder(new EmptyBorder(0,0,10,0));
    }

    private void setActionListener() {
        deleteButton.addActionListener(e -> {
            try {
                ProductionRepository.delete(monthDropdown.getSelectedItem().toString());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            backToMain();
        });
        backButton.addActionListener(e -> {
            backToMain();
        });
    }
}
