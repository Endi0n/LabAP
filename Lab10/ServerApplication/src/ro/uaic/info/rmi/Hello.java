package ro.uaic.info.rmi;

import java.rmi.RemoteException;

public class Hello implements IHello {
    @Override
    public String sayHelloTo(String name) throws RemoteException {
        return String.format("Hello %s!", name);
    }
}
