package common;

public class User {
    private String username;
    private Boolean isConnected;

    public User(String username)
    {
        this.username = username;
        this.isConnected=true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }
}
