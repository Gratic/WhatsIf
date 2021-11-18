package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class SentMessagePanel extends MessagePanel {

    public SentMessagePanel(Gui gui)
    {
        super(gui);
        setBackground(new Color(0x3D6581));
        JLabel person = new JLabel("You : ");
        this.add(person);
    }

}
