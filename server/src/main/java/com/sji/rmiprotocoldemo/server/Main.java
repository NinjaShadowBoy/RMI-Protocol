package com.sji.rmiprotocoldemo.server;

import com.sji.rmiprotocoldemo.common.ComputeEngineInterface;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws RemoteException, UnknownHostException, NotBoundException {
        // Instantiate the remote object
        ComputeEngine obj = new ComputeEngine();
        int PORT_NUMBER = 8080;
        String serviceName = "compute";

        // Bind the remote object's stub in the registry'
        ComputeEngineInterface stub = (ComputeEngineInterface) UnicastRemoteObject.exportObject(obj, 0);
        // Bind the remote object's stub in the registry'
        Registry registry = LocateRegistry.createRegistry(PORT_NUMBER);

        // Get the IP address of the machine running the server
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        String host = InetAddress.getLocalHost().getHostAddress();

        String url = "rmi://" + host + ":" + PORT_NUMBER + "/" + serviceName;
        System.out.println("Server started at " + url);

        // Rebind the remote object's stub in the registry'
        registry.rebind(serviceName, stub);
        System.out.println("Server started");
    }
}