package client.controller.state;

import client.controller.Controller;

import java.io.IOException;

/**
 * Joining Conversation State. Trying to connect to a conversation.
 *
 * After state(s) possible : Conversation Joined, Joining Conversation Failed
 * Before state(s) possible : User Connected
 */
public class JoiningConversationState implements State {
    @Override
    public void run(Controller c) {
        try {
            String message = c.getCurrentUser().receiveSocketLine();

            System.out.println(message);

            String[] arguments = message.split(":");

            String command = arguments[0];
            String returnValue = arguments[2];
            if (command != null && command.equals("confirmJoinChatroom") && returnValue.equals("0")) {
                // Connection success
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
