package com.mycompany.projeto.sd;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author evelise
 */
public interface MonteCarloRMI extends Remote {
    double calcularPi(long numPontos) throws RemoteException;
}
