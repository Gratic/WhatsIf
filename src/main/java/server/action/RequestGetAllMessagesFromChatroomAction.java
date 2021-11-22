package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;

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
