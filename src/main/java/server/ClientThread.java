package server;

import common.command.CommandSender;
import common.utils.ConnectionState;
import server.action.*;

import java.net.Socket;

/**
 * ClientThread manages a connection with a client.
 * Answer and send request from and to client.
 */
public class ClientThread extends Thread {
    private final Socket clientSocket;

    public ClientThread(Socket s) {
        this.clientSocket = s;
    }

    @Override
    public void run() {
        ConnectionState currentConnection = new ConnectionState(clientSocket);
        CommandSender commandSender = new CommandSender(currentConnection.getSocketUtils());

        try {
            while (currentConnection.isAlive()) {
                String line = currentConnection.receiveSocketLine();

                if (line == null) break;

                currentConnection.setCurrentCommand(line);

                String[] arguments = currentConnection.getCurrentArguments();
                String command = arguments[0];

                Action actionToExecute = null;
                switch (command) {
                    case "connect" -> actionToExecute = new RequestConnectionAction();
                    case "newChatroom" -> actionToExecute = new RequestCreateChatroomAction();
                    case "getChatroomSummaries" -> actionToExecute = new RequestGetChatroomSummariesAction();
                    case "getAllMessagesFromChatroom" -> actionToExecute = new RequestGetAllMessagesFromChatroomAction();
                    case "getAllMessagesFromChatroomSinceHash" -> actionToExecute = new RequestGetAllMessagesFromChatroomSinceHashAction();
                    case "sendMessage" -> actionToExecute = new SendMessageAction();
                    case "disconnect" -> actionToExecute = new DisconnectAction();
                    default -> System.out.println("Invalid Command: " + line);
                }

                if (actionToExecute != null)
                    actionToExecute.execute(currentConnection, commandSender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            currentConnection.close();
        }
    }
}
