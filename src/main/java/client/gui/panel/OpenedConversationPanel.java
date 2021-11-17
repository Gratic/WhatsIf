package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class OpenedConversationPanel extends GuiPanel {

    public OpenedConversationPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(350,200));
        setBackground(new Color(0xA9BEBE));
        // setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "You opened a conversation"
        );
        this.add(welcomeLabel);

    }
}
