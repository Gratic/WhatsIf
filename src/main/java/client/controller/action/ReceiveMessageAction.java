package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.model.Conversation;
import common.model.Message;
import common.model.TextMessage;
import common.utils.ConnectionState;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ReceiveMessageAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {

        String[] arguments = currentConnection.getCurrentArguments(7);

        String conversationId = arguments[1];
        String messageHash = arguments[2];
        String type = arguments[3];
        String timestamp = arguments[4];
        String sender = arguments[5];
        String value = arguments[6];

        long convId = Long.parseLong(conversationId);
        int hash = Integer.parseInt(messageHash);
        Conversation conversation = controller.getConversationsOfUser().get(convId);


        if (type.equals("text")) {
            Message newMessage = new TextMessage(convId, Long.parseLong(timestamp), sender, value);
            if (newMessage.hashCode() == hash) {
                controller.getConversationsOfUser().get(convId).addMessage(newMessage);
            }
        }

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(timestamp), 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("k'h'm");
        String date = localDateTime.format(formatter);
        String receivedMessageContent = (sender + ":" + date + ": " + value);

        if (gui.getCurrentState() == gui.getConversationOpenedViewState() && controller.getCurrentConnection().getCurrentConversation().getId() == Long.parseLong(conversationId)) {
            if (type.equals("text")) {


                gui.getConversationOpenedViewState().receiveMessage(receivedMessageContent);

            } else {
                System.out.println("WARNING: the message received is a type unknown. (" + type + ")");
            }

        }
    }
}
