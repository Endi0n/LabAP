package ro.uaic.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String serverAddr, int port) throws IOException {
        socket = new Socket(serverAddr, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    public void send(String message) {
        out.println(message);
        out.flush();
    }

    public void close() throws IOException{
        in.close();
        out.close();
        socket.close();
    }
}
