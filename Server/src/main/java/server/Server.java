package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws InterruptedException, IOException {
        try {
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
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                new ServerThread(serverSocket.accept()).start();//запуск потока
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}