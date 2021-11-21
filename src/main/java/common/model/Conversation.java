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

    private final List<String> usernames;
    private final List<Message> messages;

    public Conversation(long id)
    {
        this.id = id;

        messages = Collections.synchronizedList(new ArrayList<>());
        usernames = Collections.synchronizedList(new ArrayList<>());
    }

    public Conversation(long id, String user1, String user2) {
        this.id = id;

        messages = Collections.synchronizedList(new ArrayList<>());
        usernames = Collections.synchronizedList(new ArrayList<>());
        addUsername(user1);
        addUsername(user2);
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

    public long getId()
    {
        return id;
    }

    public int numberOfParticipants()
    {
        return usernames.size();
    }
}
