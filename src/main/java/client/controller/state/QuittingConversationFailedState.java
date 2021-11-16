package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

/**
 * Quitting Conversation Failed State. Failed to quit a conversation.
 * <p>
 * After state(s) possible : Conversation Joined
 * Before state(s) possible : Quitting Conversation
 */
public class QuittingConversationFailedState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        System.out.println("Quitting failed!");
        c.setCurrentState(c.conversationJoinedState);
    }
}
