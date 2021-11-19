package common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a chat room.
 */
public class Conversation implements Serializable {
    private User user1;
    private User user2;

    private final Map<User, Boolean> isInRoom;

    private List<Message> messages;

    public Conversation(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;

        isInRoom = new HashMap<>();
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

    public void setMessages(List<Message> messages) {
        this.messages = messages;
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

    public void sendMessage(TextMessage message)
    {
        user1.sendSocketMessage(message.toString());
    }
}
