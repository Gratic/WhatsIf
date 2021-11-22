package client.gui.panel;

import client.gui.Gui;

import javax.swing.*;
import java.awt.*;

public class IndividualConversationPanel extends GuiPanel {

    public IndividualConversationPanel (Gui gui){
        super(gui);
        setOpaque(true);
        setPreferredSize(new Dimension(400, 80));
        setLayout(new FlowLayout());
    }
}
