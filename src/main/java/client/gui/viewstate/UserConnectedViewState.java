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
        userConnectedPanel.getConversationsPanel().removeAll();
        for(Long conversationId : conversationMap.keySet())
        {
            IndividualConversationPanel conversationPanel = new IndividualConversationPanel(gui);
            Conversation conversation = conversationMap.get(conversationId);
            String lastMessage = conversation.getMessages().get(conversation.getMessages().size()-1).getValue();
            JLabel message = new JLabel(lastMessage);
            conversationPanel.add(message);
            conversationPanel.revalidate();
            conversationPanel.repaint();
            userConnectedPanel.getConversationsPanel().add(message);
            userConnectedPanel.revalidate();
            userConnectedPanel.repaint();
        }
    }

}
