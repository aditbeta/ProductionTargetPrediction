package show;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Popup extends BaseFrame {

    private JPanel popupPanel;
    private JLabel message;
    private JButton closeButton;

    public Popup(String message) {
        this.message.setText(message);
        setLabelPadding(this.message);
        setPanel(popupPanel, message, message.length()*10, 120);

        closeButton.addActionListener(e -> {
            backToMain();
        });
    }
}
