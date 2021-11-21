package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

public class AskUserConversationState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        String otherUser = c.getUsernameOtherUser();

        if (otherUser.equals("/quit")) {
            c.setCurrentState(c.terminationState);
        } else {
            c.getCurrentUser().sendSocketMessage("requestJoinChatroom:" + otherUser);
            System.out.println(otherUser);

            c.setCurrentState(c.joiningConversationState);
        }
    }


}
