package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.ConnectingFailedViewState;

/**
 * Connection Failed State. The application enters this state when it failed to connect to a user.
 * <p>
 * After state(s) possible : Initial
 * Before state(s) possible : Connecting
 */
public class ConnectionFailedState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        System.out.println("Connection failed!");

        gui.setCurrentViewState(new ConnectingFailedViewState(gui));

    }
}
