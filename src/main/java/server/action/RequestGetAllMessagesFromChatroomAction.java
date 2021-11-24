package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;

/**
 * In charge of sending all messages from a conversation to a user.
 */
public class RequestGetAllMessagesFromChatroomAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        String convIdStr = arguments[1];
        long convId = Long.parseLong(convIdStr);

        Conversation conversation = MainServer.conversationDao.searchConversationWithId(convId);

        commandSender.sendAllMessagesFromChatroom(conversation);
    }
}
