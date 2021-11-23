package client;

import client.controller.Controller;
import client.controller.action.*;
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
        currentConnection = controller.getCurrentConnection();
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

                    case "confirmQuitChatroom" -> {
                        actionToExecute = new QuitChatroomAction();
                    }

                    case "confirmAddUserToChatroom" -> {
                        actionToExecute = new BeingAddedInChatroomAction();
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
            e.printStackTrace();
        } finally {
            controller.setCurrentState(controller.terminationState);
        }
    }
}
