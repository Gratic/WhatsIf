package client;

import client.controller.Controller;

import java.net.Socket;

public class SocketThread extends Thread {

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
                System.out.println("ligne : " + line);

                if (line == null) break;

                //currentConnection.setCurrentCommand(line);

                String[] arguments = line.split(":");
                String command = arguments[0];


                switch (command) {

                    case "confirmMessage" -> controller.conversationJoinedState.receiveMessage(controller, line);
                    case "confirmQuitChatroom" -> {
                        inConversation = false;
                        controller.clearMessagesSent();
                        controller.clearMessagesReceived();
                        controller.setCurrentState(controller.userConnectedState);
                    }
                    default -> controller.setCurrentState(controller.quittingConversationFailedState);
                }

            }
        } catch (Exception e) {
            System.err.println("Error in Socket Connection: " + e);
        } finally {
            //controller.getCurrentConversation().;
        }
    }
}
