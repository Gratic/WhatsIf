package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class UserConnectedPanel extends GuiPanel {

    private ConversationsPanel conversationsPanel;
    private UserPanel userPanel;


    public UserConnectedPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(200,80));
        setBackground(new Color(0x239AC5));
        setLayout(new BorderLayout());
        conversationsPanel = new ConversationsPanel(gui);
        userPanel = new UserPanel(gui);
        this.add(conversationsPanel, BorderLayout.WEST);
        this.add(userPanel, BorderLayout.NORTH);



    }
}
