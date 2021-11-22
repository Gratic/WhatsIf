package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

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

    /*default void receivingMessageButtonClick(Controller controller)
    {
        controller.conversationJoinedState.receiveMessage(controller);
    }*/

    /*
    default void quittingConvButtonClick(Controller controller) {
        controller.getCurrentUser().sendSocketMessage("quitChatroom");
        controller.setCurrentState(controller.quittingConversationState);
    }

     */


    default void disconnectButtonClick(Controller controller) {
        controller.userConnectedState.disconnectUser();
    }

}


