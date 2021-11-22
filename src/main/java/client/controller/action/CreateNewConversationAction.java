package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.ConversationOpenedViewState;
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
            Long id = Long.parseLong(arguments[2]);
            Conversation conversation = new Conversation(id);
            controller.getConversationsOfUser().put(id,conversation);
            currentConnection.setCurrentConversation(conversation);
            controller.gui.setCurrentViewState(new ConversationOpenedViewState(gui, controller));

        }else
        {
            System.out.println("Impossible to create a new conversation");
        }

    }
}
