package show;

import javax.swing.*;
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

    public void backToMain() {
        dispose();
        try {
            new MainFrame();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
