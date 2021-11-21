package server;

import common.model.Conversation;
import common.model.Message;
import common.model.TextMessage;
import common.model.User;
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
        userDao = new UserDao();
        conversationDao = ConversationDao.getInstance();

        /* PERSISTANCE - MISE EN FICHIER */
//        User u1 = new User("alexis");
//        User u2 = new User("florie");
//
//        long id = conversationDao.create(u1, u2);
//        System.out.println("Id=" + id);
//
//        Conversation conv = conversationDao.searchConversationWithId(id);
//        conv.addMessage(new TextMessage(conv.getId(), u1.getUsername(), "salut !"));
//        conv.addMessage(new TextMessage(conv.getId(), u2.getUsername(), "hello !"));
//
//        conversationDao.persistAll();

        /* PERSISTANCE - CHARGEMENT DES DONNEES */
        conversationDao.loadConversations();
        Conversation conv = conversationDao.searchConversationWithId(1L);

        System.out.println("conv id=" + conv.getId());
        System.out.println("user size=" + conv.numberOfParticipants());
        System.out.println("user 1=" + conv.getUsername(0));
        System.out.println("user 2=" + conv.getUsername(1));

        for(Message message : conv.getMessages())
        {
            System.out.println(message);
        }


        /*
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
        }*/
    }
}
