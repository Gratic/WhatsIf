package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import common.model.User;

/**
 * Termination State. Last state before termination of the application in normal execution.
 * <p>
 * After state(s) possible : None
 * Before state(s) possible : Initial, User Connected
 */
public class TerminationState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        System.out.println("disconnected");
        User user = c.getCurrentUser();
        if (user != null) {
            user.closeSocket();
        }

        c.closeSocket();
        c.setCurrentState(c.initState);
    }
}
