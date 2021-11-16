package client.controller.state;

import client.controller.Controller;

import java.util.Scanner;

/**
 * Connection Failed State. The application enters this state when it failed to connect to a user.
 * <p>
 * After state(s) possible : Initial
 * Before state(s) possible : Connecting
 */
public class ConnectionFailedState implements State {
    @Override
    public void run(Controller c) {
        System.out.println("Connection failed!");

        boolean validInput = false;
        boolean userWantToQuit = false;

        Scanner sc = new Scanner(System.in);
        while(!validInput)
        {
            System.out.println("Do you want to retry ? (y/n)");
            String input = sc.nextLine();

            if(input.equals("y"))
            {
                userWantToQuit = true;
                validInput = true;
            }
            else if (input.equals("n"))
            {
                validInput = true;
            }
        }

        if(userWantToQuit)
            c.setCurrentState(c.terminationState);
        else
            c.setCurrentState(c.initState);
    }
}
