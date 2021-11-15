package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.List;

public class ClientThread extends Thread {
    private Socket clientSocket;

    public ClientThread(Socket s) { this.clientSocket = s; }

    @Override
    public void run() {
        try {
            BufferedReader socIn = null;
            socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            SocketAddress addr = clientSocket.getLocalSocketAddress();
            SocketAddress addr2 = clientSocket.getRemoteSocketAddress();
            PrintStream socOut = new PrintStream(clientSocket.getOutputStream());

            while (true) {
                String line = socIn.readLine();
                List<String> arguments = List.of(line.split(":"));

                System.out.println(addr + ": " + line + ": " + addr2);

                if(arguments.size() != 0)
                {
                    switch (arguments.get(0)) {
                        case "requestConnection" -> {
                            System.out.println(arguments.get(1));
                        }
                        case "joinConversation" -> {
                        }
                        case "sendMessage" -> {
                        }
                        case "quitConversation" -> {

                        }
                        default -> {
                            System.out.println("Invalid Command");
                        }
                    }
                }

                socOut.println(line);
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
