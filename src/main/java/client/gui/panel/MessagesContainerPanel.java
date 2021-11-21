package client.gui.panel;

import client.gui.Gui;

import java.awt.*;

public class MessagesContainerPanel extends GuiPanel {


    public MessagesContainerPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(200, 80));
        setBackground(new Color(0xC9E4E7));
        setLayout(new FlowLayout());
        setBorder(null);
    }

}
