package client.controller;

import client.SocketThread;
import client.controller.state.*;
import client.gui.Gui;
import common.model.Conversation;
import common.model.User;
import common.utils.ConnectionState;
import common.utils.SocketUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private User currentUser;
    private Conversation currentConversation;
    public final Gui gui;
    private SocketThread socketThread;
    private Map<Long, Conversation> conversationsOfUser;
    private Map<Long, String> conversationsNameOfUser;

    public final InitState initState;
    public final ConnectingState askUserLoginState;
    public final ConnectingState connectingState;
    public final UserConnectedState userConnectedState;
    public final ConversationOpenedState conversationOpenedState;
    public final TerminationState terminationState;

    private State currentState;

    private Socket socket;
    private SocketUtils socketUtils;

    public ConnectionState currentConnection;


    public Controller() {
        conversationsOfUser = new HashMap<>();
        conversationsNameOfUser = new HashMap<>();
        this.gui = new Gui(this);
        this.initState = new InitState();
        this.askUserLoginState = new ConnectingState();
        this.connectingState = new ConnectingState();
        this.userConnectedState = new UserConnectedState();
        this.conversationOpenedState = new ConversationOpenedState();
        this.terminationState = new TerminationState();
        init();
    }

    public void init() {
        gui.init();
        setCurrentState(initState);
    }

    public void setCurrentState(State newState) {
        this.currentState = newState;
        runCurrentState();
    }

    public void runCurrentState() {
        this.currentState.run(this, gui);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;

        if (this.socketUtils != null) {
            this.socketUtils.close();
        }

        this.socketUtils = new SocketUtils(socket);

    }

    public ConnectionState getCurrentConnection() {
        return currentConnection;
    }

    public void setCurrentConnection(ConnectionState currentConnection) {
        this.currentConnection = currentConnection;
    }

    public String receiveSocketLine() throws IOException {
        return this.socketUtils.receiveSocketLine();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Conversation getCurrentConversation() {
        return currentConversation;
    }

    public void setCurrentConversation(Conversation currentConversation) {
        this.currentConversation = currentConversation;
    }

    public void connectingButtonClick(Gui gui, String username, String ip, int port) {
        this.currentState.connectingButtonClick(this, username, ip, port);
        // gui.setCurrentViewState(new UserConnectedViewState(gui));
    }

    public void creatingConversationButtonClick(Gui gui, String username) {
        this.currentState.creatingConversationButtonClick(this, username);
    }

    public void quitDefinitlyConv(Gui gui) {
        this.currentState.quitDefinitlyConv(this);
    }

    public void closeSocket() {
        if (this.socketUtils != null)
            this.socketUtils.close();
    }


    public void disconnectButtonClick(Gui gui) {
        this.currentState.disconnectButtonClick(this);
    }

    public void sendingButtonClick(Gui gui, String textMessage) {
        this.currentState.sendingMessageButtonClick(this, textMessage);
    }

    public void addUserButtonClick(Gui gui, String username) {
        this.currentState.addUserToTheConversation(this, username);
    }


    public SocketThread getSocketThread() {
        return socketThread;
    }

    public void setSocketThread(SocketThread socketThread) {
        this.socketThread = socketThread;
    }

    public Map<Long, Conversation> getConversationsOfUser() {
        return conversationsOfUser;
    }

    public void setConversationsOfUser(Map<Long, Conversation> conversationsOfUser) {
        this.conversationsOfUser = conversationsOfUser;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void updateConversationsTimer() {
        this.getCurrentState().updateConversationsTimer(this);
    }

    public Map<Long, String> getConversationsNameOfUser() {
        return conversationsNameOfUser;
    }

    public void setConversationsNameOfUser(Map<Long, String> conversationsNameOfUser) {
        this.conversationsNameOfUser = conversationsNameOfUser;
    }

    public void changeFocusConversation(Conversation conversation) {
        this.getCurrentConnection().setCurrentConversation(conversation);
        this.setCurrentState(this.conversationOpenedState);
    }

    public void quitConversation() {
        this.getCurrentConnection().setCurrentConversation(null);
        this.setCurrentState(this.userConnectedState);
    }

    public void emptyTheConnection() {
        this.getCurrentConnection().setCurrentUser(null);
        this.getCurrentConnection().setCurrentCommand(null);
        this.getCurrentConnection().setCurrentConversation(null);
        this.getCurrentConnection().setAlive(false);
        this.getCurrentConnection().setCurrentSocket(null);
    }

}
