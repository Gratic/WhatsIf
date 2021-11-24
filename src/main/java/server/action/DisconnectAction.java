package server.action;

import common.command.CommandSender;
import common.utils.ConnectionState;

/**
 * In charge of the disconnection request from the user.
 *
 * Close everything and put the user as disconnected.
 *
 * Confirm the disconnection.
 */
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
