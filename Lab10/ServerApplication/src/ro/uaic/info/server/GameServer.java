package ro.uaic.info.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

public class GameServer {
    static private boolean keepAlive = true;

    public static void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(1000);

            while (GameServer.keepAlive) {
                try {
                    var socket = serverSocket.accept();
                    new ClientThread(socket).start();
                } catch (SocketTimeoutException e) {}
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public static void stop() {
        GameServer.keepAlive = false;
    }

    public static boolean getKeepAlive() {
        return GameServer.keepAlive;
    }
}
