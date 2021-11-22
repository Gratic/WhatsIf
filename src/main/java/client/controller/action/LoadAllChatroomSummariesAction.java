package client.controller.action;

import client.controller.Controller;
import client.gui.Gui;
import common.utils.ConnectionState;

import java.io.IOException;

public class LoadAllChatroomSummariesAction implements Action{
    @Override
    public void execute(ConnectionState currentConnection, Controller controller, Gui gui) throws IOException {
        String[] arguments = currentConnection.getCurrentArguments();
        int code = Integer.parseInt(arguments[1]);
        if(code==0){


            for(int i = 1; i<arguments.length;i++) {

            }


        }else if(code==1){
            System.out.println("no conversation");
        }else if(code==2)
        {
            System.out.println("error");
        }

    }
}
