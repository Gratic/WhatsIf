package client;

import client.controller.Controller;

public class Main {

    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Controller controller = new Controller();
            }
        });
    }
}
