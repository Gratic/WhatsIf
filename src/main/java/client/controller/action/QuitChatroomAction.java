package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.model.Conversation;
import common.utils.ConnectionState;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuitChatroomAction implements Action{
    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();
        int code =Integer.parseInt(arguments[1]);
        if(code==0)
        {
            Long convId = Long.parseLong(arguments[2]);
            Map<Long, Conversation> conversationMap = controller.getConversationsOfUser();
            conversationMap.remove(convId);
            controller.setCurrentState(controller.userConnectedState);
        }
    }
}
