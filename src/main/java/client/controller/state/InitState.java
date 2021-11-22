package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.InitViewState;

/**
 * Initial state. The application starts in this state.
 * <p>
 * After state(s) possible : AskUserLoginState
 */
public class InitState implements State {


    @Override
    public void run(Controller c, Gui gui) {
        InitViewState initViewState = new InitViewState(gui);
        gui.setInitViewState(initViewState);
        gui.setCurrentViewState(initViewState);
    }
}
