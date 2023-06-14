package show;

import entity.Production;
import repository.ProductionObject;
import repository.ProductionRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionInput extends JFrame {
    private JLabel monthLabel;
    private JComboBox monthField;
    private JLabel sellLabel;
    private JTextField sellField;
    private JLabel orderLabel;
    private JTextField orderField;
    private JTextField targetField;
    private JLabel targetLabel;
    private JButton submitButton;
    private JPanel productionInputPanel;

    private List<Production> productions;

    public ProductionInput(List<Production> productionList) {
        productions = productionList;

        setContentPane(productionInputPanel);
        setTitle("Production Input Form");
        setSize(250,175);
        setLocationRelativeTo(null);
        setVisible(true);

        setDropdownMonth();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    new MainFrame();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        submitButton.addActionListener(e -> {
            String month = monthField.getSelectedItem().toString();
            Double sell = Double.parseDouble(sellField.getText());
            Double order = Double.parseDouble(orderField.getText());
            Double target = Double.parseDouble(targetField.getText());

            ProductionObject production = new ProductionObject(month, sell, order, target);

            try {
                ProductionRepository.insert(production);
                dispose();
                new MainFrame();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void setDropdownMonth() {
        List<String> months = new ArrayList<>(List.of("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
//        List<String> exists = new ArrayList<>();
        productions.forEach(data -> months.remove(data.getMonth()));
        monthField.setModel(new DefaultComboBoxModel<>(months.toArray(new String[0])));
    }
}
