package common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SocketUtils {
    private final Socket socket;
    private PrintStream socOut;
    private BufferedReader socIn;


    public SocketUtils(Socket socket) {
        this.socket = socket;

        try {
            this.socOut = new PrintStream(socket.getOutputStream());
            this.socIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility function to send a message on the user's socket.
     *
     * @param message the message to send
     */
    public void sendSocketMessage(String message) {
        this.socOut.println(message);
    }


    /**
     * Utility function to receive a message from the user's socket.
     *
     * @return String, the message
     * @throws IOException IOException
     */
    public String receiveSocketLine() throws IOException {
        return this.socIn.readLine();
    }


    /**
     * Utility function to determine if there is a message pending.
     *
     * @return true if a message is waiting in buffer, else false
     * @throws IOException IOException
     */
    public boolean socketIncomingData() throws IOException {
        return this.socIn.ready();
    }

    public void close() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (socIn != null) {
            try {
                socIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (socOut != null) {
            socOut.close();
        }
    }
}
