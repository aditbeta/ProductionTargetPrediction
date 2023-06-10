package show;

import repository.ProductionRepository;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DeleteInput extends JFrame {
    private JPanel deletePanel;
    private JLabel monthDeleteLabel;
    private JTextField monthDeleteField;
    private JButton monthDeleteButton;

    public DeleteInput() {
        setContentPane(deletePanel);
        setTitle("Delete Production Input by Month");
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

        monthDeleteButton.addActionListener(e -> {
            try {
                ProductionRepository.delete(monthDeleteField.getText());
                dispose();
                new MainFrame();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
