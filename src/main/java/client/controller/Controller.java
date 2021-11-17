package client.controller;

import client.controller.state.*;
import client.gui.Gui;
import client.gui.viewstate.UserConnectedViewState;
import common.model.Conversation;
import common.model.User;
import common.utils.SocketUtils;

import java.io.IOException;
import java.net.Socket;

public class Controller {
    private User currentUser;
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


    public Controller() {
        this.gui = new Gui(this);
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
    }

        if (this.socketUtils != null) {
            this.socketUtils.close();
        }

        this.socketUtils = new SocketUtils(socket);
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

    public void connectingButtonClick(Gui gui, String username, String ip, int port) {
        this.currentState.connectingButtonClick(this, username, ip, port);
       // gui.setCurrentViewState(new UserConnectedViewState(gui));
    }

    public void joiningConversationButtonClick(Gui gui, String username){
        this.currentState.joiningConversationButtonClick(this, username);
    }

    public void closeSocket() {
        if (this.socketUtils != null)
            this.socketUtils.close();
    }
}
