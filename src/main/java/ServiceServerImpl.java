import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

/**
 * Implementation for ServiceServe interface
 */
public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {
    HashMap serviceList;

    /**
     * Initializes the server's services
     * @throws RemoteException
     */
    public  ServiceServerImpl() throws RemoteException {
        setUpServices();
    }

    /**
     * Creates a list of services
     */
    private void setUpServices(){
        serviceList = new HashMap();
        serviceList.put("Dice Rolling Service", new DiceService());
        serviceList.put("Day of Week Service", new DayOfTheWeekService());
        serviceList.put("Visual Music Service", new MiniMusicService());
    }

    /**
     * Provides an array of available services
     * @return Object []
     */
    public Object [] getServiceList() {
        System.out.println("in remote");
        return serviceList.keySet().toArray();
    }

    /**
     * Returns a selected service
     * @param serviceKey
     * @return Service
     * @throws RemoteException
     */
    public Service getService(Object serviceKey) throws RemoteException {
        Service theService = (Service) serviceList.get(serviceKey);
        return theService;
    }

    public static void main(String[] args) {
        try {
            Naming.rebind("ServiceServer", new ServiceServerImpl());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Remote service is running");
    }
}
