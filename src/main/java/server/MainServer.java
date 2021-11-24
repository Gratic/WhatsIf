package server;

import server.dao.ConversationDao;
import server.dao.UserDao;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * MainServer is the starting point.
 * Listen on port given in the first argument.
 * Creates a new thread for each connection.
 */
public class MainServer {
    public static UserDao userDao;
    public static ConversationDao conversationDao;

    public static void main(String[] args) {
        ServerSocket listenSocket;
        userDao = UserDao.getInstance();
        conversationDao = ConversationDao.getInstance();

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
            System.err.println("Error in MainServer: " + e);
        }
    }
}
