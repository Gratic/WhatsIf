package server.action;

import common.command.CommandSender;
import common.utils.ConnectionState;

public class QuitChatroomAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) {
        if (currentConnection.getCurrentConversation() != null) {
            currentConnection.setCurrentConversation(null);
            currentConnection.sendSocketMessage("confirmQuitChatroom");
        }
    }
}
