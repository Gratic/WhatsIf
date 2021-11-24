package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends GuiPanel implements ActionListener {

    private final Controller controller;
    private final JButton disconnect;

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

        disconnect = new JButton("disconnect");
        disconnect.addActionListener(this);
        this.add(disconnect, BorderLayout.WEST);

    }

    /**
     * Disconnect the user when the button is clicked
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == disconnect) {
            this.controller.disconnectButtonClick(this.gui);
        }

    }
}
