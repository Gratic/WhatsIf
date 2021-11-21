package server.dao;

import common.model.Conversation;
import common.model.User;
import common.utils.Pair;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConversationDao manages Conversations.
 */
public class ConversationDao {
    private final Map<Long, Conversation> conversations;

    private static long nextID = -1L;
    private final static String dataLocation = "data/server/";
    private final static String idLocation = dataLocation + "id/";
    private final static String convLocation = dataLocation + "conv/";
    private static ConversationDao conversationDaoSingleton = null;

    public static ConversationDao getInstance()
    {
        if(conversationDaoSingleton == null)
        {
            conversationDaoSingleton = new ConversationDao();
            loadId();
        }

        return conversationDaoSingleton;
    }

    private ConversationDao()
    {
        conversations = new ConcurrentHashMap<>();
    }

    /**
     * Creates a new conversation.
     *
     * @param u1 a user
     * @param u2 a user
     * @return true if created, else false.
     */
    public long create(User u1, User u2) {
        Pair<User, User> users = User.orderTwoUser(u1, u2);
        long currentId = nextID;

        if (conversations.containsKey(nextID))
            return -1;
        else {
            conversations.put(nextID, new Conversation(nextID, users.getFirst(), users.getSecond()));
            nextID++;
            persistId();
        }

        return currentId;
    }

    /**
     * Finds and retrieve the conversation of user u1 and user u2.
     *
     * @param u1 a user
     * @param u2 a user
     * @return a conversation, null if not existing.
     */
    public Conversation searchConversationWithBothUsers(User u1, User u2) {
        //TODO: change
        Pair<User, User> users = User.orderTwoUser(u1, u2);
        return conversations.getOrDefault(0L, null);
    }

    public Conversation searchConversationWithId(long id)
    {
        return conversations.getOrDefault(id, null);
    }

    public void loadConversations()
    {
        //TODO: load nextID
        File f = new File(convLocation);

        for(String path : f.list())
        {
            Conversation conversation = loadConversation(convLocation + path);
            if(conversation != null)
            {
                conversations.put(conversation.getId(), conversation);
            }
        }
    }

    public Conversation loadConversation(String path)
    {
        System.out.println(path);
        ObjectInputStream ois = null;

        Conversation conversation = null;

        try {
            final FileInputStream fichierIn = new FileInputStream(path);
            ois = new ObjectInputStream(fichierIn);
            conversation = (Conversation) ois.readObject();
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }

        return conversation;
    }

    public void persist(Conversation conversation)
    {
        File f = new File(convLocation);
        if(!f.exists())
        {
            f.mkdirs();
        }

        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream(convLocation + conversation.getId()+".conv");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(conversation);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void persistAll()
    {
        for(Conversation conversation : conversations.values())
        {
            persist(conversation);
        }
    }

    public static void loadId()
    {
        File f = new File(idLocation);
        if(!f.exists())
        {
            f.mkdirs();
        }

        File fId = new File(idLocation + "conversationDao.id");
        if(!fId.exists())
        {
            nextID = 0L;
        }
        else
        {
            try {
                BufferedReader fIdr = new BufferedReader(new FileReader(fId));

                String line = fIdr.readLine();

                nextID = Long.parseLong(line);

                fIdr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void persistId()
    {
        File f = new File(idLocation);
        if(!f.exists())
        {
            f.mkdirs();
        }

        File fId = new File(idLocation + "conversationDao.id");

        try {
            FileWriter fIdw = new FileWriter(fId);
            fIdw.write(String.valueOf(nextID) + "\n");
            fIdw.flush();
            fIdw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
