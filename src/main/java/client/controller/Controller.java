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

    public final InitState initState;
    public final ConnectingState connectingState;
    public final ConnectionFailedState connectionFailedState;
    public final UserConnectedState userConnectedState;
    public final JoiningConversationState joiningConversationState;
    public final JoiningConversationFailedState joiningConversationFailedState;
    public final ConversationJoinedState conversationJoinedState;
    public final QuittingConversationState quittingConversationState;
    public final QuittingConversationFailedState quittingConversationFailedState;
    public final TerminationState terminationState;

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
        this.terminationState = new TerminationState();

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

    public void setSocOut(PrintStream socOut) {
        this.socOut = socOut;
    }

    public void setSocIn(BufferedReader socIn) {
        this.socIn = socIn;
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

    public void close()
    {
        if(socket != null && !socket.isClosed())
        {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(socIn != null)
        {
            try {
                socIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(socOut != null)
        {
            socOut.close();
        }
    }
}
