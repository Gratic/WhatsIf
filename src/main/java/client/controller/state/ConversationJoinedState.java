package client.controller.state;

import client.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * Conversation Joined State. Successfully joined a conversation. The current user is able to receive message and to send them.
 *
 * After state(s) possible : Quitting Conversation
 * Before state(s) possible : Joining Conversation, Quitting Conversation Failed
 */
public class ConversationJoinedState implements State {
    @Override
    public void run(Controller c) {
        Scanner sc = new Scanner(System.in);
        boolean continueChatting = true;

        try {
            while (continueChatting) {
                while (c.getCurrentUser().socketIncomingData()) {
                    String message = c.getCurrentUser().receiveSocketLine();

                    System.out.println(message);
                }


                String userAction = sc.nextLine();

                switch (userAction) {
                    case "/reload" -> {
                    }
                    case "/quit" -> continueChatting = false;
                    default -> c.getCurrentUser().sendSocketMessage("sendMessage:text:" + userAction);
                }
            }

            c.getCurrentUser().sendSocketMessage("quitChatroom");
            c.setCurrentState(c.quittingConversationState);
        } catch (IOException e) {
            e.printStackTrace();
            c.setCurrentState(c.joiningConversationFailedState);
        }
    }
}
