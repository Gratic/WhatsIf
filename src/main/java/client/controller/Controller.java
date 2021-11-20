package client.controller;

import client.controller.state.*;
import client.gui.Gui;
import common.model.Conversation;
import common.model.TextMessage;
import common.model.User;
import common.utils.ConnectionState;
import common.utils.SocketUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private User currentUser;
    private String usernameOtherUser;
    private Conversation currentConversation;
    private List<TextMessage> messagesSent;
    private List<String> messagesReceived;
    private final Gui gui;

    public final InitState initState;
    public final AskUserLoginState askUserLoginState;
    public final ConnectingState connectingState;
    public final ConnectionFailedState connectionFailedState;
    public final AskUserConversationState askUserConversationState;
    public final UserConnectedState userConnectedState;
    public final JoiningConversationState joiningConversationState;
    public final JoiningConversationFailedState joiningConversationFailedState;
    public final ConversationJoinedState conversationJoinedState;
    public final QuittingConversationState quittingConversationState;
    public final QuittingConversationFailedState quittingConversationFailedState;
    public final TerminationState terminationState;

    private State currentState;

    private Socket socket;
    private SocketUtils socketUtils;

    public ConnectionState currentConnection;


    public Controller() {
        this.gui = new Gui(this);
        this.messagesSent = new ArrayList<>();
        this.messagesReceived = new ArrayList<>();
        this.initState = new InitState();
        this.askUserLoginState = new AskUserLoginState();
        this.connectingState = new ConnectingState();
        this.connectionFailedState = new ConnectionFailedState();
        this.userConnectedState = new UserConnectedState();
        this.askUserConversationState = new AskUserConversationState();
        this.joiningConversationState = new JoiningConversationState();
        this.joiningConversationFailedState = new JoiningConversationFailedState();
        this.conversationJoinedState = new ConversationJoinedState();
        this.quittingConversationState = new QuittingConversationState();
        this.quittingConversationFailedState = new QuittingConversationFailedState();
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

    public void connectingButtonClick(Gui gui, String username, String ip, int port ){
        this.currentState.connectingButtonClick(this, username, ip, port);
       // gui.setCurrentViewState(new UserConnectedViewState(gui));
    }

    public void joiningConversationButtonClick(Gui gui, String username){
        this.currentState.joiningConversationButtonClick(this, username);
    }

    public void retryConnectingButtonClick (Gui gui){
        this.currentState.retryConnectingButtonClick(this);
    }

    public void quitConnectingButtonClick (Gui gui)
    {
        this.currentState.quittingConnectingButtonClick(this);
    }

    public void closeSocket() {
        if (this.socketUtils != null)
            this.socketUtils.close();
    }

    public void quittingConvButtonClick(Gui gui)
    {
        this.currentState.quittingConvButtonClick(this);
    }

    public void sendingButtonClick(Gui gui, String textMessage)
    {

        this.currentState.sendingMessageButtonClick(this, textMessage);
    }

   /* public void receivingButtonClick (Gui gui)
    {
        this.currentState.receivingMessageButtonClick(this);
    }

    */
    public String getUsernameOtherUser() {
        return usernameOtherUser;
    }

    public void setUsernameOtherUser(String usernameOtherUser) {
        this.usernameOtherUser = usernameOtherUser;
    }

    public List<TextMessage> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<TextMessage> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public List<String> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<String> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public void clearMessagesSent()
    {
        this.messagesSent.clear();
    }

    public void clearMessagesReceived()
    {
        this.messagesReceived.clear();
    }

    public void addMessageSent(TextMessage message)
    {
        messagesSent.add(message);
    }

    public void addMessageReceived(String message)
    {
        messagesReceived.add(message);
    }

}
