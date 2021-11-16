package server.action;

import server.ConnectionState;

public class DisconnectAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection) {
        if (currentConnection.getCurrentUser() != null) {
            currentConnection.getCurrentUser().sendSocketMessage("confirmDisconnect");
            currentConnection.getCurrentUser().setConnected(false);
            currentConnection.getCurrentUser().closeSocket();
            currentConnection.setAlive(false);
        }
    }
}
