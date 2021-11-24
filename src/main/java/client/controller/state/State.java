package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import common.command.CommandSender;

public interface State {

    void run(Controller c, Gui gui);

    /**
     * Send a connecting request to the server, containing the username, the ip address and the port
     * @param controller the controller
     * @param username the username entered
     * @param ip the ip entered
     * @param port the port entered
     */
    default void connectingButtonClick(Controller controller, String username, String ip, int port) {
        controller.askUserLoginState.entry(username, ip, port);
        controller.setCurrentState(controller.askUserLoginState);
    }

    /**
     * Send a creating conversation request to the server, containing the usernames of the other users of the conversation
     * @param controller the controller
     * @param usernames the usernames of the other users of the conversation to be created
     */
    default void creatingConversationButtonClick(Controller controller, String usernames) {
        controller.userConnectedState.createNewChatroom(usernames);
    }

    /**
     * Send a sending message request to the server, containing the message
     * @param controller the controller
     * @param textMessage message to be sent
     */
    default void sendingMessageButtonClick(Controller controller, String textMessage) {
        controller.conversationOpenedState.sendMessage(controller, textMessage);
    }

    /**
     * Send a updating conversations request to the server
     * @param controller the controller
     */
    default void updateConversationsTimer(Controller controller) {

        if (controller.getCurrentUser() != null) {
            CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
            commandSender.sendGetChatroomSummaries(controller.getCurrentUser().getUsername());
        }
    }

    /**
     * Send a quitting request to the server
     * @param controller the controller
     */
    default void quitDefinitlyConv(Controller controller) {
        CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
        commandSender.sendQuitConversation(controller.getCurrentConnection().getCurrentConversation(), controller.getCurrentUser().getUsername());
    }

    /**
     * Send a adding user request to the server, containing the username of the user who wants to quit the conversation
     * @param controller the controller
     * @param username sername of the user who wants to quit the conversation
     */
    default void addUserToTheConversation(Controller controller, String username) {
        CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
        commandSender.sendAddUserToChatroom(controller.getCurrentConnection().getCurrentConversation(), username);
    }

    /**
     * Send a disconnecting request to the server
     * @param controller controller
     */
    default void disconnectButtonClick(Controller controller) {
        controller.userConnectedState.disconnectUser();
    }

}


