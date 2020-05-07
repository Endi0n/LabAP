package ro.uaic.info.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHello extends Remote {
    String sayHelloTo(String name) throws RemoteException;
}
