package common.model;

import common.utils.ConnectionState;
import common.utils.Pair;
import common.utils.SocketUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.Objects;

/**
 * Represents a user of the application.
 */
public class User implements Serializable {
    private String username;
    private transient Boolean isConnected;
    private transient Socket socket;
    private transient SocketUtils socketUtils;

    public User(String username) {
        this.username = username;
        this.isConnected = false;
        this.socket = null;
        this.socketUtils = null;
    }

    public User(String username, Socket socket) {
        this.username = username;
        this.isConnected = false;
        this.socket = socket;
        this.socketUtils = new SocketUtils(socket);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;

        if (this.socketUtils != null)
            this.socketUtils.close();

        if(socket != null)
            this.socketUtils = new SocketUtils(socket);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Utility function to return a pair of alphabetically ordered users.
     *
     * @param u1 a user
     * @param u2 a user
     * @return A pair of User, first has his lexicographical order before second.
     */
    public static Pair<User, User> orderTwoUser(User u1, User u2) {
        Pair<User, User> result;

        if (u1.getUsername().compareTo(u2.getUsername()) <= 0) {
            result = new Pair<>(u1, u2);
        } else {
            result = new Pair<>(u2, u1);
        }

        return result;
    }

    public void closeSocket() {
        this.socketUtils.close();
    }
}
