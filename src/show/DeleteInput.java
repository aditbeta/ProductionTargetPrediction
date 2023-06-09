package show;

import repository.ProductionRepository;

import javax.swing.*;
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

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
