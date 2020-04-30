package ro.uaic.info.client;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            var client = new GameClient("localhost", 8001);
            System.out.println(client.readLine());
            client.send("exit");
            System.out.println(client.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
