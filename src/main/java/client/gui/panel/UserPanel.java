package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends GuiPanel{

    public UserPanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(200,80));
        setBackground(new Color(0x239AC5));
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "Hello user"
        );
        this.add(welcomeLabel,BorderLayout.EAST);

    }
}
