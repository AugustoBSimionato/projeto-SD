package com.mycompany.projeto.sd;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MonteCarloServidor implements MonteCarloRMI {

    @Override
    public double calcularPi(long numPontos) throws RemoteException {
        // Vamos reutilizar o método paralelo que seu amigo fez
        try {
            int numThreads = Runtime.getRuntime().availableProcessors();
            return MonteCarloParalelo.calcularPi(numPontos, numThreads);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Erro no cálculo de Pi", e);
        }
    }

    public static void main(String[] args) {
        try {
            MonteCarloServidor servidor = new MonteCarloServidor();
            MonteCarloRMI stub = (MonteCarloRMI) UnicastRemoteObject.exportObject(servidor, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MonteCarlo", stub);

            System.out.println("Servidor Monte Carlo pronto...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
