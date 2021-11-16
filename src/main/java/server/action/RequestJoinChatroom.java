package server.action;

import common.model.User;
import server.ConnectionState;
import server.MainServer;

public class RequestJoinChatroom implements Action {
    @Override
    public void execute(ConnectionState currentConnection) {
        String[] arguments = currentConnection.getCurrentArguments();
        String username = arguments[1];

        System.out.println("requestJoinChatroom: " + username);

        if (MainServer.userDao.isConnected(username)) {
            User otherUser = MainServer.userDao.searchByUsername(username);
            MainServer.conversationDao.create(currentConnection.getCurrentUser(), otherUser);

            currentConnection.setCurrentConversation(MainServer.conversationDao.searchConversationWithBothUsers(currentConnection.getCurrentUser(), otherUser));

            currentConnection.getCurrentConversation().setIsInRoom(currentConnection.getCurrentUser(), true);

            currentConnection.sendSocketMessage("confirmJoinChatroom:" + username + ":0");
            System.out.println("confirmJoinChatroom: " + username + ":0");
        } else {
            currentConnection.sendSocketMessage("confirmJoinChatroom:" + username + ":1");
            System.out.println("confirmJoinChatroom: " + username + ":1");
        }
    }
}
