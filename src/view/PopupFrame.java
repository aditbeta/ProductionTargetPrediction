package view;

import javax.swing.*;

public class PopupFrame extends BaseFrame {

    private JPanel popupPanel;
    private JLabel message;
    private JButton closeButton;

    public PopupFrame(String message) {
        this.message.setText(message);
        setLabelPadding(this.message);
        setPanel(popupPanel, message, message.length()*10, 120);

        closeButton.addActionListener(e -> {
            backToMain();
        });
    }
}
