package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class SendingMessagePanel extends GuiPanel {



    public SendingMessagePanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(350, 200));
        setBackground(new Color(0xDDEFED));
        setLayout(new BorderLayout());


    }


}
