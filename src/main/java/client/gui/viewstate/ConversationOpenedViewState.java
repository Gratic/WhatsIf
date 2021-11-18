package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.OpenedConversationPanel;
import client.gui.panel.SendingMessagePanel;
import client.gui.panel.UserConnectedPanel;
import jdk.jshell.Snippet;

import java.awt.*;

public class ConversationOpenedViewState extends ViewState{

    private UserConnectedPanel userConnectedPanel;
    private Controller controller;

    public ConversationOpenedViewState(Gui gui, Controller c) {
        super(gui);
        this.controller = c;

        //System.out.println("j'ai changé d'état");
        gui.getMainPanel().removeAll();
        //gui.getFrame().removeAll();
        userConnectedPanel = new UserConnectedPanel(gui, controller);
        createGuiComponents();
        gui.getMainPanel().add(userConnectedPanel, BorderLayout.CENTER);

        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
        OpenedConversationPanel openedConversationPanel = new OpenedConversationPanel(gui, controller);
        userConnectedPanel.add(openedConversationPanel, BorderLayout.CENTER);
        userConnectedPanel.revalidate();
        userConnectedPanel.repaint();




    }
}
