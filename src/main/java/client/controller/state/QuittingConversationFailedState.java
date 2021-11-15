package client.controller.state;

import client.controller.Controller;

/**
 * Quitting Conversation Failed State. Failed to quit a conversation.
 *
 * After state(s) possible : Conversation Joined
 * Before state(s) possible : Quitting Conversation
 */
public class QuittingConversationFailedState implements State {
    @Override
    public void run(Controller c) {
        System.out.println("Quitting failed!");
        c.setCurrentState(c.conversationJoinedState);
    }
}
