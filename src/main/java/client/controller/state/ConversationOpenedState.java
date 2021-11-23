package client.controller.state;

import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.ConversationOpenedViewState;
import common.command.CommandSender;
import common.model.TextMessage;

/**
 * Conversation Joined State. Successfully joined a conversation. The current user is able to receive message and to send them.
 * <p>
 * After state(s) possible : Quitting Conversation
 * Before state(s) possible : Joining Conversation, Quitting Conversation Failed
 */
public class ConversationOpenedState implements State {
    private String message;
    private Gui gui;
    private CommandSender commandSender;


    @Override
    public void run(Controller c, Gui gui) {
        this.gui = gui;
        commandSender = new CommandSender(c.getCurrentConnection().getSocketUtils());
        ConversationOpenedViewState conversationOpenedViewState = new ConversationOpenedViewState(gui, c);
        gui.setConversationOpenedViewState(conversationOpenedViewState);
        gui.setCurrentViewState(gui.getConversationOpenedViewState());

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void sendMessage(Controller controller, String text) {
        TextMessage message = new TextMessage(controller.getCurrentConnection().getCurrentConversation().getId(), controller.getCurrentUser().getUsername(), text);
        commandSender.sendMessage(message);
    }


}
