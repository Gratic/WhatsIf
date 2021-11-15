package client.controller.state;

import client.controller.Controller;

import java.io.IOException;

public class ConnectionFailedState implements State {
    @Override
    public void run(Controller c) throws IOException {
        System.out.println("Connection failed!");
        c.setCurrentState(c.initState);
    }
}
