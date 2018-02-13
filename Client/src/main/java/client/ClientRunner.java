package client;


import gui.Window;

import java.net.ServerSocket;
import java.net.SocketException;

public class ClientRunner {
    public static ClientThread clientThread;

    public static void main(String[] args) {
        int port = 7798;
        try {
            if (args.length != 0) {
                port = Integer.valueOf(args[0]);
            } else {
                port = 7798;
            }
        } catch (NumberFormatException e) {
            port = 7798;
        }
        clientThread = new ClientThread(port);
        clientThread.start();
        Window window = Window.getInstance();
        window.setVisible(true);
    }
}

