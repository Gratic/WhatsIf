package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public abstract class MessagePanel extends GuiPanel{

    public MessagePanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(400,80));
        setLayout(new BorderLayout());


    }
}
