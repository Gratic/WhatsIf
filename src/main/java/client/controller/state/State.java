package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

public interface State {

    void run(Controller c, Gui gui);

    default void connectingButtonClick(Controller controller){
        //controller.setCurrentState(controller.connectingState);
    }
}


