package ro.uaic.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
    private final Socket socket;

    public ClientThread(Socket socket) throws SocketException {
        this.socket = socket;
        socket.setSoTimeout(1000);
    }

    @Override
    public void run() {
        try {
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new PrintWriter(socket.getOutputStream());

            out.println("Hello!");
            out.flush();

            var keepAlive = true;
            while (keepAlive) {
                try {
                    var request = in.readLine();
                    switch (request) {
                        case "exit":
                            keepAlive = false;
                            out.println("Goodbye!");
                            break;
                        case "stop":
                            GameServer.stop();
                            break;
                        default:
                            out.println("Unknown command.");
                    }
                } catch (IOException e) {
                    if (!GameServer.getKeepAlive()) {
                        keepAlive = false;
                        out.println("Server is shutting down.");
                    }
                }
                out.flush();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
