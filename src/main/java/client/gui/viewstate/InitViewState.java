package client.gui.viewstate;

import client.gui.Gui;
import client.gui.panel.InitPanel;

import java.awt.*;

public class InitViewState extends ViewState {

    private final InitPanel initPanel;

    public InitViewState(Gui gui) {
        super(gui);
        initPanel = new InitPanel(gui);
        createGuiComponents();
        gui.getMainPanel().add(initPanel, BorderLayout.CENTER);
        initPanel.revalidate();
        initPanel.repaint();
        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
    }
}
