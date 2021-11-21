package server.action;

import common.model.User;
import common.utils.ConnectionState;
import common.utils.Pair;
import server.MainServer;

import java.io.IOException;

public class RequestJoinChatroom implements Action {
    @Override
    public void execute(ConnectionState currentConnection) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();
        String username = arguments[1];

        System.out.println("requestJoinChatroom: " + username);

        if (MainServer.userDao.isConnected(username)) {
            User otherUser = MainServer.userDao.searchByUsername(username);
            MainServer.conversationDao.create(currentConnection.getCurrentUser().getUsername(), otherUser.getUsername());

            currentConnection.setCurrentConversation(MainServer.conversationDao.searchConversationWithBothUsers(currentConnection.getCurrentUser(), otherUser));

            currentConnection.sendSocketMessage("confirmJoinChatroom:" + username + ":0");


            System.out.println("confirmJoinChatroom: " + username + ":0");
        } else {
            currentConnection.sendSocketMessage("confirmJoinChatroom:" + username + ":1");
            System.out.println("confirmJoinChatroom: " + username + ":1");
        }
    }
}
