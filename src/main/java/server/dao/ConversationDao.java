package server.dao;

import common.model.Conversation;
import common.model.User;
import common.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class ConversationDao {
    private final Map<Pair<User, User>, Conversation> conversations;

    public ConversationDao() {
        conversations = new HashMap<>();
    }

    public boolean create(User u1, User u2) {
        Pair<User, User> users = User.orderTwoUser(u1, u2);

        if (conversations.containsKey(users))
            return false;
        else {
            conversations.put(users, new Conversation(users.getFirst(), users.getSecond()));
        }

        return true;
    }

    public Conversation searchConversationWithBothUsers(User u1, User u2) {
        Pair<User, User> users = User.orderTwoUser(u1, u2);
        return conversations.getOrDefault(users, null);
    }
}
