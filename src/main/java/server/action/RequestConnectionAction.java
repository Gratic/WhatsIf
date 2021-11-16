package server.action;

import common.model.User;
import server.ConnectionState;
import server.MainServer;

public class RequestConnectionAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection) {
        String[] arguments = currentConnection.getCurrentArguments();
        String username = arguments[1];
        if (!username.equals("")) {
            System.out.println("requestConnection: " + username);

            currentConnection.setCurrentUser(new User(username, currentConnection.getCurrentSocket()));
            currentConnection.getCurrentUser().setConnected(true);

            if (MainServer.userDao.create(currentConnection.getCurrentUser())) {
                System.out.println("connection successful");
                currentConnection.sendSocketMessage("confirmConnection:" + username + ":0");
            } else {
                currentConnection.setCurrentUser(null);
                System.out.println("connection failed");
                currentConnection.sendSocketMessage("confirmConnection:" + username + ":1");
            }
        }
    }
}
