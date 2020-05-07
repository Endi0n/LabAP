package ro.uaic.info;

import ro.uaic.info.rmi.IHello;

import java.rmi.registry.LocateRegistry;

public class Main {

    public static void main(String[] args) {
        try {
            var registry = LocateRegistry.getRegistry("localhost", 8000);
            var helloStub = (IHello) registry.lookup("Hello");
            System.out.println(helloStub.sayHelloTo("Mr. Test"));

            var client = new GameClient("localhost", 8001);
            System.out.println(client.readLine());
            client.send("stop");
            System.out.println(client.readLine());
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
