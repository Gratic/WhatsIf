package server.action;

import com.sun.tools.javac.Main;
import common.command.CommandSender;
import common.model.User;
import common.utils.ConnectionState;
import server.MainServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestCreateChatroomAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();

        List<String> usernames = new ArrayList<>();
        usernames.addAll(Arrays.asList(arguments).subList(1, arguments.length));

        long convId = MainServer.conversationDao.create(usernames);

        if(convId != -1L)
        {
            commandSender.sendConfirmCreatedChatroom(0, convId);
            MainServer.conversationDao.persist(MainServer.conversationDao.searchConversationWithId(convId));
        }
        else
        {
            commandSender.sendConfirmCreatedChatroom(1, convId);
        }
    }
}
