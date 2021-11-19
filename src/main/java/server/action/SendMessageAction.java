package server.action;

import common.model.Message;
import common.model.TextMessage;
import common.model.User;
import common.utils.ConnectionState;
import server.MainServer;

public class SendMessageAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection) {
        if (currentConnection.getCurrentConversation() != null) {
            Message newMessage = null;

            String[] arguments = currentConnection.getCurrentArguments(5);

            String type = arguments[1];
            String timestamp = arguments[2];
            String sender = arguments[3];
            String value = arguments[4];

            User senderUser = MainServer.userDao.searchByUsername(sender);

            if (type.equals("text") && senderUser != null) {
                newMessage = new TextMessage(Long.parseLong(timestamp), senderUser, value);
            }

            if (newMessage != null)
                currentConnection.getCurrentConversation().addMessage(newMessage);

            // Making the confirmation command
            if (currentConnection.getCurrentUser() != null) {
                String confirmMessage = "confirmMessage:" + type + ":" + timestamp + ":" + currentConnection.getCurrentUser().getUsername() + ":" + value;

                // Sending the confirmation command to the sender.
                currentConnection.sendSocketMessage(confirmMessage);

                // Sending the confirmation command to the other if he's connected
                User otherUser;
                if ((otherUser = currentConnection.getCurrentConversation().getOtherUser(currentConnection.getCurrentUser())) != null) {
                    if (currentConnection.getCurrentConversation().getIsInRoom(otherUser)) {
                        otherUser.sendSocketMessage(confirmMessage);
                    }
                }
            }
        } // if currentConversation != null
    }
}
