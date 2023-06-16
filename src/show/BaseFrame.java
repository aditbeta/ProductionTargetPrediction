package show;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BaseFrame extends JFrame {

    public void setPanel(JPanel panel, String title, int width, int height) {
        setUndecorated(true);
        setContentPane(panel);
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setTableStyle(JTable table) {
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        table.setRowHeight(30);
    }

    public void backToMain() {
        dispose();
        try {
            new MainFrame();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
