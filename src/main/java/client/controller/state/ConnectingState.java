package client.controller.state;

import client.controller.Controller;
import common.model.User;

import java.io.IOException;

/**
 * Connecting State. The application enters this state when trying to connect to a user.
 *
 * After state(s) possible : User Connected, Connection Failed
 * Before state(s) possible : Initial
 */
public class ConnectingState implements State {
    @Override
    public void run(Controller c) {
        try {
            String message = c.receiveSocketLine();
            String[] arguments = message.split(":");

            String command = arguments[0];
            String returnValue = arguments[2];

            if (command != null && command.equals("confirmConnection") && returnValue.equals("0")) {
                // Connection success
                c.setCurrentUser(new User(arguments[1], c.getSocket()));
                c.getCurrentUser().setConnected(true);

                c.setCurrentState(c.userConnectedState);
            } else {
                // Connection failed
                c.setCurrentUser(null);
                c.setCurrentState(c.connectionFailedState);
            }
        } catch (IOException e) {
            e.printStackTrace();
            c.setCurrentState(c.initState);
        }
    }
}
