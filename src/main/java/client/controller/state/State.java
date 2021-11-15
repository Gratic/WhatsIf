package client.controller.state;

import client.controller.Controller;

import java.io.IOException;

public interface State {

    void run(Controller c) throws IOException;
}
