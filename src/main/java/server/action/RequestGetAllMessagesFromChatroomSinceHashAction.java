package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;

/**
 * In charge of sending all messages from a conversation since a has to a user.
 */
public class RequestGetAllMessagesFromChatroomSinceHashAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        String convIdStr = arguments[1];
        String hashStr = arguments[2];
        long convId = Long.parseLong(convIdStr);
        int hash = Integer.parseInt(hashStr);

        Conversation conversation = MainServer.conversationDao.searchConversationWithId(convId);

        commandSender.sendAllMessagesFromChatroomSinceHash(conversation, hash);
    }
}
