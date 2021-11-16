package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

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
        Scanner sc = new Scanner(System.in);

        System.out.println("Talk with who ? Or quit using /quit");
        String input = sc.nextLine();

        if (input.equals("/quit")) {
            c.setCurrentState(c.terminationState);
        } else {
            c.getCurrentUser().sendSocketMessage("requestJoinChatroom:" + input);

            c.setCurrentState(c.joiningConversationState);
        }

    }
}
