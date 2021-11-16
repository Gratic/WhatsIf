package client.gui.viewstate;

import client.gui.Gui;
import client.gui.panel.InitPanel;

import java.awt.*;

public class UserConnectedViewState extends ViewState{

    private InitPanel initPanel;
    public UserConnectedViewState(Gui gui) {
        super(gui);
        gui.getMainPanel().removeAll();
        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
    }
}
