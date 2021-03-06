package common.utils;

import common.model.Conversation;
import common.model.User;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * Represents the status of a connection.
 */
public class ConnectionState implements Serializable {
    private User currentUser;
    private Conversation currentConversation;
    private Socket currentSocket;
    private SocketUtils socketUtils;

    private boolean alive;

    private String currentCommand;

    public ConnectionState(Socket currentSocket) {
        this.currentUser = null;
        this.currentConversation = null;
        this.currentSocket = currentSocket;
        this.socketUtils = new SocketUtils(currentSocket);
        this.alive = true;
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

    public Socket getCurrentSocket() {
        return currentSocket;
    }

    public void setCurrentSocket(Socket currentSocket) {
        this.currentSocket = currentSocket;

        if (this.socketUtils != null)
            this.socketUtils.close();

        if (currentSocket != null)
            this.socketUtils = new SocketUtils(currentSocket);
    }

    public String getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(String currentCommand) {
        this.currentCommand = currentCommand;
    }

    public String[] getCurrentArguments() {
        return currentCommand.split(":");
    }

    public String[] getCurrentArguments(int limit) {
        return currentCommand.split(":", limit);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public SocketUtils getSocketUtils() {
        return socketUtils;
    }

    /**
     * Utility function to send a message on the user's socket.
     *
     * @param message the message to send
     */
    public void sendSocketMessage(String message) {
        this.socketUtils.sendSocketMessage(message);
    }


    /**
     * Utility function to receive a message from the user's socket.
     *
     * @return String, the message
     * @throws IOException IOException
     */
    public String receiveSocketLine() throws IOException {
        return this.socketUtils.receiveSocketLine();
    }

    /**
     * Utility function to determine if there is a message pending.
     *
     * @return true if a message is waiting in buffer, else false
     * @throws IOException IOException
     */
    public boolean socketIncomingData() throws IOException {
        return this.socketUtils.socketIncomingData();
    }

    /**
     * Closes everything.
     */
    public void close() {
        if (getCurrentUser() != null) {
            getCurrentUser().setConnected(false);
            if (getCurrentUser().getSocket() != null) {
                closeSocket();
                setCurrentSocket(null);
                getCurrentUser().setSocket(null);
            }
        }
    }

    /**
     * Closes the socket.
     */
    public void closeSocket() {
        if (this.socketUtils != null)
            this.socketUtils.close();

        this.socketUtils = null;
        this.currentSocket = null;
    }
}
