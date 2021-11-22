package client.controller.state;

import client.SocketThread;
import client.controller.Controller;
import client.gui.Gui;
import client.gui.viewstate.UserConnectedViewState;
import common.command.CommandSender;
import common.model.Conversation;
import common.utils.SocketUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User Connected State. The user is connected.
 * <p>
 * After state(s) possible : Joining Conversation
 * Before state(s) possible : Connecting, Joining Conversation Failed, Quitting Conversation
 */
public class UserConnectedState implements State {
    private SocketThread thread;
    private Controller controller;
    private CommandSender commandSender;


    @Override
    public void run(Controller c, Gui gui) {

        this.controller = c;
        SocketUtils socketUtils = c.getCurrentConnection().getSocketUtils();
        commandSender = new CommandSender(socketUtils);

        UserConnectedViewState userConnectedViewState = new UserConnectedViewState(gui, c);
        gui.setUserConnectedViewState(userConnectedViewState);
        gui.setCurrentViewState(userConnectedViewState);
        thread = new SocketThread(c.getCurrentUser().getSocket(), c);
        controller.setSocketThread(thread);
        thread.start();


    }

    public void createNewChatroom(String usernames) {
        String[] users = usernames.split(",");
        List<String > participants = new ArrayList<>(List.of(users));
        if(!participants.contains(controller.getCurrentUser().getUsername()))
        {
            participants.add(controller.getCurrentUser().getUsername());
        }
        System.out.println("chatroom created");
        commandSender.sendCreateChatroom(participants);

    }


    public void disconnectUser() {
        commandSender.sendDisconnect();
    }




}
