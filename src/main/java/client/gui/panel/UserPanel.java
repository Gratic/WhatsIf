package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends GuiPanel {

    private final Controller controller;

    public UserPanel(Gui gui, Controller controller) {
        super(gui);
        this.controller = controller;
        setOpaque(true);
        setPreferredSize(new Dimension(200, 80));
        setBackground(new Color(0x87CCEA));
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "Hello " + controller.getCurrentUser().getUsername() + " ! "
        );
        this.add(welcomeLabel, BorderLayout.EAST);

    }
}
