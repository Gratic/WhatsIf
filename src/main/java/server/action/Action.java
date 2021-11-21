package server.action;

import common.utils.ConnectionState;

import java.io.IOException;

public interface Action {
    void execute(ConnectionState currentConnection) throws IOException;
}
