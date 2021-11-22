package client.controller.state;

import client.SocketThread;
import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.UserConnectedViewState;

/**
 * User Connected State. The user is connected.
 * <p>
 * After state(s) possible : Joining Conversation
 * Before state(s) possible : Connecting, Joining Conversation Failed, Quitting Conversation
 */
public class UserConnectedState implements State {
    private SocketThread thread;
    private Controller controller;

    @Override
    public void run(Controller c, Gui gui) {

        this.controller = c;
        UserConnectedViewState userConnectedViewState = new UserConnectedViewState(gui, c);
        gui.setUserConnectedViewState(userConnectedViewState);
        gui.setCurrentViewState(userConnectedViewState);
        thread = new SocketThread(c.getCurrentUser().getSocket(), c);
        controller.setSocketThread(thread);
        thread.start();
        receiveConversationsSummaries();
        int[] conversationsId = controller.getConversationsOfUser();
        for(int id : conversationsId)
        {
            receiveMessagesFromConversation(id);
        }

        if (thread.currentConnection.getCurrentConversation() != null) {
            c.conversationOpenedState.setIdConversation(thread.currentConnection.getCurrentConversation().getId);
            c.setCurrentState(c.conversationOpenedState);
        }

    }

    public void createNewChatroom(String usernames) {
        String[] users = usernames.split(",");
        String command = (controller.getCurrentUser().getUsername());
        for (int i = 0; i < users.length; i++) {
            command = (command + ":" + users[i]);
        }
        controller.getCurrentUser().sendSocketMessage("newChatroom:" + command);
    }

    public void receiveConversationsSummaries() {
        String command = (controller.getCurrentUser().getUsername());
        controller.getCurrentUser().sendSocketMessage("getChatroomSummaries:" + command);
    }

    public void receiveMessagesFromConversation(int conversationId){
        controller.getCurrentUser().sendSocketMessage("getAllMessagesFromChatroom:"+conversationId);
    }

    public void disconnectUser() {
        controller.getCurrentUser().sendSocketMessage("disconnect");
    }




}
