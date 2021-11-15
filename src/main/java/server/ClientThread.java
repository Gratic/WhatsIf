package server;

import common.model.Conversation;
import common.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
                    String command = arguments[0];
                    switch (command) {
                        case "requestConnection" -> {
                            String username = arguments[1];
                            if (!username.equals("")) {
                                System.out.println("requestConnection: " + username);

                                currentUser = new User(username, clientSocket);
                                currentUser.setConnected(true);

                                if (MainServer.userDao.create(currentUser)) {
                                    System.out.println("connection successful");
                                    socOut.println("confirmConnection:" + username + ":0");
                                } else {
                                    currentUser = null;
                                    System.out.println("connection failed");
                                    socOut.println("confirmConnection:" + username + ":1");
                                }
                            }
                        }
                        case "requestJoinChatroom" -> {
                            String username = arguments[1];

                            System.out.println("requestJoinChatroom: " + username);

                            if (MainServer.userDao.isConnected(username)) {
                                User otherUser = MainServer.userDao.searchByUsername(username);
                                MainServer.conversationDao.create(currentUser, otherUser);

                                currentConversation = MainServer.conversationDao.searchConversationWithBothUsers(currentUser, otherUser);

                                currentConversation.setIsInRoom(currentUser, true);

                                socOut.println("confirmJoinChatroom:" + username + ":0");
                                System.out.println("confirmJoinChatroom: " + username + ":0");
                            } else {
                                socOut.println("confirmJoinChatroom:" + username + ":1");
                                System.out.println("confirmJoinChatroom: " + username + ":1");
                            }
                        }
                        case "sendMessage" -> {
                            User otherUser = currentConversation.getOtherUser(currentUser);
                            String type = arguments[1];
                            String value = arguments[2];

                            String confirmMessage = "confirmMessage:" + type + ":" + currentUser.getUsername() + ":" + value;

                            socOut.println(confirmMessage);

                            if (currentConversation.getIsInRoom(otherUser)) {
                                otherUser.sendSocketMessage(confirmMessage);
                            }
                        }
                        case "quitChatroom" -> {
                            currentConversation.setIsInRoom(currentUser, false);
                            currentConversation = null;
                            socOut.println("confirmQuitChatroom");
                        }
                        default -> System.out.println("Invalid Command: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
