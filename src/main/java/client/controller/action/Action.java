package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.utils.ConnectionState;

import java.io.IOException;

public interface Action {
    void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException;
}

