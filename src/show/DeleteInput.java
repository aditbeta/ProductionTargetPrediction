package show;

import entity.Production;
import repository.ProductionRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteInput extends JFrame {
    private JPanel deletePanel;
    private JLabel titleLabel;
    private JTextField monthDeleteField;
    private JButton deleteButton;
    private JComboBox monthDropdown;
    private JButton cancelButton;

    private List<Production> productions;

    public DeleteInput(List<Production> productionList) {
        setStyle();
        setActionListener();

        setData(productionList);

        setPanel();
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

    private void setPanel() {
        setUndecorated(true);
        setContentPane(deletePanel);
        setTitle("Delete Production Input by Month");
        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
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
