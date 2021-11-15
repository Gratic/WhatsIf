package common.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class Message {
    protected String type;
    private long timestamp;
    protected User sender;
    protected String value;

    public Message(String type, User sender, String value) {
        this.type = type;
        this.timestamp = LocalDateTime.now(ZoneId.systemDefault()).toEpochSecond(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
        this.sender = sender;
        this.value = value;
    }

    public Message(String type, long timestamp, User sender, String value) {
        this.type = type;
        this.timestamp = timestamp;
        this.sender = sender;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return timestamp == message.timestamp && type.equals(message.type) && sender.equals(message.sender) && value.equals(message.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timestamp, sender);
    }

    @Override
    public String toString() {
        return "sendMessage:" + type + ':' + timestamp + ':' + sender + ':' + value;
    }
}
