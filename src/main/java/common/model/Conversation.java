package common.model;

import java.util.List;

public class Conversation {
    private User user1;
    private User user2;

    private boolean isInRoomU1;
    private boolean isInRoomU2;

    private List<Message> messages;

    public Conversation(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.isInRoomU1 = false;
        this.isInRoomU2 = false;
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

    public boolean getIsInRoom(User u) {
        if (user1.equals(u)) {
            return isInRoomU1;
        } else if (user2.equals(u)) {
            return isInRoomU2;
        }

        return false;
    }

    public void setIsInRoom(User u, boolean value) {
        if (user1.equals(u)) {
            isInRoomU1 = value;
        } else if (user2.equals(u)) {
            isInRoomU2 = value;
        }
    }

    public User getOtherUser(User u) {
        if (user1.equals(u)) {
            return user2;
        } else if (user2.equals(u)) {
            return user1;
        }

        return null;
    }
}
