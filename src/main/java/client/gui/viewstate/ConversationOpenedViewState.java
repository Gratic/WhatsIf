package client.gui.viewstate;

import client.gui.Gui;
import client.gui.panel.OpenedConversationPanel;
import client.gui.panel.UserConnectedPanel;

import java.awt.*;

public class ConversationOpenedViewState extends ViewState{

    private UserConnectedPanel userConnectedPanel;

    public ConversationOpenedViewState(Gui gui) {
        super(gui);
        //System.out.println("j'ai changé d'état");
        gui.getMainPanel().removeAll();
        //gui.getFrame().removeAll();
        userConnectedPanel = new UserConnectedPanel(gui);
        createGuiComponents();
        gui.getMainPanel().add(userConnectedPanel, BorderLayout.CENTER);

        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
        OpenedConversationPanel openedConversationPanel = new OpenedConversationPanel(gui);
        userConnectedPanel.add(openedConversationPanel, BorderLayout.CENTER);
        userConnectedPanel.revalidate();
        userConnectedPanel.repaint();




    }
}
