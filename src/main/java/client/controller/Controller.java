package client.controller;

import client.controller.state.ConnectingState;
import client.controller.state.InitState;
import client.controller.state.State;
import client.controller.state.UserConnectedState;
import common.Conversation;
import common.User;

import java.io.IOException;

public class Controller {


    public User currentUser;
    private Conversation conversation;

    public State initState;
    public State ConnectingState;
    public State UserConnectedState;

    private State currentState;


    public Controller(){
        this.initState = new InitState();
       // this.ConnectingState = new ConnectingState();
        //this.UserConnectedState = new UserConnectedState();

        init();
    }

    public void init()
    {
        this.currentState = initState;
        runCurrentState();
    }

    public void runCurrentState()
    {

        try {
            this.currentState.run(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
