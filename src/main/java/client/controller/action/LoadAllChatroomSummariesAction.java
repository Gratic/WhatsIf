package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;

import java.io.IOException;

public class LoadAllChatroomSummariesAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        int code = Integer.parseInt(arguments[1]);

        if (code == 0) {
            int numberOfConversation = Integer.parseInt(arguments[2]);
            for (int i = 0; i < numberOfConversation; i++) {
                String[] parameters = arguments[i + 3].split(";");
                CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
                Long id = Long.parseLong(parameters[0]);
                int nbOfMessage = Integer.parseInt(parameters[1]);
                int hashLastMessage = Integer.parseInt(parameters[2]);
                String conversationName = parameters[3];

                if (!controller.getConversationsOfUser().containsKey(id)) {
                    Conversation conv = new Conversation(id);
                    controller.getConversationsOfUser().put(id, conv);
                    controller.getConversationsNameOfUser().put(id, conversationName);

                    commandSender.sendGetAllMessagesFromChatroom(id);

                } else {
                    Conversation conv = controller.getConversationsOfUser().get(id);
                    if (conv.getMessages().size() != nbOfMessage) {
                        int hash;
                        if (conv.getMessages().size() != 0) {
                            hash = conv.getMessages().get(conv.getMessages().size() - 1).hashCode();

                        } else {
                            hash = 0;
                        }

                        if(hash != hashLastMessage)
                            commandSender.sendGetAllMessagesFromChatroomSinceHash(conv.getId(), hash);
                    }
                }

            }

            gui.getUserConnectedViewState().showConversations();
        } else if (code == 1) {

        } else if (code == 2) {
            System.out.println("error");
        }

    }
}
