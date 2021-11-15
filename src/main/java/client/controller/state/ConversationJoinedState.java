package client.controller.state;

import client.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class ConversationJoinedState implements State {
    @Override
    public void run(Controller c) throws IOException {
        Scanner sc = new Scanner(System.in);

        boolean continueChatting = true;

        while (continueChatting) {
            while (c.socketIncomingData()) {
                String message = c.receiveSocketLine();

                System.out.println(message);
            }


            String userAction = sc.nextLine();

            switch (userAction) {
                case "/reload" -> {
                }
                case "/quit" -> continueChatting = false;
                default -> c.sendSocketMessage("sendMessage:text:" + userAction);
            }
        }

        c.sendSocketMessage("quitChatroom");
        c.setCurrentState(c.quittingConversationState);
    }
}
