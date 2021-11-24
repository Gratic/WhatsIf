package server.dao;

import common.model.User;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UserDao manages Users.
 *
 * Responsible for the CRUD of users.
 */
public class UserDao {
    private final Map<String, User> users;

    private final static String dataLocation = "data/server/";
    private final static String idLocation = dataLocation + "id/";
    private final static String userLocation = dataLocation + "users/";
    private static UserDao userDaoSingleton = null;

    /**
     * Singleton design pattern.
     * The first time this method is called, creates an instance.
     *
     * @return the current instance.
     */
    public static UserDao getInstance() {
        if (userDaoSingleton == null) {
            userDaoSingleton = new UserDao();
        }

        return userDaoSingleton;
    }

    private UserDao() {
        users = new ConcurrentHashMap<>();
        loadUsers();
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
        if (exists(username)) {
            return users.get(username).getConnected();
        } else
            return false;
    }

    /**
     * Returns true if the user exists, else false.
     *
     * @param username username
     * @return returns true if the user exists, else false.
     */
    public boolean exists(String username) {
        return users.containsKey(username);
    }

    /**
     * Load all users from the directory /data/server/users.
     */
    public void loadUsers() {
        File f = new File(userLocation);

        if (f.exists()) {
            for (String path : f.list()) {
                User user = loadUser(userLocation + path);
                if (user != null) {
                    user.setConnected(false);
                    users.put(user.getUsername(), user);
                }
            }
        }
    }

    /**
     * Load a user given a path.
     *
     * @param path path to a user data file.
     * @return a user.
     */
    public User loadUser(String path) {
        System.out.println(path);
        ObjectInputStream ois = null;

        User user = null;

        try {
            final FileInputStream fichierIn = new FileInputStream(path);
            ois = new ObjectInputStream(fichierIn);
            user = (User) ois.readObject();
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }

        return user;
    }

    /**
     * Persist a user.
     *
     * @param user the user to persist.
     */
    public void persist(User user) {
        File f = new File(userLocation);
        if (!f.exists()) {
            f.mkdirs();
        }

        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream(userLocation + user.getUsername() + ".conv");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(user);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Persist all users.
     */
    public void persistAll() {
        for (User user : users.values()) {
            persist(user);
        }
    }
}
