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

    /**
     * Set the current connection of the application
     * @param currentConnection the current connection
     */
    public void setCurrentConnection(ConnectionState currentConnection) {
        this.currentConnection = currentConnection;
    }

    /**
     * Utility function to receive a line from a socket
     * @return the line
     * @throws IOException
     */
    public String receiveSocketLine() throws IOException {
        return this.socketUtils.receiveSocketLine();
    }

    /**
     * Get the current user of the application
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Set the current user of the application
     * @param currentUser the current user
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    /**
     * Called when the connect button is clicked
     * Call the connecting function in the current state
     * @param gui gui of the application
     */
    public void connectingButtonClick(Gui gui, String username, String ip, int port) {
        this.currentState.connectingButtonClick(this, username, ip, port);
        // gui.setCurrentViewState(new UserConnectedViewState(gui));
    }

    /**
     * Called when the creating chatroom button is clicked
     * Call the creating chatroom function in the current state
     * @param gui gui of the application
     */
    public void creatingConversationButtonClick(Gui gui, String username) {
        this.currentState.creatingConversationButtonClick(this, username);
    }

    /**
     * Called when the quit definitly button is clicked
     * Call the quit definitly function in the current state
     * @param gui gui of the application
     */
    public void quitDefinitlyConv(Gui gui) {
        this.currentState.quitDefinitlyConv(this);
    }

    /**
     * Close the socket
     */
    public void closeSocket() {
        if (this.socketUtils != null)
            this.socketUtils.close();
    }


    /**
     * Called when the disconnect button is clicked,
     * call the disconnecting function in the current state
     * @param gui gui of application
     */
    public void disconnectButtonClick(Gui gui) {
        this.currentState.disconnectButtonClick(this);
    }

    /**
     * Called when the button to send a message is clicked
     * call the sending function in the current state
     * @param gui gui of application
     * @param textMessage message to be send
     */
    public void sendingButtonClick(Gui gui, String textMessage) {
        this.currentState.sendingMessageButtonClick(this, textMessage);
    }

    /**
     * Called when the button to add an user is clicked
     * call the add user function in the current state
     * @param gui the gui of application
     * @param username the username of the user to add to the conversation
     */
    public void addUserButtonClick(Gui gui, String username) {
        this.currentState.addUserToTheConversation(this, username);
    }


    /**
     * set the value of the socket thread
     * @param socketThread the socket thread
     */
    public void setSocketThread(SocketThread socketThread) {
        this.socketThread = socketThread;
    }

    /**
     * Get a map containing the id of conversations the user is in as keys and conversations as value
     * @return the map containing the ids and the conversations
     */
    public Map<Long, Conversation> getConversationsOfUser() {
        return conversationsOfUser;
    }

    /**
     * Get the current state of the application
     * @return the current state
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Fonction called every second to get an update of the conversations of the user.
     */
    public void updateConversationsTimer() {
        this.getCurrentState().updateConversationsTimer(this);
    }

    /**
     * Get a map containing the id of conversations as keys and names of conversations as value
     * @return the map containing the ids and the names
     */
    public Map<Long, String> getConversationsNameOfUser() {
        return conversationsNameOfUser;
    }



    /**
     * Change the focus conversation and enter in the conversationOpenedState
     * @param conversation the new focused conversation
     */
    public void changeFocusConversation(Conversation conversation) {
        this.getCurrentConnection().setCurrentConversation(conversation);
        this.setCurrentState(this.conversationOpenedState);
    }

    /**
     * Quit temporarly a conversation to return to the userConnectedState
     * Set the focused conversation to null
     */
    public void quitConversation() {
        this.getCurrentConnection().setCurrentConversation(null);
        this.setCurrentState(this.userConnectedState);
    }

    /**
     * Empty the current connection of the user, used when a user disconnects
     */
    public void emptyTheConnection() {
        this.getCurrentConnection().setCurrentUser(null);
        this.getCurrentConnection().setCurrentCommand(null);
        this.getCurrentConnection().setCurrentConversation(null);
        this.getCurrentConnection().setAlive(false);
        this.getCurrentConnection().setCurrentSocket(null);
    }

}
