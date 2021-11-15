package server.dao;

import common.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    Map<String, User> users;

    public UserDao() {
        users = new HashMap<>();
    }

    public boolean create(User newUser) {
        if (users.containsKey(newUser.getUsername())) {
            return false;
        }

        users.put(newUser.getUsername(), newUser);
        return true;
    }

    public User searchByUsername(String username) {
        return users.get(username);
    }

    public boolean isConnected(String username) {
        if (users.containsKey(username)) {
            return users.get(username).getConnected();
        } else
            return false;
    }
}
