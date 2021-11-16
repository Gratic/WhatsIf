package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Initial state. The application starts in this state.
 * <p>
 * After state(s) possible : AskUserLoginState
 */
public class InitState implements State {


    @Override
    public void run(Controller c, Gui gui) {

    }
}
