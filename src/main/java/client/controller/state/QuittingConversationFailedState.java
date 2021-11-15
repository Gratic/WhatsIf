package client.controller.state;

import client.controller.Controller;

import java.io.IOException;

public class QuittingConversationFailedState implements State {
    @Override
    public void run(Controller c) throws IOException {
        System.out.println("Quitting failed!");
        c.setCurrentState(c.conversationJoinedState);
    }
}
