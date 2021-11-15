package client.controller;

import client.controller.state.*;
import common.model.Conversation;
import common.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Controller {
    private User currentUser;
    private Conversation conversation;

    public InitState initState;
    public ConnectingState connectingState;
    public ConnectionFailedState connectionFailedState;
    public UserConnectedState userConnectedState;
    public JoiningConversationState joiningConversationState;
    public JoiningConversationFailedState joiningConversationFailedState;
    public ConversationJoinedState conversationJoinedState;
    public QuittingConversationState quittingConversationState;
    public QuittingConversationFailedState quittingConversationFailedState;

    private State currentState;

    private Socket socket;
    private PrintStream socOut = null;
    private BufferedReader socIn = null;


    public Controller() {
        this.initState = new InitState();
        this.connectingState = new ConnectingState();
        this.connectionFailedState = new ConnectionFailedState();
        this.userConnectedState = new UserConnectedState();
        this.joiningConversationState = new JoiningConversationState();
        this.joiningConversationFailedState = new JoiningConversationFailedState();
        this.conversationJoinedState = new ConversationJoinedState();
        this.quittingConversationState = new QuittingConversationState();
        this.quittingConversationFailedState = new QuittingConversationFailedState();

        init();
    }

    public void init() {
        setCurrentState(initState);
    }

    public void setCurrentState(State newState) {
        this.currentState = newState;
        runCurrentState();
    }

    public void runCurrentState() {
        this.currentState.run(this);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintStream getSocOut() {
        return socOut;
    }

    public void setSocOut(PrintStream socOut) {
        this.socOut = socOut;
    }

    public BufferedReader getSocIn() {
        return socIn;
    }

    public void setSocIn(BufferedReader socIn) {
        this.socIn = socIn;
    }

    public void sendSocketMessage(String message) {
        this.socOut.println(message);
    }

    public String receiveSocketLine() throws IOException {
        return this.socIn.readLine();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
