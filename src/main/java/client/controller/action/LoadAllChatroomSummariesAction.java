package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;

import java.io.IOException;

public class LoadAllChatroomSummariesAction implements Action{
    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        int code = Integer.parseInt(arguments[1]);
        if(code==0){


            for(int i = 1; i<arguments.length;i++) {
                if((i-1)%3==0)
                {
                    CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
                    Long id = Long.parseLong(arguments[i]);
                    if(!controller.getConversationsOfUser().containsKey(id)){
                        Conversation conv = new Conversation(id);
                        controller.getConversationsOfUser().put(id,conv);
                        commandSender.sendGetAllMessagesFromChatroom(id);

                    }else{
                        Conversation conv = controller.getConversationsOfUser().get(id);
                        int hash = conv.getMessages().get(conv.getMessages().size()-1).hashCode();
                        commandSender.sendAllMessagesFromChatroomSinceHash(conv, hash);
                    }
                }
            }

            gui.getUserConnectedViewState().showConversations();
        }else if(code==1){
            System.out.println("no conversation");
        }else if(code==2)
        {
            System.out.println("error");
        }

    }
}
