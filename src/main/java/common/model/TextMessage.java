package common.model;

public class TextMessage extends Message {
    public TextMessage(User sender, String content) {
        super("text", sender, content);
    }

    public TextMessage(long timestamp, User sender, String content) {
        super("text", timestamp, sender, content);
    }
}
