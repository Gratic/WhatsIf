package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class ConversationsPanel extends GuiPanel {

    public ConversationsPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(350, 200));
        setBackground(new Color(0xDDF3EF));
        // setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "Your conversations"
        );
        this.add(welcomeLabel);

    }
}
