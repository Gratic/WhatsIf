package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.*;
import common.model.User;

import javax.swing.*;
import java.awt.*;

public class UserConnectedViewState extends ViewState{


    private UserConnectedPanel userConnectedPanel;
    private AskConversationPanel askConversationPanel;

    public UserConnectedViewState(Gui gui) {
        super(gui);
        gui.getMainPanel().removeAll();
        //gui.getFrame().removeAll();
        userConnectedPanel = new UserConnectedPanel(gui);
        createGuiComponents();
        gui.getMainPanel().add(userConnectedPanel, BorderLayout.CENTER);

        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
        askConversationPanel = new AskConversationPanel(gui);
        userConnectedPanel.add(askConversationPanel, BorderLayout.CENTER);
        userConnectedPanel.revalidate();
        userConnectedPanel.repaint();

    }
}
