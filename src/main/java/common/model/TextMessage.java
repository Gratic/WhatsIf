package common.model;


public class TextMessage extends Message {

    private String content;

    public TextMessage(User user, String content) {
        super(user);
        this.content = content;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
