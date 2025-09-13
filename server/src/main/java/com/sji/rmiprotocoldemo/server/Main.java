package com.sji.rmiprotocoldemo.server;

import com.sji.rmiprotocoldemo.common.ComputeEngineInterface;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try{
            ComputeEngine obj = new ComputeEngine();
            ComputeEngineInterface stub = (ComputeEngineInterface) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/compute";
            System.out.println("Server started at " + url);


            registry.bind(url, stub);
            System.out.println("Server started");
            //Abena - 172.16.2.22
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}