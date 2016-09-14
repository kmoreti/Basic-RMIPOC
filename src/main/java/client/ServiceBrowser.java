package client;

import interfaces.Service;
import interfaces.ServiceServer;

import java.awt.*;
import javax.swing.*;
import java.rmi.*;
import java.awt.event.*;

/**
 * Client application ServiceBrowser
 * This application gets a list of services provided by the RMI service on the Server
 * and shows a GUI which allows the user to consume the services.
 */
public class ServiceBrowser {

    JPanel mainPanel;
    JComboBox serviceList;
    ServiceServer server;

    public void buildGUI(){
        JFrame frame = new JFrame("RMI Browser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        Object [] services = getServiceList();

        serviceList = new JComboBox(services);

        frame.getContentPane().add(BorderLayout.NORTH,serviceList);

        serviceList.addActionListener(new MyListListener());

        frame.setSize(500,500);
        frame.setVisible(true);
    }

    void loadService(Object serviceSelection){
        try {
            Service svc = server.getService(serviceSelection);
            mainPanel.removeAll();
            mainPanel.add(svc.getGuiPanel());
            mainPanel.validate();
            mainPanel.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    Object [] getServiceList() {
        Object obj = null;
        Object [] services = null;
        try {
            obj = Naming.lookup("rmi://10.146.93.108/ServiceServer");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        server = (ServiceServer) obj;

        try {
            services = server.getServiceList();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return services;
    }

    class MyListListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            Object selection = serviceList.getSelectedItem();
            loadService(selection);
        }
    }

    public static void main(String[] args) {
        new ServiceBrowser().buildGUI();
    }
}
