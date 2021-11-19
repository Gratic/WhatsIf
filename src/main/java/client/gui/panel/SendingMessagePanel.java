package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendingMessagePanel extends GuiPanel implements ActionListener {

    JButton sendMessageButton;
    JTextArea messageArea;
    public SendingMessagePanel(Gui gui) {
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(350,200));
        setBackground(new Color(0xDDEFED));
        setLayout(new BorderLayout());


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("clické "+messageArea.getText());
        this.gui.getController().sendingButtonClick(this.gui,messageArea.getText());

    }
}
