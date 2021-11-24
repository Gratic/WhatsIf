package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import common.command.CommandSender;
import common.model.User;
import common.utils.ConnectionState;
import common.utils.SocketUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Connecting state, accessed when a user tries to connect
 * <p>
 * After state(s) possible : UserConnectedState, InitState
 * Before state(s) possible : InitState
 */ 
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

                c.setSocket(connectionSocket);
                c.setCurrentConnection(new ConnectionState(connectionSocket));
                SocketUtils socketUtils = c.getCurrentConnection().getSocketUtils();

                CommandSender commandSender = new CommandSender(socketUtils);
                commandSender.sendConnectToUser(username);

                String message = c.receiveSocketLine();
                String[] arguments = message.split(":");

                String command = arguments[0];
                String returnValue = arguments[2];

                if (command != null && command.equals("confirmConnect") && returnValue.equals("0")) {
                    // Connection success
                    User user = new User(arguments[1], c.getSocket());
                    c.setCurrentUser(user);
                    c.getCurrentUser().setConnected(true);

                    c.getCurrentConnection().setCurrentUser(user);

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
