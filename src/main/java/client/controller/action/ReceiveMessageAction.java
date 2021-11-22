package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.utils.ConnectionState;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ReceiveMessageAction implements Action{
    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {

        String command = currentConnection.receiveSocketLine();

        String[] arguments = command.split(":");
        int conversationId = Integer.parseInt(arguments[1]);

        String type = arguments[2];
        long timestamp = Long.parseLong(arguments[3]);
        String sender = arguments[4];
        String value = arguments[5];

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("k'h'm");
        String date = localDateTime.format(formatter);
        String receivedMessageContent = (sender + ":" + date + ": " + value);

        if (type.equals("text")) {

            gui.getConversationOpenedViewState().receiveMessage(receivedMessageContent);

        } else {
            System.out.println("WARNING: the message received is a type unknown. (" + type + ")");
        }
    }
}
