package com.sji.rmiprotocoldemo.server;

import com.sji.rmiprotocoldemo.common.ComputeEngineInterface;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Main {
    public static void main(String[] args) {
        try{
            ComputeEngine server = new ComputeEngine();

            if(UnicastRemoteObject.unexportObject(server, true)){
                System.out.println("Server unexported");
            }

            ComputeEngineInterface stub = (ComputeEngineInterface) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/compute";

            System.out.println("Server started at " + url);


            registry.rebind(url, stub);

            //Abena - 172.16.2.22
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}