package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import common.model.TextMessage;
import common.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Termination State. Last state before termination of the application in normal execution.
 * <p>
 * After state(s) possible : None
 * Before state(s) possible : Initial, User Connected
 */
public class TerminationState implements State {
    @Override
    public void run(Controller c, Gui gui) {
        User user = c.getCurrentUser();
        if(user != null)
        {
            user.close();
        }

        c.close();
    }
}
