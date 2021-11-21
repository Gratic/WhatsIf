package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class ReceivedMessagePanel extends MessagePanel {

    public ReceivedMessagePanel(Gui gui) {
        super(gui);
        setBackground(new Color(0x77A7C9));
        JLabel person = new JLabel("Them : ");
        this.add(person);
    }
}
