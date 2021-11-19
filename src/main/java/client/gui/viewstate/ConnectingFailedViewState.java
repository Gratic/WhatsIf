package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.OpenedConversationPanel;
import client.gui.panel.UserConnectedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectingFailedViewState extends ViewState implements ActionListener {

    private JButton retry;
    private JButton quit;
    public ConnectingFailedViewState(Gui gui) {
        super(gui);
        gui.getMainPanel().removeAll();
        createGuiComponents();
        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
        retry = new JButton("Retry");
        retry.addActionListener(this);
        gui.getMainPanel().add(retry, BorderLayout.WEST);
        quit = new JButton ("Quit");
        quit.addActionListener(this);
        gui.getMainPanel().add(quit, BorderLayout.EAST);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==retry){
            this.gui.getController().retryConnectingButtonClick(this.gui);
        }else if(e.getSource()==quit){
            this.gui.getController().quitConnectingButtonClick(this.gui);
        }
    }
}
