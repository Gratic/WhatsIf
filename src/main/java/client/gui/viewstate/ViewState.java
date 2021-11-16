package client.gui.viewstate;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public abstract class ViewState {
    protected Gui gui;
    protected JPanel panel;

    public ViewState(Gui gui) {
        this.gui = gui;
    }

    protected void createGuiComponents() {
        // base panel
        panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(new Color(192, 133, 133));
        panel.setLocation(50, 50);
        panel.setLayout(null);

        // inner JPanel to be centered
        JPanel innerPanel = new JPanel();
        FlowLayout verticalFlowLayout = new FlowLayout(
                FlowLayout.LEADING, 50, 15
        );
        innerPanel.setLayout(verticalFlowLayout);
        innerPanel.setPreferredSize(new Dimension(700, 400));
        innerPanel.setBackground(new Color(211, 5, 5));


        JLabel welcomeLabel = new JLabel(
                "You win"
        );
        welcomeLabel.setSize(600, 30);
        innerPanel.add(welcomeLabel);

        // main panel settings
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(700, 700));
        panel.add(innerPanel);
        panel.setVisible(true);

        panel.repaint();
    }
}
