package client.controller.state;

import client.controller.Controller;
import common.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class InitState implements State{

    Socket connectionSocket = null;

    PrintStream socOut = null;

    public void run(Controller c) throws IOException {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your username");
        String username = sc.nextLine();
        if(username!=null && !username.equals("")){
            c.currentUser = new User(username);
            System.out.println("Enter the ip address");
            String ip = sc.nextLine();
            System.out.println("Enter the port");
            int port = sc.nextInt();
            try{
                connectionSocket = new Socket(ip, port);
                socOut = new PrintStream(connectionSocket.getOutputStream());
                socOut.println("requestConnection:"+username);
            }catch (IOException e) {
                System.err.println("Couldn't get I/O for "
                        + "the connection to:"+ ip);
                System.exit(1);
            }
        }

    }
}
