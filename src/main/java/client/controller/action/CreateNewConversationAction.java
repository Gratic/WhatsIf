package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.model.Conversation;
import common.utils.ConnectionState;

import java.io.IOException;

public class CreateNewConversationAction implements Action{

    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();
        int code = Integer.parseInt(arguments[1]);
        if(code==0)
        {
            int id = Integer.parseInt(arguments[2]);
            Conversation conversation = new Conversation(id);
        }else
        {
            System.out.println("Impossible to create a new conversation");
        }

    }
}
