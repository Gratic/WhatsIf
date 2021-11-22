package server.action;

import common.command.CommandSender;
import common.utils.ConnectionState;

public class DisconnectAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) {
        if (currentConnection.getCurrentUser() != null) {
            commandSender.sendConfirmeDisconnect();
            currentConnection.close();
            currentConnection.setAlive(false);
        }
    }
}
