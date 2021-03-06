package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.model.Conversation;
import common.utils.ConnectionState;

import java.io.IOException;
/**
 * Action called when the current user creates a new chatroom
 * Add the conversation to the list of conversations of the current user
 */
public class CreateNewConversationAction implements Action {

    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();
        int code = Integer.parseInt(arguments[1]);
        if (code == 0) {
            Long id = Long.parseLong(arguments[2]);
            Conversation conversation = new Conversation(id);
            controller.getConversationsOfUser().put(id, conversation);
            String name = gui.getUserConnectedViewState().getAskConversationPanel().getName()+","+controller.getCurrentUser().getUsername();
            controller.getConversationsNameOfUser().put(id, name);

            controller.changeFocusConversation(conversation);
        } else {
            System.out.println("Impossible to create a new conversation");
        }

    }
}
