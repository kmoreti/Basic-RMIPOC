import java.rmi.*;

/**
 * The interface for methods provided by the Server
 */
public interface ServiceServer extends Remote{
    Object [] getServiceList() throws RemoteException;
    Service getService(Object serviceKey) throws RemoteException;
}
