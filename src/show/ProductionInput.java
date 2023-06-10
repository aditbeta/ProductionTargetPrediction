package show;

import repository.ProductionObject;
import repository.ProductionRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ProductionInput extends JFrame {
    private JLabel monthLabel;
    private JTextField monthField;
    private JLabel sellLabel;
    private JTextField sellField;
    private JLabel orderLabel;
    private JTextField orderField;
    private JTextField targetField;
    private JLabel targetLabel;
    private JButton submitButton;
    private JPanel productionInputPanel;

    public ProductionInput() {
        setContentPane(productionInputPanel);
        setTitle("Production Input Form");
        setSize(450,300);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    new MainFrame();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String month = monthField.getText();
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
            }
        });
    }
}
