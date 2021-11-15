package client.controller.state;

import client.controller.Controller;

public class JoiningConversationFailedState implements State {
    @Override
    public void run(Controller c) {
        System.out.println("Joining chatroom failed!");
        c.setCurrentState(c.userConnectedState);
    }
}
