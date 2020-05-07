package ro.uaic.info;

import ro.uaic.info.rmi.IHello;
import ro.uaic.info.rmi.Hello;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class GameServer {
    static private boolean keepAlive = true;

    public static void start(int rmiServerPort, int tcpServerPort) throws RemoteException, AlreadyBoundException {
        startRMIServer(rmiServerPort);
        startTCPServer(tcpServerPort);
    }

    private static void startRMIServer(int port) throws RemoteException, AlreadyBoundException {
        var helloEndpoint = new Hello();
        var helloStub = (IHello) UnicastRemoteObject.exportObject(helloEndpoint, 0);

        var registry = LocateRegistry.createRegistry(port);
        registry.bind("Hello", helloStub);
    }

    private static void startTCPServer(int port) {
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
