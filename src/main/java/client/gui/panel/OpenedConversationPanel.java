package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class OpenedConversationPanel extends GuiPanel {

    private SendingMessagePanel sendingMessagePanel;
    private InfosConvPanel infosConvPanel;
    Controller controller;
    public OpenedConversationPanel(Gui gui, Controller controller) {
        super(gui);
        this.controller = controller;
        setOpaque(true);
        setPreferredSize(new Dimension(350,200));
        setBackground(new Color(0xC9E4E7));
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "You opened a conversation"
        );
        this.add(welcomeLabel);
        sendingMessagePanel = new SendingMessagePanel(gui);
        this.add(sendingMessagePanel, BorderLayout.SOUTH);
        infosConvPanel = new InfosConvPanel(gui, controller);
        this.add(infosConvPanel, BorderLayout.NORTH);
        MessagesScrollPanel scroll = new MessagesScrollPanel(gui);
        this.add(scroll, BorderLayout.CENTER);


    }
}