package client.gui.viewstate;

import client.gui.Gui;
import client.gui.panel.InitPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitViewState extends ViewState implements ActionListener {

    private InitPanel initPanel;
    private JButton button;
    public InitViewState(Gui gui) {
        super(gui);
        initPanel = new InitPanel(gui);
        gui.getMainPanel().removeAll();
        createGuiComponents();
        gui.getMainPanel().add(initPanel, BorderLayout.CENTER);
        initPanel.revalidate();
        initPanel.repaint();
        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
