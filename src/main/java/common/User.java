package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class User {
    private String username;
    private Boolean isConnected;
    private Socket socket;

    private PrintStream socOut = null;
    private BufferedReader socIn = null;

    public User(String username, Socket socket) {
        this.username = username;
        this.isConnected = false;
        this.socket = socket;

        try {
            this.socOut = new PrintStream(socket.getOutputStream());
            this.socIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    public void sendSocketMessage(String message) {
        this.socOut.println(message);
    }

    public String receiveSocketLine() throws IOException {
        return this.socIn.readLine();
    }
}
