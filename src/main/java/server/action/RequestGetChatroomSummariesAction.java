package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;
import java.util.List;

/**
 * In charge of sending the summaries of conversations to a user.
 *
 * If status is 0 everything is okay.
 * If status is 1 their is no conversation.
 */
public class RequestGetChatroomSummariesAction implements Action {

    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        String caller = arguments[1];

        List<Conversation> conversations = MainServer.conversationDao.searchConversationThatIncludeUsername(caller);

        if (conversations.size() == 0) {
            commandSender.sendChatroomSummaries(1, conversations);
        } else {
            commandSender.sendChatroomSummaries(0, conversations);
        }
    }
}
