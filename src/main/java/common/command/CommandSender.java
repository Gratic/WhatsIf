package common.command;

import common.model.Conversation;
import common.model.Message;
import common.utils.SocketUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandSender {
    private final SocketUtils socketUtils;

    public CommandSender(SocketUtils socketUtils) {
        this.socketUtils = socketUtils;
    }

    public void sendConnectToUser(String username) {
        String message = "connect:" + username;
        socketUtils.sendSocketMessage(message);
    }

    public void sendConfirmConnectedToUser(String username, int status) {
        String message = "confirmConnect:" + username + ":" + status;
        socketUtils.sendSocketMessage(message);
    }

    public void sendDisconnect() {
        String message = "disconnect";
        socketUtils.sendSocketMessage(message);
    }

    public void sendConfirmeDisconnect() {
        String message = "confirmDisconnect";
        socketUtils.sendSocketMessage(message);
    }

    public void sendCreateChatroom(List<String> participants) {
        String message = "newChatroom:" + String.join(":", participants);
        socketUtils.sendSocketMessage(message);
    }

    public void sendConfirmCreatedChatroom(int status, long id) {
        String message = "confirmChatroom:" + status;
        if (status == 0) {
            message = message.concat(":" + id);
        }
        socketUtils.sendSocketMessage(message);
    }

    public void sendGetChatroomSummaries(String currentUserUsername) {
        String message = "getChatroomSummaries:" + currentUserUsername;
        socketUtils.sendSocketMessage(message);
    }

    public void sendChatroomSummaries(int status, List<Conversation> conversations) {
        String message = "chatroomSummaries:" + status;

        if (status == 0) {
            message = message.concat(":" + conversations.size() + ":");
            List<String> parts = new ArrayList<>();
            for (Conversation conversation : conversations) {
                if (conversation.getMessages().size() == 0) {
                    parts.add(conversation.getId() + ";" + conversation.getMessages().size() + ";" + 0 + ";" + conversation.getNom());
                } else {
                    parts.add(conversation.getId() + ";" + conversation.getMessages().size() + ";" + conversation.getMessages().get(conversation.getMessages().size() - 1).hashCode() + ";" + conversation.getNom());
                }
            }
            message = message.concat(String.join(":", parts));
        }
        socketUtils.sendSocketMessage(message);
    }

    public void sendGetAllMessagesFromChatroom(long id) {
        String message = "getAllMessagesFromChatroom:" + id;
        socketUtils.sendSocketMessage(message);
    }

    public void sendGetAllMessagesFromChatroomSinceHash(long id, int hash)
    {
        String message = "getAllMessagesFromChatroomSinceHash:"+id+":"+hash;
        socketUtils.sendSocketMessage(message);
    }

    public void sendConfirmMessage(Message messageObj)
    {
        String message = "confirmMessage:" + messageObj.getConversationId() + ":" + messageObj.hashCode() + ":" + messageObj.getType() + ":" + messageObj.getTimestamp() + ":" + messageObj.getSender() + ":" + messageObj.getValue();
        socketUtils.sendSocketMessage(message);
    }

    public void sendAllMessagesFromChatroom(Conversation conversation) {
        for (Message message : conversation.getMessages()) {
            sendConfirmMessage(message);
        }
    }

    public void sendAllMessagesFromChatroomSinceHash(Conversation conversation, int hash) {
        int size = conversation.getMessages().size() - 1;

        List<Message> messagesToSend = new ArrayList<>();

        for (int i = size; i >= 0; i--) {
            Message messageObj = conversation.getMessages().get(i);
            if (messageObj.hashCode() == hash) {
                break;
            }

            messagesToSend.add(messageObj);
        }

        Collections.reverse(messagesToSend);

        for (Message messageObj : messagesToSend) {
            sendConfirmMessage(messageObj);
        }
    }

    public void sendMessage(Message messageObj) {
        String message = messageObj.toString();
        socketUtils.sendSocketMessage(message);
    }

    public void sendQuitConversation(Conversation conv, String username) {
        String message = "quitChatroom:" + conv.getId() + ":" + username;
        socketUtils.sendSocketMessage(message);
    }

    public void sendConfirmQuitConversation(int status, Conversation conv, String username) {
        String message = "confirmQuitChatroom:" + status;
        if (status == 0) {
            message = message + ":" + conv.getId() + ":" + username;
        }

        socketUtils.sendSocketMessage(message);
    }

    public void sendAddUserToChatroom(Conversation conv, String username) {
        String message = "addUserToChatroom:" + conv.getId() + ":" + username;
        socketUtils.sendSocketMessage(message);
    }

    public void sendConfirmAddUserToChatroom(int status, Conversation conv, String username) {
        String message = "confirmAddUserToChatroom:" + status;

        if (status == 0) {
            message = message + ":" + conv.getId() + ":" + username;
        }

        socketUtils.sendSocketMessage(message);
    }
}
