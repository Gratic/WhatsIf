package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import common.model.Conversation;
import common.model.User;

import java.io.IOException;

/**
 * Joining Conversation State. Trying to connect to a conversation.
 * <p>
 * After state(s) possible : Conversation Joined, Joining Conversation Failed
 * Before state(s) possible : User Connected
 */
public class JoiningConversationState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        try {
            String message = c.getCurrentUser().receiveSocketLine();

            String[] arguments = message.split(":");

            String command = arguments[0];
            String username = arguments[1];
            String returnValue = arguments[2];
            if (command != null && command.equals("confirmJoinChatroom") && returnValue.equals("0")) {
                // Connection success
                System.out.println("You started a conversation with " + username + ". Be friendly ;) !");
                c.setCurrentState(c.conversationJoinedState);
            } else {
                // Connection failed
                c.setCurrentState(c.joiningConversationFailedState);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            c.setCurrentState(c.joiningConversationFailedState);
        }

    }
}
