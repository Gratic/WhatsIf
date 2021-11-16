package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

import java.io.IOException;

/**
 * Quitting Conversation State. Trying to quit a conversation.
 * <p>
 * After state(s) possible : User Connected, Quitting Conversation Failed
 * Before state(s) possible : Conversation Joined
 */
public class QuittingConversationState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        try {
            String message = c.getCurrentUser().receiveSocketLine();

            String[] arguments = message.split(":");

            String command = arguments[0];
            if (command != null && command.equals("confirmQuitChatroom")) {
                // Connection success
                c.setCurrentState(c.userConnectedState);
            } else {
                // Connection failed
                c.setCurrentState(c.quittingConversationFailedState);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            c.setCurrentState(c.quittingConversationFailedState);
        }
    }
}
