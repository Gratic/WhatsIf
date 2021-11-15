package client.controller.state;

import client.controller.Controller;
import common.model.TextMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Conversation Joined State. Successfully joined a conversation. The current user is able to receive message and to send them.
 * <p>
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

                    String[] arguments = message.split(":");

                    String command = arguments[0];
                    if (command != null && command.equals("confirmMessage")) {
                        String type = arguments[1];
                        long timestamp = Long.parseLong(arguments[2]);
                        String sender = arguments[3];
                        String value = arguments[4];

                        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("k'h'm");
                        String date = localDateTime.format(formatter);

                        if (type.equals("text")) {
                            System.out.println(sender + ":" + date + ": " + value);
                        } else {
                            System.out.println("WARNING: the message received is a type unknown. (" + type + ")");
                        }
                    }

                }

                String userAction = sc.nextLine();

                switch (userAction) {
                    case "/reload" -> {
                    }
                    case "/quit" -> continueChatting = false;
                    default -> {
                        TextMessage message = new TextMessage(c.getCurrentUser(), userAction);
                        c.getCurrentUser().sendSocketMessage(message.toString());
                    }
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
