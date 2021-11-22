package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import common.model.User;
import common.utils.ConnectionState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ConnectingState implements State {

    Socket connectionSocket = null;
    PrintStream socOut = null;
    BufferedReader socIn = null;
    private String username = null;
    private String ip = null;
    private int port = 0;

    public void run(Controller c, Gui gui) {


        if (username != null && !username.equals("")) {

            try {
                connectionSocket = new Socket(ip, port);
                socOut = new PrintStream(connectionSocket.getOutputStream());
                socIn = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                socOut.println("connect:" + username);

                c.setSocket(connectionSocket);
                c.setCurrentConnection(new ConnectionState(connectionSocket));

                String message = c.receiveSocketLine();
                String[] arguments = message.split(":");

                String command = arguments[0];
                String returnValue = arguments[2];

                if (command != null && command.equals("confirmConnect") && returnValue.equals("0")) {
                    // Connection success
                    c.setCurrentUser(new User(arguments[1], c.getSocket()));
                    c.getCurrentUser().setConnected(true);

                    System.out.println("Connecté !");

                    c.setCurrentState(c.userConnectedState);
                } else {
                    // Connection failed
                    c.setCurrentUser(null);
                    c.setCurrentState(c.initState);
                }


            } catch (IOException e) {
                System.err.println("Couldn't get I/O for "
                        + "the connection to:" + ip);
                c.setCurrentState(c.initState);
            }


        }


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void entry(String username, String ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
    }
}
