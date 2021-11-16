package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AskUserLoginState implements State{

    Socket connectionSocket = null;
    PrintStream socOut = null;
    BufferedReader socIn = null;
    private String username = null;
    private String ip = null;
    private int port = 0;

    public void run(Controller c, Gui gui) {
        Scanner sc = new Scanner(System.in);
        /*
        System.out.println("Enter your username");
        String username = sc.nextLine();
         */

        if (username != null && !username.equals("")) {

            try {
                connectionSocket = new Socket(ip, port);
                socOut = new PrintStream(connectionSocket.getOutputStream());
                socIn = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                socOut.println("requestConnection:" + username);

                c.setSocket(connectionSocket);
                c.setSocIn(socIn);
                c.setSocOut(socOut);

                c.setCurrentState(c.connectingState);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for "
                        + "the connection to:" + ip);
                c.setCurrentState(c.connectionFailedState);
            }
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
