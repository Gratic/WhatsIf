package common.model;

public class TextMessage extends Message {
    public TextMessage(long conversationId, String sender, String content) {
        super(conversationId, "text", sender, content);
    }

    public TextMessage(long conversationId, long timestamp, String sender, String content) {
        super(conversationId, "text", timestamp, sender, content);
    }

}
