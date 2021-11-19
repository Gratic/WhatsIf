package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.UserConnectedViewState;

import java.util.Scanner;

/**
 * User Connected State. The user is connected.
 * <p>
 * After state(s) possible : Joining Conversation
 * Before state(s) possible : Connecting, Joining Conversation Failed, Quitting Conversation
 */
public class UserConnectedState implements State {
    @Override
    public void run(Controller c, Gui gui) {


        gui.setCurrentViewState(new UserConnectedViewState(gui, c));

        System.out.println("Talk with who ? Or quit using /quit");
    }


}
