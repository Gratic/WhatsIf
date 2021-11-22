package client.gui;

import client.controller.Controller;
import client.gui.panel.InitPanel;
import client.gui.panel.MainPanel;
import client.gui.viewstate.ConversationOpenedViewState;
import client.gui.viewstate.InitViewState;
import client.gui.viewstate.UserConnectedViewState;
import client.gui.viewstate.ViewState;

import javax.swing.*;
import java.awt.*;

public class Gui {

    protected Controller controller;
    //protected ViewState currentViewState;


    protected JFrame frame;
    protected int height = 960;
    protected int width = 960;

    public MainPanel mainPanel;
    public ViewState currentState;


    private ConversationOpenedViewState conversationOpenedViewState;
    private InitViewState initViewState;
    private UserConnectedViewState userConnectedViewState;


    public Gui(Controller controller) {
        this.controller = controller;


        frame = new JFrame("WhatsIF");

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new MainPanel();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);


    }

    public void init() {

        this.currentState = new InitViewState(this);
        //this.currentState = new UserConnectedViewState(this);
    }

    public void setCurrentViewState(ViewState currentViewState) {
        this.currentState = currentViewState;
    }



    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public Controller getController() {
        return controller;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ConversationOpenedViewState getConversationOpenedViewState() {
        return conversationOpenedViewState;
    }

    public void setConversationOpenedViewState(ConversationOpenedViewState conversationOpenedViewState) {
        this.conversationOpenedViewState = conversationOpenedViewState;
    }

    public InitViewState getInitViewState() {
        return initViewState;
    }

    public void setInitViewState(InitViewState initViewState) {
        this.initViewState = initViewState;
    }

    public UserConnectedViewState getUserConnectedViewState() {
        return userConnectedViewState;
    }

    public void setUserConnectedViewState(UserConnectedViewState userConnectedViewState) {
        this.userConnectedViewState = userConnectedViewState;
    }

    public ViewState getCurrentState() {
        return currentState;
    }
}
