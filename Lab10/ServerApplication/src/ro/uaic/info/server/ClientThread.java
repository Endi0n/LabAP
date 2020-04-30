package ro.uaic.info.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new PrintWriter(socket.getOutputStream());

            out.println("Hello!");

            var keepAlive = true;
            while (keepAlive) {
                var request = in.readLine();
                switch (request) {
                    case "exit":
                        keepAlive = false;
                        out.println("Goodbye!");
                        break;
                    default:
                        out.println("Unknown command.");
                }
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
