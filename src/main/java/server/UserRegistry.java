package server;

import common.User;

import java.util.HashMap;
import java.util.Map;

public class UserRegistry {
    Map<String, User> users;

    public UserRegistry() {
        users = new HashMap<>();
    }

    public boolean addUser(User newUser) {
        if (users.containsKey(newUser.getUsername())) {
            return false;
        }

        users.put(newUser.getUsername(), newUser);
        return true;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean isConnected(String username) {
        if (users.containsKey(username)) {
            return users.get(username).getConnected();
        } else
            return false;
    }
}
