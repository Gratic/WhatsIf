package server.action;

import common.command.CommandSender;
import common.model.User;
import common.utils.ConnectionState;
import server.MainServer;

public class RequestConnectionAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) {
        String[] arguments = currentConnection.getCurrentArguments();
        String username = arguments[1];
        if (!username.equals("")) {
            System.out.println("connect: " + username);

            if(MainServer.userDao.exists(username))
            {
                if(MainServer.userDao.isConnected(username))
                {
                    currentConnection.setCurrentUser(null);
                    System.out.println("connection failed");
                    commandSender.sendConfirmConnectedToUser(username, 1);
                }
                else
                {
                    currentConnection.setCurrentUser(MainServer.userDao.searchByUsername(username));
                    currentConnection.getCurrentUser().setConnected(true);
                    System.out.println("connection successful");
                    commandSender.sendConfirmConnectedToUser(username, 0);
                }
            }
            else
            {
                MainServer.userDao.create(new User(username, currentConnection.getCurrentSocket()));
                currentConnection.setCurrentUser(MainServer.userDao.searchByUsername(username));
                currentConnection.getCurrentUser().setConnected(true);
                System.out.println("connection successful");
                commandSender.sendConfirmConnectedToUser(username, 0);
                MainServer.userDao.persist(MainServer.userDao.searchByUsername(username));
            }
        }
    }
}
