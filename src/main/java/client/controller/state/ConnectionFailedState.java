package client.controller.state;

import client.controller.Controller;

/**
 * Connection Failed State. The application enters this state when it failed to connect to a user.
 *
 * After state(s) possible : Initial
 * Before state(s) possible : Connecting
 */
public class ConnectionFailedState implements State {
    @Override
    public void run(Controller c) {
        System.out.println("Connection failed!");
        c.setCurrentState(c.initState);
    }
}
