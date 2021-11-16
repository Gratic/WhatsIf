package server.action;

import server.ConnectionState;

public interface Action {
    void execute(ConnectionState currentConnection);
}
