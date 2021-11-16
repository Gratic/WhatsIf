package server;

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

        try {
            while (currentConnection.isAlive()) {
                String line = currentConnection.receiveSocketLine();

                if (line == null) break;

                currentConnection.setCurrentCommand(line);

                String[] arguments = currentConnection.getCurrentArguments();
                String command = arguments[0];

                Action actionToExecute = null;
                switch (command) {
                    case "requestConnection" -> actionToExecute = new RequestConnectionAction();
                    case "requestJoinChatroom" -> actionToExecute = new RequestJoinChatroom();
                    case "sendMessage" -> actionToExecute = new SendMessageAction();
                    case "quitChatroom" -> actionToExecute = new QuitChatroomAction();
                    case "disconnect" -> actionToExecute = new DisconnectAction();
                    default -> System.out.println("Invalid Command: " + line);
                }

                if (actionToExecute != null)
                    actionToExecute.execute(currentConnection);
            }
        } catch (Exception e) {
            System.err.println("Error in Client Connection: " + e);
        } finally {
            currentConnection.close();
        }
    }
}
