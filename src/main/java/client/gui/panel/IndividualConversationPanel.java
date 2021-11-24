package client.gui.panel;

import client.controller.Controller;
import client.gui.Gui;
import common.model.Conversation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IndividualConversationPanel extends GuiPanel implements MouseListener {

    private Conversation conversation;
    private final Controller controller;

    public IndividualConversationPanel(Gui gui, Controller controller) {
        super(gui);

        this.controller = controller;
        setOpaque(true);
        setPreferredSize(new Dimension(400, 80));
        setBackground(new Color(0x96BAC4));
        addMouseListener(this);
        setLayout(new FlowLayout());
    }

    /**
     * Change the focused conversation when a conversation panel is clicked
     * @param e action event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        controller.changeFocusConversation(conversation);


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
