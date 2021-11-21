package server.action;

import common.utils.ConnectionState;

public class QuitChatroomAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection) {
        if (currentConnection.getCurrentConversation() != null) {
            currentConnection.setCurrentConversation(null);
            currentConnection.sendSocketMessage("confirmQuitChatroom");
        }
    }
}
