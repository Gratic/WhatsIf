package client.controller.state;

import client.controller.Controller;
import common.model.User;

import java.io.IOException;

public class ConnectingState implements State {
    @Override
    public void run(Controller c) throws IOException {
        String message = c.receiveSocketLine();

        String[] arguments = message.split(":");

        if (arguments[0] != null && arguments[0].equals("confirmConnection") && arguments[2].equals("0")) {
            // Connection success
            c.setCurrentUser(new User(arguments[1], c.getSocket()));
            c.getCurrentUser().setConnected(true);

            c.setCurrentState(c.userConnectedState);
        } else {
            // Connection failed
            c.setCurrentUser(null);
            c.setCurrentState(c.connectionFailedState);
        }
    }
}
