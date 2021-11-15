package client.controller.state;

import client.controller.Controller;

import java.io.IOException;

public class JoiningConversationState implements State {
    @Override
    public void run(Controller c) throws IOException {
        String message = c.receiveSocketLine();

        System.out.println(message);

        String[] arguments = message.split(":");

        if (arguments[0] != null && arguments[0].equals("confirmJoinChatroom") && arguments[2].equals("0")) {
            // Connection success
            c.setCurrentState(c.conversationJoinedState);
        } else {
            // Connection failed
            c.setCurrentState(c.joiningConversationFailedState);
        }
    }
}
