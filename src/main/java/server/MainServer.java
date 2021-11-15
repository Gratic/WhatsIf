package server;

import server.dao.ConversationDao;
import server.dao.UserDao;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static UserDao userDao;
    public static ConversationDao conversationDao;

    public static void main(String[] args) {
        ServerSocket listenSocket;
        userDao = new UserDao();
        conversationDao = new ConversationDao();

        if (args.length != 1) {
            System.out.println("Usage: java EchoServer <EchoServer port>");
            System.exit(1);
        }
        try {
            listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
            System.out.println("Server ready...");
            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Connexion from:" + clientSocket.getInetAddress());
                ClientThread ct = new ClientThread(clientSocket);
                ct.start();
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
