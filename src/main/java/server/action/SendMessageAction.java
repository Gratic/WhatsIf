package server.action;

import common.command.CommandSender;
import common.model.Conversation;
import common.model.Message;
import common.model.TextMessage;
import common.model.User;
import common.utils.ConnectionState;
import common.utils.SocketUtils;
import server.MainServer;

public class SendMessageAction implements Action {
    @Override
    public void execute(ConnectionState currentConnection, CommandSender commandSender) {
        String[] arguments = currentConnection.getCurrentArguments(7);

        String conversationId = arguments[1];
        String messageHash = arguments[2];
        String type = arguments[3];
        String timestamp = arguments[4];
        String sender = arguments[5];
        String value = arguments[6];

        long convId = Long.parseLong(conversationId);
        int hash = Integer.parseInt(messageHash);
        Conversation conversation = MainServer.conversationDao.searchConversationWithId(convId);

        if (type.equals("text") && sender.equals(currentConnection.getCurrentUser().getUsername())) {
            Message newMessage = new TextMessage(convId, Long.parseLong(timestamp), sender, value);
            if (newMessage.hashCode() == hash)
            {
                conversation.addMessage(newMessage);

                MainServer.conversationDao.persist(conversation);

                // Making the confirmation command
                int nbParticipants = conversation.numberOfParticipants();
                for(int i = 0; i < nbParticipants; i++)
                {
                    String participantUsername = conversation.getUsername(i);
                    if(MainServer.userDao.isConnected(participantUsername))
                    {
                        User user = MainServer.userDao.searchByUsername(participantUsername);
                        CommandSender comSender = new CommandSender(new SocketUtils(user.getSocket()));
                        comSender.sendConfirmMessage(newMessage);
                    }
                }
            }
        }
    }
}
