package client.controller.state;

import client.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class UserConnectedState implements State {
    @Override
    public void run(Controller c) throws IOException {
        System.out.println("Connecté !");

        Scanner sc = new Scanner(System.in);

        System.out.println("Talk with who ?");
        String username = sc.nextLine();

        c.sendSocketMessage("requestJoinChatroom:" + username);

        while (c.socketIncomingData()) {
            System.out.println("in buffer:" + c.receiveSocketLine());
        }

        c.setCurrentState(c.joiningConversationState);
    }
}
