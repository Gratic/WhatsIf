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

        /* PERSISTANCE - MISE EN FICHIER */
//        userDao.create(new User("alexis"));
//        userDao.create(new User("florie"));
//
//        userDao.persistAll();
//
//        List<String> usernames = new ArrayList<>();
//        usernames.add("alexis");
//        usernames.add("florie");
//
//        long id = conversationDao.create(usernames);
//        System.out.println("Id=" + id);
//
//        Conversation conv = conversationDao.searchConversationWithId(id);
//        conv.addMessage(new TextMessage(conv.getId(), usernames.get(0), "salut !"));
//        conv.addMessage(new TextMessage(conv.getId(), usernames.get(1), "hello !"));
//
//        conversationDao.persistAll();

        /* PERSISTANCE - CHARGEMENT DES DONNEES */
//        Conversation conv = conversationDao.searchConversationWithId(0L);
//
//        System.out.println("conv id=" + conv.getId());
//        System.out.println("user size=" + conv.numberOfParticipants());
//        System.out.println("user 1=" + conv.getUsername(0));
//        System.out.println("user 2=" + conv.getUsername(1));
//
//        for(Message message : conv.getMessages())
//        {
//            System.out.println(message);
//        }

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
