package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;


public abstract class GuiPanel extends JPanel {

    public Gui gui;

    public GuiPanel(Gui gui) {
        this.gui = gui;
    }
}

