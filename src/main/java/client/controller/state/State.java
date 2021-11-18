package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;

public interface State {

    void run(Controller c, Gui gui);

    default void connectingButtonClick(Controller controller, String username, String ip, int port) {
        controller.askUserLoginState.setUsername(username);
        controller.askUserLoginState.setIp(ip);
        controller.askUserLoginState.setPort(port);
        controller.setCurrentState(controller.askUserLoginState);
    }

    default void joiningConversationButtonClick(Controller controller, String username){
        controller.setUsernameOtherUser(username);
        controller.setCurrentState(controller.askUserConversationState);
    }

    default void sendingMessageButtonClick(Controller controller, String textMessage){
        //controller.conversationJoinedState.setUserAction(textMessage);
    }

    default void quittingConvButtonClick(Controller controller)
    {
        controller.getCurrentUser().sendSocketMessage("quitChatroom");
        controller.setCurrentState(controller.quittingConversationState);
    }
}


