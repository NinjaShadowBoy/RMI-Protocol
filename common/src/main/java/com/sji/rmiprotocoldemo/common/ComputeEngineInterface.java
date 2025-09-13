package com.sji.rmiprotocoldemo.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComputeEngineInterface extends Remote {
    String computeRequest(String request) throws RemoteException;
}
