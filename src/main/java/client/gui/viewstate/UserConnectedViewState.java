package client.gui.viewstate;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.panel.AskConversationPanel;
import client.gui.panel.IndividualConversationPanel;
import client.gui.panel.UserConnectedPanel;
import common.model.Conversation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class UserConnectedViewState extends ViewState{


    private final UserConnectedPanel userConnectedPanel;
    private AskConversationPanel askConversationPanel;
    private final Controller controller;
    private Timer timer;

    public UserConnectedViewState(Gui gui, Controller controller) {
        super(gui);
        this.controller = controller;
        gui.getMainPanel().removeAll();
        userConnectedPanel = new UserConnectedPanel(gui, controller);
        createGuiComponents();

        timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                controller.updateConversationsTimer();

            }
        });
        timer.start();



        controller.updateConversationsTimer();

        gui.getMainPanel().add(userConnectedPanel, BorderLayout.CENTER);
        gui.getMainPanel().revalidate();
        gui.getMainPanel().repaint();


    }

    protected void createGuiComponents() {
        askConversationPanel = new AskConversationPanel(gui);
        userConnectedPanel.add(askConversationPanel, BorderLayout.CENTER);
        userConnectedPanel.revalidate();
        userConnectedPanel.repaint();

    }

    public void stopTimer()
    {
        if(timer!=null){
            timer.stop();
        }
    }

    public void showConversations()
    {
        Map<Long, Conversation> conversationMap = controller.getConversationsOfUser();
        Map<Long, String> conversationNamesMap = controller.getConversationsNameOfUser();
        userConnectedPanel.getConversationsPanel().removeAll();
        for(Long conversationId : conversationMap.keySet())
        {
            IndividualConversationPanel conversationPanel = new IndividualConversationPanel(gui, controller);
            Conversation conversation = conversationMap.get(conversationId);
            conversationPanel.setConversation(conversationMap.get(conversationId));
            int nbMessages = conversation.getMessages().size();
            String text;
            String sender;
            if(nbMessages!=0){
                text = conversation.getMessages().get(conversation.getMessages().size()-1).getValue();
                sender = conversation.getMessages().get(conversation.getMessages().size()-1).getSender()+" : ";

            }else {
                text = "No message in the conversation";
                sender="";
            }
                JLabel usernames = new JLabel(conversationNamesMap.get(conversationId));
                JLabel message = new JLabel(text);
                JLabel senderUsername = new JLabel(sender);
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

}
