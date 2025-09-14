package com.sji.rmiprotocoldemo.server;

import com.sji.rmiprotocoldemo.common.ComputeEngineInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine extends UnicastRemoteObject implements ComputeEngineInterface {

    protected ComputeEngine() throws RemoteException {
    }

    @Override
    public String computeRequest(String request) throws RemoteException {
        try {
            var res = String.valueOf(Calculator.evaluate(request));
            System.out.println("result: " + res);
            return res;

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
