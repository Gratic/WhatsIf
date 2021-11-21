package client.gui.panel;

import client.gui.Gui;

import java.awt.*;

public abstract class MessagePanel extends GuiPanel {

    public MessagePanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(400, 80));
        setLayout(new BorderLayout());


    }
}
