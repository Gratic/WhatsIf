package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

/**
 * Joining Conversation Failed State. Failed to connect to the conversation.
 * <p>
 * After state(s) possible : User Connected
 * Before state(s) possible : Joining Conversation
 */
public class JoiningConversationFailedState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        System.out.println("Joining chatroom failed!");
        c.setCurrentState(c.userConnectedState);
    }
}
