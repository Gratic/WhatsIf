package common.command;

import common.model.Conversation;
import common.model.Message;
import common.utils.SocketUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility function to send command over the socket.
 */
public class CommandSender {
    private final SocketUtils socketUtils;

    public CommandSender(SocketUtils socketUtils) {
        this.socketUtils = socketUtils;
    }

    /**
     * Send the connection command.
     *
     * @param username the username
     */
    public void sendConnectToUser(String username) {
        String message = "connect:" + username;
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the confirmation command of the connection command.
     *
     * @param username the username
     * @param status the status
     */
    public void sendConfirmConnectedToUser(String username, int status) {
        String message = "confirmConnect:" + username + ":" + status;
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the disconnect command.
     */
    public void sendDisconnect() {
        String message = "disconnect";
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the confirmation command to the disconnect command.
     */
    public void sendConfirmeDisconnect() {
        String message = "confirmDisconnect";
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the command to create a chatroom.
     *
     * @param participants participants
     */
    public void sendCreateChatroom(List<String> participants) {
        String message = "newChatroom:" + String.join(":", participants);
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the confirmation command to a chatroom creation command.
     *
     * @param status status
     * @param id id
     */
    public void sendConfirmCreatedChatroom(int status, long id) {
        String message = "confirmChatroom:" + status;
        if (status == 0) {
            message = message.concat(":" + id);
        }
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the request to get all summaries of all chatroom the user is in.
     *
     * @param currentUserUsername username
     */
    public void sendGetChatroomSummaries(String currentUserUsername) {
        String message = "getChatroomSummaries:" + currentUserUsername;
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send all summaries of all chatroom the user is in.
     *
     * @param status status
     * @param conversations conversations
     */
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

    /**
     * Send the request to get all messages from a conversation.
     *
     * @param id id
     */
    public void sendGetAllMessagesFromChatroom(long id) {
        String message = "getAllMessagesFromChatroom:" + id;
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the request to get all messages from a conversation since hash to a user.
     *
     * @param id id
     * @param hash hash
     */
    public void sendGetAllMessagesFromChatroomSinceHash(long id, int hash)
    {
        String message = "getAllMessagesFromChatroomSinceHash:"+id+":"+hash;
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send a message to a user.
     *
     * @param messageObj the message
     */
    public void sendConfirmMessage(Message messageObj)
    {
        String message = "confirmMessage:" + messageObj.getConversationId() + ":" + messageObj.hashCode() + ":" + messageObj.getType() + ":" + messageObj.getTimestamp() + ":" + messageObj.getSender() + ":" + messageObj.getValue();
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send all messages of a conversation to a user.
     *
     * @param conversation Conversation
     */
    public void sendAllMessagesFromChatroom(Conversation conversation) {
        for (Message message : conversation.getMessages()) {
            sendConfirmMessage(message);
        }
    }

    /**
     * Send all messages of a conversation since hash to a user.
     * Hash is excluded from the messages sent.
     *
     * @param conversation Conversation
     * @param hash hash
     */
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

    /**
     * Send a message to a conversation.
     *
     * @param messageObj message
     */
    public void sendMessage(Message messageObj) {
        String message = messageObj.toString();
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the request to quit a conversation.
     *
     * @param conv Conversation
     * @param username username of the user that wants to quit.
     */
    public void sendQuitConversation(Conversation conv, String username) {
        String message = "quitChatroom:" + conv.getId() + ":" + username;
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the confirmation of a quitting request.
     *
     * @param status status
     * @param conv Conversation
     * @param username username of the user that wants to quit
     */
    public void sendConfirmQuitConversation(int status, Conversation conv, String username) {
        String message = "confirmQuitChatroom:" + status;
        if (status == 0) {
            message = message + ":" + conv.getId() + ":" + username;
        }

        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the request to add a user to a conversation.
     *
     * @param conv Conversation
     * @param username the username
     */
    public void sendAddUserToChatroom(Conversation conv, String username) {
        String message = "addUserToChatroom:" + conv.getId() + ":" + username;
        socketUtils.sendSocketMessage(message);
    }

    /**
     * Send the confirmation to the request to add a user to a conversation.
     *
     * @param status status
     * @param conv Conversation
     * @param username username
     */
    public void sendConfirmAddUserToChatroom(int status, Conversation conv, String username) {
        String message = "confirmAddUserToChatroom:" + status;

        if (status == 0) {
            message = message + ":" + conv.getId() + ":" + username;
        }

        socketUtils.sendSocketMessage(message);
    }
}
