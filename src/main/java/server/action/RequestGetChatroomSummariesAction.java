package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;
import java.util.List;

public class RequestGetChatroomSummariesAction implements Action {

    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        String caller = arguments[1];

        List<Conversation> conversations = MainServer.conversationDao.searchConversationThatIncludeUsername(caller);

        if(conversations.size() == 0)
        {
            commandSender.sendChatroomSummaries(1, conversations);
        }
        else
        {
            commandSender.sendChatroomSummaries(0, conversations);
        }
    }
}
