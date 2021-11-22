package common.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a chat room.
 */
public class Conversation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String nom;

    private final List<String> usernames;
    private final List<Message> messages;

    public Conversation(long id)
    {
        this.id = id;
        this.nom = nom;

        messages = Collections.synchronizedList(new ArrayList<>());
        usernames = Collections.synchronizedList(new ArrayList<>());
    }

    public Conversation(long id, List<String> usernames) {
        this.id = id;

        messages = Collections.synchronizedList(new ArrayList<>());
        this.usernames = Collections.synchronizedList(new ArrayList<>());

        for(String username : usernames)
        {
            addUsername(username);
        }
    }

    public String getUsername(int index)
    {
        return usernames.get(index);
    }

    public void addUsername(String username)
    {
        if(!usernames.contains(username))
        {
            usernames.add(username);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * Get the other participant, other than u.
     *
     * @param u a user
     * @return User, the other user.
     */
    public List<String> getOtherUsers(String u) {
        List<String> result = new ArrayList<>();
        for(String username : usernames)
        {
            if(!username.equals(u))
            {
                result.add(username);
            }
        }

        return result;
    }

    public String generateNom()
    {
        return String.join(",", usernames);
    }

    public String getNom()
    {
        nom = generateNom();
        return nom;
    }

    public long getId()
    {
        return id;
    }

    public int numberOfParticipants()
    {
        return usernames.size();
    }
}
