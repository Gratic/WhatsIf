package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.AskConversationPanel;
import client.gui.panel.UserConnectedPanel;

import java.awt.*;

public class UserConnectedViewState extends ViewState {


    private final UserConnectedPanel userConnectedPanel;
    private AskConversationPanel askConversationPanel;
    private final Controller controller;

    public UserConnectedViewState(Gui gui, Controller controller) {
        super(gui);
        this.controller = controller;
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
        askConversationPanel = new AskConversationPanel(gui);
        userConnectedPanel.add(askConversationPanel, BorderLayout.CENTER);
        userConnectedPanel.revalidate();
        userConnectedPanel.repaint();

    }
}
