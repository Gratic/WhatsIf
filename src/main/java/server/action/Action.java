package server.action;

import common.command.CommandSender;
import common.utils.ConnectionState;

import java.io.IOException;

public interface Action {
    void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException;
}
