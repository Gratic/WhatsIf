package server;

import common.Conversation;
import common.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket clientSocket;

    public ClientThread(Socket s) {
        this.clientSocket = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintStream socOut = new PrintStream(clientSocket.getOutputStream());

            User currentUser = null;
            Conversation currentConversation = null;

            while (true) {
                String line = socIn.readLine();
                String[] arguments = line.split(":");

                if (arguments.length != 0) {
                    switch (arguments[0]) {
                        case "requestConnection" -> {
                            String username = arguments[1];
                            if (!username.equals("")) {
                                System.out.println("requestConnection: " + username);

                                currentUser = new User(username, clientSocket);
                                currentUser.setConnected(true);

                                if (MainServer.userRegistry.addUser(currentUser)) {
                                    System.out.println("connection successful");
                                    socOut.println("confirmConnection:" + username + ":0");
                                } else {
                                    System.out.println("connection failed");
                                    socOut.println("confirmConnection:" + username + ":1");
                                }
                            }
                        }
                        case "requestJoinChatroom" -> {
                            String username = arguments[1];

                            System.out.println("requestJoinChatroom: " + username);

                            if (MainServer.userRegistry.isConnected(username)) {
                                currentConversation = new Conversation(currentUser, MainServer.userRegistry.getUser(username));
                                socOut.println("confirmJoinChatroom:" + username + ":0");
                                System.out.println("confirmJoinChatroom: " + username + ":0");
                            } else {
                                socOut.println("confirmJoinChatroom:" + username + ":1");
                                System.out.println("confirmJoinChatroom: " + username + ":1");
                            }
                        }
                        case "sendMessage" -> {
                            String confirmMessage = "confirmTextMessage:" + currentUser.getUsername() + ":" + arguments[1];
                            socOut.println(confirmMessage);
                            currentConversation.getUser2().sendSocketMessage(confirmMessage);
                        }
                        case "quitConversation" -> {
                            currentConversation = null;
                            socOut.println("quitChatroom");
                        }
                        default -> System.out.println("Invalid Command: " + line);
                    }
                }

                socOut.println(line);
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
