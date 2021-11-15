package server.dao;

import common.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao manages Users.
 */
public class UserDao {
    Map<String, User> users;

    public UserDao() {
        users = new HashMap<>();
    }

    /**
     * Creates a new user.
     *
     * @param newUser the new user
     * @return true if created, else false.
     */
    public boolean create(User newUser) {
        if (users.containsKey(newUser.getUsername())) {
            return false;
        }

        users.put(newUser.getUsername(), newUser);
        return true;
    }

    /**
     * Retrieve a user by its username.
     *
     * @param username the username
     * @return the user which has this username
     */
    public User searchByUsername(String username) {
        return users.get(username);
    }

    /**
     * Return true if a user is connected.
     * Else false.
     *
     * @param username the username
     * @return true if the user is connected, else false.
     */
    public boolean isConnected(String username) {
        if (users.containsKey(username)) {
            return users.get(username).getConnected();
        } else
            return false;
    }
}
