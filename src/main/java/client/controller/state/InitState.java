package client.controller.state;

import client.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Initial state. The application starts in this state.
 *
 * After state(s) possible : Connecting
 * Before state(s) possible : Connection Failed
 */
public class InitState implements State {

    Socket connectionSocket = null;
    PrintStream socOut = null;
    BufferedReader socIn = null;

    public void run(Controller c) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your username");
        String username = sc.nextLine();

        if (username != null && !username.equals("")) {
            System.out.println("Enter the ip address");
            String ip = sc.nextLine();
            System.out.println("Enter the port");
            int port = sc.nextInt();

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
                System.exit(1);
            }
        }

    }
}
