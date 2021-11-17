package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

public class AskUserConversationState implements State{
    private String otherUser = null;
    @Override
    public void run(Controller c, Gui gui) {


        if(otherUser.equals("/quit"))
        {
            c.setCurrentState(c.terminationState);
        }
        else
        {
            c.getCurrentUser().sendSocketMessage("requestJoinChatroom:" + otherUser);
            System.out.println(otherUser);

            c.setCurrentState(c.joiningConversationState);
        }
    }

    public String getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(String otherUser) {
        this.otherUser = otherUser;
    }
}
