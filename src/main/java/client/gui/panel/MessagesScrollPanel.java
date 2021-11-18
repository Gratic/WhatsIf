package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class MessagesScrollPanel extends JScrollPane {

    public MessagesScrollPanel(Gui gui) {
        super();
        setOpaque(true);
        this.getViewport().setBackground(new Color(0xC9E4E7));
        setPreferredSize(
                new Dimension(275, 700)
        );
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        MessagesContainerPanel messagesContainerPanel = new MessagesContainerPanel(gui);
        this.getViewport().add(messagesContainerPanel);
        repaint();
    }
}
