package com.mycompany.projeto.sd;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author evelise
 */
public class MonteCarloServidor implements MonteCarloRMI {

    @Override
    public double calcularPi(long numPontos) throws RemoteException {
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

            // Cria o registry localmente na porta 1099 (se já existir, pega o existente)
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("Registry criado na porta 1099");
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(1099);
                System.out.println("Registry já existente obtido");
            }

            registry.rebind("MonteCarlo", stub);

            System.out.println("Servidor Monte Carlo pronto...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
