package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import common.command.CommandSender;

public interface State {

    void run(Controller c, Gui gui);

    default void connectingButtonClick(Controller controller, String username, String ip, int port) {
        controller.askUserLoginState.entry(username, ip, port);
        controller.setCurrentState(controller.askUserLoginState);
    }

    default void creatingConversationButtonClick(Controller controller, String usernames) {
        controller.userConnectedState.createNewChatroom(usernames);
    }

    default void sendingMessageButtonClick(Controller controller, String textMessage) {
        controller.conversationOpenedState.sendMessage(controller, textMessage);
    }

    default void updateConversationsTimer(Controller controller){

        if(controller.getCurrentUser() != null)
        {
            CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
            commandSender.sendGetChatroomSummaries(controller.getCurrentUser().getUsername());
        }
    }

    default void quitDefinitlyConv (Controller controller)
    {
        CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
        commandSender.sendQuitConversation(controller.getCurrentConnection().getCurrentConversation(), controller.getCurrentUser().getUsername());
    }

    default void addUserToTheConversation (Controller controller, String username)
    {
        CommandSender commandSender = new CommandSender(controller.getCurrentConnection().getSocketUtils());
        commandSender.sendAddUserToChatroom(controller.getCurrentConnection().getCurrentConversation(),username);
    }
    default void disconnectButtonClick(Controller controller) {
        controller.userConnectedState.disconnectUser();
    }

}


