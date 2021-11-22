package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.OpenedConversationPanel;
import client.gui.panel.ReceivedMessagePanel;
import client.gui.panel.UserConnectedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConversationOpenedViewState extends ViewState implements ActionListener {

    private final UserConnectedPanel userConnectedPanel;
    private final Controller controller;
    private OpenedConversationPanel openedConversationPanel;
    private JButton sendMessageButton;
    private JTextArea messageArea;
    private int previousNumberOfMessages = 0;
   // private final File file;
    private BufferedReader reader;


    public ConversationOpenedViewState(Gui gui, Controller c) {
        super(gui);
        this.controller = c;

        //file = new File("conversations/" + c.getCurrentUser().getUsername() + "_" + c.getUsernameOtherUser());


        //System.out.println("j'ai changé d'état");
        gui.getMainPanel().removeAll();
        //gui.getFrame().removeAll();
        userConnectedPanel = new UserConnectedPanel(gui, controller);
        createGuiComponents();
        /*
        try {
            if (file.length() != 0) {
                reader = new BufferedReader(new FileReader(file));
                String message = reader.readLine();
                while (message != null) {
                    addMessagePanel(message);
                    message = reader.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        gui.getMainPanel().add(userConnectedPanel, BorderLayout.CENTER);

        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
        openedConversationPanel = new OpenedConversationPanel(gui, controller);
        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(this);
        openedConversationPanel.getSendingMessagePanel().add(sendMessageButton, BorderLayout.EAST);
        messageArea = new JTextArea();
        messageArea.setPreferredSize(new Dimension(600, 100));
        messageArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JLabel otherUser = new JLabel("Conversation with " );
        openedConversationPanel.getInfosConvPanel().add(otherUser, BorderLayout.CENTER);
        openedConversationPanel.getInfosConvPanel().add(otherUser);
        openedConversationPanel.revalidate();
        openedConversationPanel.revalidate();
        openedConversationPanel.getSendingMessagePanel().add(messageArea, BorderLayout.CENTER);
        openedConversationPanel.revalidate();
        openedConversationPanel.repaint();
        userConnectedPanel.add(openedConversationPanel, BorderLayout.CENTER);
        userConnectedPanel.revalidate();
        userConnectedPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendMessageButton) {
            this.gui.getController().sendingButtonClick(this.gui, messageArea.getText());
        }
    }

    public void receiveMessage(String message) {
        addMessagePanel(message);
    }

    public void addMessagePanel(String message) {
        ReceivedMessagePanel received = new ReceivedMessagePanel(gui);
        JLabel messageLabel = new JLabel(message);
        received.add(messageLabel);
        openedConversationPanel.getScroll().getMessagesContainerPanel().add(received);
        openedConversationPanel.getScroll().getMessagesContainerPanel().revalidate();
        openedConversationPanel.getScroll().getMessagesContainerPanel().repaint();
        openedConversationPanel.getScroll().revalidate();
        openedConversationPanel.getScroll().repaint();
        openedConversationPanel.revalidate();
        openedConversationPanel.repaint();
    }

}
