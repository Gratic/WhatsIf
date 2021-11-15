package common.model;

public class Message {
    protected User sender;

    public Message(User sender) {
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
