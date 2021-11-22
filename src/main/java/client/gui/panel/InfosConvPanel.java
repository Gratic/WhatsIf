package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfosConvPanel extends GuiPanel implements ActionListener {
    private final JButton quitConvButton;
    private final Controller controller;

    public InfosConvPanel(Gui gui, Controller controller) {
        super(gui);
        this.controller = controller;
        setOpaque(true);
        setPreferredSize(new Dimension(350, 20));
        setBackground(new Color(0xC9E4E7));
        setLayout(new BorderLayout());
        setBorder(null);


        quitConvButton = new JButton("Leave the conversation");
        quitConvButton.setBackground(Color.red);
        quitConvButton.setForeground(Color.white);
        quitConvButton.setSize(10, 10);
        quitConvButton.addActionListener(this);
        this.add(quitConvButton, BorderLayout.EAST);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitConvButton) {
            System.out.println("quit conv");


        }
    }
}
