package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;

import java.awt.*;

public class UserConnectedPanel extends GuiPanel {

    private final ConversationsPanel conversationsPanel;
    private final UserPanel userPanel;
    private final Controller controller;


    public UserConnectedPanel(Gui gui, Controller c) {
        super(gui);
        this.controller = c;
        setOpaque(true);
        setPreferredSize(new Dimension(200, 80));
        setBackground(new Color(0xDCF1FF));
        setLayout(new BorderLayout());
        conversationsPanel = new ConversationsPanel(gui);
        userPanel = new UserPanel(gui, controller);
        this.add(conversationsPanel, BorderLayout.WEST);
        this.add(userPanel, BorderLayout.NORTH);
    }

    public ConversationsPanel getConversationsPanel() {
        return conversationsPanel;
    }
}
