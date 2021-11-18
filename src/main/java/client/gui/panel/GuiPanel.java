package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;


public abstract class GuiPanel extends JPanel {

    public Gui gui;

    public GuiPanel(Gui gui) {

        this.gui = gui;
        setBorder(BorderFactory.createLineBorder(Color.darkGray,2));
    }
}

