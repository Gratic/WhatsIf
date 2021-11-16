package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

public interface State {

    void run(Controller c, Gui gui);

    default void connectingButtonClick(Controller controller, String username, String ip, int port){
        controller.askUserLoginState.setUsername(username);
        controller.askUserLoginState.setIp(ip);
        controller.askUserLoginState.setPort(port);
        controller.setCurrentState(controller.askUserLoginState);

    }
}


