package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;

public class RequestAddUserToChatroomAction implements Action{
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        long convId = Long.parseLong(arguments[1]);
        String username = arguments[2];

        Conversation conv = MainServer.conversationDao.searchConversationWithId(convId);

        if(conv != null)
        {
            if(MainServer.userDao.exists(username))
            {
                if(!conv.userIsInConversation(username))
                {
                    conv.addUsername(username);
                    MainServer.conversationDao.persist(conv);

                    commandSender.sendConfirmAddUserToChatroom(0, conv, username);
                }
                else
                {
                    commandSender.sendConfirmAddUserToChatroom(1, null, null);
                }
            }
            else
            {
                commandSender.sendConfirmAddUserToChatroom(2, null, null);
            }
        }
        else
        {
            commandSender.sendConfirmAddUserToChatroom(3, null, null);
        }
    }
}
