package server.dao;

import common.model.Conversation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
    private final static String convLocation = dataLocation + "conversations/";
    private static ConversationDao conversationDaoSingleton = null;

    public static ConversationDao getInstance() {
        if (conversationDaoSingleton == null) {
            conversationDaoSingleton = new ConversationDao();
            loadId();
        }

        return conversationDaoSingleton;
    }

    private ConversationDao() {
        conversations = new ConcurrentHashMap<>();
        loadConversations();
    }

    /**
     * Creates a new conversation.
     *
     * @param usernames usernames
     * @return true if created, else false.
     */
    public long create(List<String> usernames) {
        long currentId = nextID;

        if (conversations.containsKey(nextID))
            return -1;
        else {
            conversations.put(nextID, new Conversation(nextID, usernames));
            nextID++;
            persistId();
        }

        return currentId;
    }

    public List<Conversation> searchConversationThatIncludeUsername(String username) {
        List<Conversation> result = new ArrayList<>();

        for (Conversation conversation : conversations.values()) {
            int nbParticipants = conversation.numberOfParticipants();
            for (int i = 0; i < nbParticipants; i++) {
                String user = conversation.getUsername(i);
                if (user.equals(username)) {
                    result.add(conversation);
                    break;
                }
            }
        }

        return result;
    }

    public Conversation searchConversationWithId(long id) {
        return conversations.getOrDefault(id, null);
    }

    public void loadConversations() {
        File f = new File(convLocation);

        if (f.exists()) {
            for (String path : f.list()) {
                Conversation conversation = loadConversation(convLocation + path);
                if (conversation != null) {
                    conversations.put(conversation.getId(), conversation);
                }
            }
        }

    }

    public Conversation loadConversation(String path) {
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

    public void persist(Conversation conversation) {
        File f = new File(convLocation);
        if (!f.exists()) {
            f.mkdirs();
        }

        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream(convLocation + conversation.getId() + ".conv");
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

    public void persistAll() {
        for (Conversation conversation : conversations.values()) {
            persist(conversation);
        }
    }

    public static void loadId() {
        File f = new File(idLocation);
        if (!f.exists()) {
            f.mkdirs();
        }

        File fId = new File(idLocation + "conversationDao.id");
        if (!fId.exists()) {
            nextID = 0L;
        } else {
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

    public static void persistId() {
        File f = new File(idLocation);
        if (!f.exists()) {
            f.mkdirs();
        }

        File fId = new File(idLocation + "conversationDao.id");

        try {
            FileWriter fIdw = new FileWriter(fId);
            fIdw.write(nextID + "\n");
            fIdw.flush();
            fIdw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
