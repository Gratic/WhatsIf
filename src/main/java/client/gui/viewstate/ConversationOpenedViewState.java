package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.OpenedConversationPanel;
import client.gui.panel.ReceivedMessagePanel;
import client.gui.panel.SendingMessagePanel;
import client.gui.panel.UserConnectedPanel;
import jdk.jshell.Snippet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversationOpenedViewState extends ViewState implements ActionListener {

    private UserConnectedPanel userConnectedPanel;
    private Controller controller;
    private OpenedConversationPanel openedConversationPanel;
    private JButton sendMessageButton;
    private JTextArea messageArea;
    private JButton receiveMessageButton;
    private int previousNumberOfMessages = 0;

    public ConversationOpenedViewState(Gui gui, Controller c) {
        super(gui);
        this.controller = c;

        //System.out.println("j'ai changé d'état");
        gui.getMainPanel().removeAll();
        //gui.getFrame().removeAll();
        userConnectedPanel = new UserConnectedPanel(gui, controller);
        createGuiComponents();
        gui.getMainPanel().add(userConnectedPanel, BorderLayout.CENTER);

        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();
    }

    protected void createGuiComponents() {
        openedConversationPanel = new OpenedConversationPanel(gui, controller);
        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(this);
        receiveMessageButton = new JButton(("Receive"));
        receiveMessageButton.addActionListener(this);
        openedConversationPanel.getSendingMessagePanel().add(sendMessageButton, BorderLayout.EAST);
        openedConversationPanel.getSendingMessagePanel().add(receiveMessageButton, BorderLayout.WEST);
        messageArea = new JTextArea();
        messageArea.setPreferredSize(new Dimension(600,100));
        messageArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        openedConversationPanel.getSendingMessagePanel().add(messageArea, BorderLayout.CENTER);
        openedConversationPanel.revalidate();
        openedConversationPanel.repaint();
        userConnectedPanel.add(openedConversationPanel, BorderLayout.CENTER);
        userConnectedPanel.revalidate();
        userConnectedPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==sendMessageButton)
        {
            System.out.println("clickéeeeee "+messageArea.getText());
            this.gui.getController().sendingButtonClick(this.gui,messageArea.getText());
            /*SendingMessagePanel send = new SendingMessagePanel(gui);
            JLabel message = new JLabel(messageArea.getText());
            send.add(message);
            openedConversationPanel.getScroll().getMessagesContainerPanel().add(send);
            openedConversationPanel.getScroll().getMessagesContainerPanel().revalidate();
            openedConversationPanel.getScroll().getMessagesContainerPanel().repaint();
            openedConversationPanel.getScroll().revalidate();
            openedConversationPanel.getScroll().repaint();
            openedConversationPanel.revalidate();
            openedConversationPanel.repaint();*/
        }if (e.getSource()==receiveMessageButton){
            this.controller.receivingButtonClick(this.gui);
            if(this.controller.getMessagesReceived().size()!=0 && this.controller.getMessagesReceived().size()!=previousNumberOfMessages){
                for(String s : this.controller.getMessagesReceived())
                {
                    System.out.println(s);
                }
                previousNumberOfMessages=this.controller.getMessagesReceived().size();
                String message = this.controller.getMessagesReceived().get(this.controller.getMessagesReceived().size()-1);
                ReceivedMessagePanel received = new ReceivedMessagePanel(gui);
                System.out.println(message);
                JLabel messageLabel = new JLabel(message);
                received.add(messageLabel);
                openedConversationPanel.getScroll().getMessagesContainerPanel().add(received);
                openedConversationPanel.getScroll().getMessagesContainerPanel().revalidate();
                openedConversationPanel.getScroll().getMessagesContainerPanel().repaint();
                openedConversationPanel.getScroll().revalidate();
                openedConversationPanel.getScroll().repaint();
                openedConversationPanel.revalidate();
                openedConversationPanel.repaint();
            }else
            {
                System.out.println("No new message found");
            }

        }



    }
}
