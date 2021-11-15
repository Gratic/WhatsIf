package client.controller.state;

import client.controller.Controller;

import java.util.Scanner;

/**
 * User Connected State. The user is connected.
 * <p>
 * After state(s) possible : Joining Conversation
 * Before state(s) possible : Connecting, Joining Conversation Failed, Quitting Conversation
 */
public class UserConnectedState implements State {
    @Override
    public void run(Controller c) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Talk with who ?");
        String username = sc.nextLine();

        c.getCurrentUser().sendSocketMessage("requestJoinChatroom:" + username);

        c.setCurrentState(c.joiningConversationState);
    }
}
