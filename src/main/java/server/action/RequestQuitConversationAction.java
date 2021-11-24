package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;

/**
 * In charge of deleting a user from a conversation.
 *
 * If status is 0 everything is okay.
 * If status is 1 the user is not in the conversation.
 * If status is 2 the conversation doesn't exist.
 */
public class RequestQuitConversationAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        long convId = Long.parseLong(arguments[1]);
        String username = arguments[2];

        Conversation conv = MainServer.conversationDao.searchConversationWithId(convId);
        if (conv != null) {
            if (conv.userIsInConversation(username)) {
                conv.removeUsername(username);
                MainServer.conversationDao.persist(conv);

                commandSender.sendConfirmQuitConversation(0, conv, username);
            } else {
                commandSender.sendConfirmQuitConversation(1, null, null);
            }
        } else {
            commandSender.sendConfirmQuitConversation(2, null, null);
        }
    }
}
