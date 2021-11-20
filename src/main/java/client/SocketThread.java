package client;

import client.controller.Controller;
import common.utils.ConnectionState;
import server.action.*;

import java.net.Socket;

public class SocketThread extends Thread{

    private final Socket clientSocket;
    private final Controller controller;

    public SocketThread(Socket s, Controller controller) {
        this.clientSocket = s;
        this.controller = controller;

    }

    @Override
    public void run() {
        //ConnectionState currentConnection = new ConnectionState(clientSocket);

        boolean inConversation = true;
        try {
            while (inConversation) {

                String line = controller.getCurrentUser().receiveSocketLine();
                System.out.println("ligne : "+line);

                if (line == null) break;

                //currentConnection.setCurrentCommand(line);

                String[] arguments = line.split(":");
                String command = arguments[0];


                switch (command) {

                    case "confirmMessage" -> controller.conversationJoinedState.receiveMessage(controller,line);

                    default -> System.out.println("Invalid Command: " + line);
                }

            }
        } catch (Exception e) {
            System.err.println("Error in Socket Connection: " + e);
        } finally {
           //controller.getCurrentConversation().;
        }
    }
}
