package ro.uaic.info.server;

import java.io.IOException;
import java.net.ServerSocket;

public class GameServer {
    public static void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                var socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
