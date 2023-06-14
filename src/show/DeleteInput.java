package show;

import entity.Production;
import repository.ProductionRepository;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteInput extends JFrame {
    private JPanel deletePanel;
    private JLabel monthDeleteLabel;
    private JTextField monthDeleteField;
    private JButton monthDeleteButton;
    private JComboBox monthDropdown;

    private List<Production> productions;

    public DeleteInput(List<Production> productionsList) {
        productions = productionsList;

        setContentPane(deletePanel);
        setTitle("Delete Production Input by Month");
        setSize(300,100);
        setLocationRelativeTo(null);
        setVisible(true);

        setDropdownValue();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    new MainFrame();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        monthDeleteButton.addActionListener(e -> {
            try {
                ProductionRepository.delete(monthDropdown.getSelectedItem().toString());
                dispose();
                new MainFrame();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void setDropdownValue() {
        List<String> monthsOption = new ArrayList<>();
        productions.forEach(data -> monthsOption.add(data.getMonth()));
        monthDropdown.setModel(new DefaultComboBoxModel<>(monthsOption.toArray(new String[0])));
    }
}
