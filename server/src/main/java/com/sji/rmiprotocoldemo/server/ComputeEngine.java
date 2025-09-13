package com.sji.rmiprotocoldemo.server;

import com.sji.rmiprotocoldemo.common.ComputeEngineInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine extends UnicastRemoteObject implements ComputeEngineInterface {
    ComputeEngine() throws RemoteException {
        super();
    }



    @Override
    public String computeRequest(String request) throws RemoteException {
        try {
            return String.valueOf(Calculator.evaluate(request));

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
