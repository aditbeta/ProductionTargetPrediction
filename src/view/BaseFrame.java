package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;

public class BaseFrame extends JFrame {

    public final Color BLACK = new Color(30, 30, 40);
    public final Color BLUE = new Color(32, 136, 203);
    public final Color LIGHT_GRAY = new Color(220, 220, 220);
    public final Color RED = new Color(232, 57, 95);
    public final Color WHITE = new Color(248, 248, 248);

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
        table.getTableHeader().setBackground(BLUE);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(30);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
    }

    public void setLabelPadding(JLabel label) {
        label.setBorder(new EmptyBorder(0,0,20,0));
    }

    public void backToMain() {
        dispose();
        try {
            new MainFrame();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void popupMessage(String message) {
        dispose();
        new PopupFrame(message);
    }

    public JTable createTable(TableModel model) {
        return new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (isRowSelected(row)) {
                    c.setBackground(BLACK);
                    c.setForeground(WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? LIGHT_GRAY : WHITE);
                    c.setForeground(BLACK);
                }
                return c;
            }
        };
    }
}
