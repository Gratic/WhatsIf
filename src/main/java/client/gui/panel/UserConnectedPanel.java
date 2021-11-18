package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class UserConnectedPanel extends GuiPanel {

    private ConversationsPanel conversationsPanel;
    private UserPanel userPanel;
    private Controller controller;


    public UserConnectedPanel(Gui gui, Controller c) {
        super(gui);
        this.controller = c;
        setOpaque(true);
        setPreferredSize(new Dimension(200,80));
        setBackground(new Color(0xDCF1FF));
        setLayout(new BorderLayout());
        conversationsPanel = new ConversationsPanel(gui);
        userPanel = new UserPanel(gui, controller);
        this.add(conversationsPanel, BorderLayout.WEST);
        this.add(userPanel, BorderLayout.NORTH);



    }
}
