package client;

import client.controller.Controller;
import client.controller.action.Action;
import client.controller.action.CreateNewConversationAction;
import client.controller.action.LoadAllChatroomSummariesAction;
import client.controller.action.ReceiveMessageAction;
import common.utils.ConnectionState;

import java.net.Socket;

public class SocketThread extends Thread {

    private final Socket clientSocket;
    private final Controller controller;
    public ConnectionState currentConnection;

    public SocketThread(Socket s, Controller controller) {
        this.clientSocket = s;
        this.controller = controller;

    }

    @Override
    public void run() {
        currentConnection = new ConnectionState(clientSocket);

        try {
            while (currentConnection.isAlive()) {


                String line = currentConnection.receiveSocketLine();


                if (line == null) break;

                currentConnection.setCurrentCommand(line);


                String[] arguments = line.split(":");
                String command = arguments[0];

                Action actionToExecute = null;
                switch (command) {

                    case "confirmMessage" ->{

                        actionToExecute = new ReceiveMessageAction();
                    }
                    case "confirmChatroom" ->{

                        actionToExecute = new CreateNewConversationAction();
                    }
                    case "chatroomSummaries" -> {
                        actionToExecute = new LoadAllChatroomSummariesAction();
                    }

                    case "confirmDisconnect" ->{
                        controller.setCurrentState(controller.terminationState);
                    }

                    
                    default -> controller.setCurrentState(controller.terminationState);
                }
                if(actionToExecute!=null)
                {
                    actionToExecute.execute(currentConnection,controller,controller.gui);
                }

            }
        } catch (Exception e) {
            System.err.println("Error in Socket Connection: " + e);
        } finally {
            //controller.getCurrentConversation().;
        }
    }
}
