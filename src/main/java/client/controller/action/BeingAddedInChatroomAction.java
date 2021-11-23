package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.model.Conversation;
import common.utils.ConnectionState;

import java.io.IOException;
import java.util.Map;

public class BeingAddedInChatroomAction implements Action{
    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();
        int code =Integer.parseInt(arguments[1]);
        System.out.println("code : "+code);
        if(code==0)
        {
            Long convId = Long.parseLong(arguments[2]);
            Conversation conv = new Conversation(convId);
            controller.getConversationsOfUser().put(convId,conv);
            gui.getUserConnectedViewState().showConversations();
        }
    }
}
