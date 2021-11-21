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

    private transient User user1;
    private transient User user2;

    private final List<String> usernames;
    private transient final Map<User, Boolean> isInRoom;

    private final List<Message> messages;

    public Conversation(long id)
    {
        this.id = id;
        this.user1 = null;
        this.user2 = null;

        messages = Collections.synchronizedList(new ArrayList<>());
        usernames = Collections.synchronizedList(new ArrayList<>());
        isInRoom = new HashMap<>();
    }

    public Conversation(long id, User user1, User user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;

        isInRoom = new HashMap<>();

        messages = Collections.synchronizedList(new ArrayList<>());
        usernames = Collections.synchronizedList(new ArrayList<>());
        usernames.add(user1.getUsername());
        usernames.add(user2.getUsername());
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public boolean getIsInRoom(User u) {
        return isInRoom.getOrDefault(u, false);
    }

    public void setIsInRoom(User u, boolean value) {
        isInRoom.put(u, value);
    }

    /**
     * Get the other participant, other than u.
     *
     * @param u a user
     * @return User, the other user.
     */
    public User getOtherUser(User u) {
        if (user1.equals(u)) {
            return user2;
        } else if (user2.equals(u)) {
            return user1;
        }

        return null;
    }

    public long getId()
    {
        return id;
    }

    public List<String> getUsernames()
    {return usernames;}

    public void addUsername(String username)
    {
        usernames.add(username);
    }

    public void sendMessage(TextMessage message)
    {
        user1.sendSocketMessage(message.toString());
    }
}
