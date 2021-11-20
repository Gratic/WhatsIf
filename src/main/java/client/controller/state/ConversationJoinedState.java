package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.ConversationOpenedViewState;
import common.model.Conversation;
import common.model.TextMessage;
import common.model.User;
import common.utils.ConnectionState;
import common.utils.Pair;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Conversation Joined State. Successfully joined a conversation. The current user is able to receive message and to send them.
 * <p>
 * After state(s) possible : Quitting Conversation
 * Before state(s) possible : Joining Conversation, Quitting Conversation Failed
 */
public class ConversationJoinedState implements State {
    private String message;
    private String receivedMessageContent;
    private String userAction="coucou";
    @Override
    public void run(Controller c, Gui gui) {
      //  Scanner sc = new Scanner(System.in);
        gui.setCurrentViewState(new ConversationOpenedViewState(gui, c));
        boolean continueChatting = true;
        System.out.println("yo");


            /*
            try{
                message = c.getCurrentUser().receiveSocketLine();
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
                        c.addMessageReceived(receivedMessageContent);
                    } else {
                        System.out.println("WARNING: the message received is a type unknown. (" + type + ")");
                    }
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }

             */







/*
        try {
            while (continueChatting) {
                while (c.getCurrentUser().socketIncomingData()) {
                    message = c.getCurrentUser().receiveSocketLine();

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



                switch (userAction) {
                    case "/reload" -> {
                    }
                    case "/quit" -> continueChatting = false;
                    default -> {
                        if(userAction!=""){
                            TextMessage message = new TextMessage(c.getCurrentUser(), userAction);
                            c.getCurrentUser().sendSocketMessage(message.toString());

                            }
                        }
                }
            }

            c.getCurrentUser().sendSocketMessage("quitChatroom");
            c.setCurrentState(c.quittingConversationState);
        } catch (IOException e) {
            e.printStackTrace();
            c.setCurrentState(c.joiningConversationFailedState);
        }

*/
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public void sendMessage(Controller controller, String text)
    {
        TextMessage message = new TextMessage(controller.getCurrentUser(), userAction);
        controller.getCurrentUser().sendSocketMessage(message.toString());
        controller.addMessageSent(message);
        System.out.println("message sent : "+text);
    }

    public String getReceivedMessageContent() {
        return receivedMessageContent;
    }

    public void setReceivedMessageContent(String receivedMessageContent) {
        this.receivedMessageContent = receivedMessageContent;
    }

    public void receiveMessage(Controller controller){
        try{
            message = controller.getCurrentUser().receiveSocketLine();
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
                    setReceivedMessageContent(date + ": " + value);
                    controller.addMessageReceived(receivedMessageContent);
                } else {
                    System.out.println("WARNING: the message received is a type unknown. (" + type + ")");
                }
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
