package server.dao;

import common.model.Conversation;
import common.model.User;
import common.utils.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * ConversationDao manages Conversations.
 */
public class ConversationDao {
    private final Map<Pair<User, User>, Conversation> conversations;

    public ConversationDao() {
        conversations = new HashMap<>();
    }

    /**
     * Creates a new conversation.
     *
     * @param u1 a user
     * @param u2 a user
     * @return true if created, else false.
     */
    public boolean create(User u1, User u2) {
        Pair<User, User> users = User.orderTwoUser(u1, u2);

        if (conversations.containsKey(users))
            return false;
        else {
            conversations.put(users, new Conversation(users.getFirst(), users.getSecond()));
        }

        return true;
    }

    /**
     * Finds and retrieve the conversation of user u1 and user u2.
     *
     * @param u1 a user
     * @param u2 a user
     * @return a conversation, null if not existing.
     */
    public Conversation searchConversationWithBothUsers(User u1, User u2) {
        Pair<User, User> users = User.orderTwoUser(u1, u2);
        return conversations.getOrDefault(users, null);
    }
}
