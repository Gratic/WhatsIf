package client.controller.state;

import client.controller.Controller;

import java.io.IOException;

public class QuittingConversationState implements State {
    @Override
    public void run(Controller c) throws IOException {
        String message = c.receiveSocketLine();

        String[] arguments = message.split(":");

        if (arguments[0] != null && arguments[0].equals("quitChatroom")) {
            // Connection success
            c.setCurrentState(c.userConnectedState);
        } else {
            // Connection failed
            c.setCurrentState(c.quittingConversationFailedState);
        }
    }
}
