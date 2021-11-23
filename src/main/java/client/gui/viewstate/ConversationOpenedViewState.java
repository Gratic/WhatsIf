package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.IndividualConversationPanel;
import client.gui.panel.OpenedConversationPanel;
import client.gui.panel.ReceivedMessagePanel;
import client.gui.panel.UserConnectedPanel;
import common.model.Conversation;
import common.model.Message;
import common.model.TextMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;

public class ConversationOpenedViewState extends ViewState implements ActionListener {

    private final UserConnectedPanel userConnectedPanel;
    private final Controller controller;
    private OpenedConversationPanel openedConversationPanel;
    private JButton sendMessageButton;
    private JTextArea messageArea;
    private Timer timer;
    private JButton quitConvButton;
    private JTextField usernameToAdd;
    private JButton add;


    public ConversationOpenedViewState(Gui gui, Controller c) {
        super(gui);
        this.controller = c;
        gui.getMainPanel().removeAll();
        userConnectedPanel = new UserConnectedPanel(gui, controller);
        createGuiComponents();
        loadPreviousMessages();

        timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                controller.updateConversationsTimer();
                showConversations();
            }

        });
        timer.start();

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
        JLabel otherUser = new JLabel("Conversation with "+controller.getConversationsNameOfUser().get(controller.currentConnection.getCurrentConversation().getId()));

        openedConversationPanel.getInfosConvPanel().add(otherUser, BorderLayout.SOUTH);

        quitConvButton = new JButton("Leave definitly the conversation");
        quitConvButton.addActionListener(this);
        quitConvButton.setBounds(10,30,50,50);
        quitConvButton.setBackground(new Color(0xEF8282));
        openedConversationPanel.getInfosConvPanel().add(quitConvButton, BorderLayout.NORTH);

        JPanel addUser = new JPanel();
        addUser.setPreferredSize(new Dimension(350,30));
        addUser.setBackground(new Color(0xC9E4E7));
        addUser.setLayout(new BorderLayout());

        usernameToAdd = new JTextField();
        add = new JButton("Add user to the conv");
        addUser.add(usernameToAdd, BorderLayout.CENTER);
        addUser.add(add, BorderLayout.EAST);
        add.addActionListener(this);



        openedConversationPanel.getInfosConvPanel().add(addUser,BorderLayout.CENTER);

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
            messageArea.selectAll();
            messageArea.replaceSelection("");
        }else if (e.getSource()== quitConvButton)
        {
            this.gui.getController().quitDefinitlyConv(this.gui);
        }else if (e.getSource() == add)
        {
            this.gui.getController().addUserButtonClick(this.gui, usernameToAdd.getText());
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

    public void showConversations() {
        Map<Long, Conversation> conversationMap = controller.getConversationsOfUser();
        Map<Long, String> conversationNamesMap = controller.getConversationsNameOfUser();
        userConnectedPanel.getConversationsPanel().removeAll();
        for (Long conversationId : conversationMap.keySet()) {
            IndividualConversationPanel conversationPanel = new IndividualConversationPanel(gui, controller);
            Conversation conversation = conversationMap.get(conversationId);
            conversationPanel.setConversation(conversationMap.get(conversationId));
            int nbMessages = conversation.getMessages().size();
            String text;
            String sender;
            if (nbMessages != 0) {
                text = conversation.getMessages().get(conversation.getMessages().size() - 1).getValue();
                sender = conversation.getMessages().get(conversation.getMessages().size() - 1).getSender()+" : ";

            } else {
                text = "No message in the conversation";
                sender = "";
            }
            JLabel usernames = new JLabel(conversationNamesMap.get(conversationId));
            conversationPanel.add(usernames);
            JLabel message = new JLabel(text);
            JLabel senderUsername = new JLabel(sender );
            conversationPanel.add(usernames);
            conversationPanel.add(senderUsername);
            conversationPanel.add(message);
            conversationPanel.revalidate();
            conversationPanel.repaint();
            userConnectedPanel.getConversationsPanel().add(conversationPanel);
            userConnectedPanel.revalidate();
            userConnectedPanel.repaint();
        }
    }

    public void loadPreviousMessages()
    {

        List <Message> messages= controller.getCurrentConnection().getCurrentConversation().getMessages();
        for(Message m : messages){
            String sender = m.getSender();
            Long timestamp = m.getTimestamp();
            String value = m.getValue();
            LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("k'h'm");
            String date = localDateTime.format(formatter);
            String receivedMessageContent = (sender + ":" + date + ": " + value);
            addMessagePanel(receivedMessageContent);
        }

    }

}
