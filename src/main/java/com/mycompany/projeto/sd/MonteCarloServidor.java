package com.mycompany.projeto.sd;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Servidor RMI para cálculo de Pi usando o método de Monte Carlo.
 * Desenvolvido por Evelise Ribino.
 * 
 * Utiliza o cálculo paralelo desenvolvido por Augusto B. Simionato.
 */
public class MonteCarloServidor implements MonteCarloRMI {

    /**
     * Implementação do método remoto que calcula Pi usando o método paralelo.
     * @param numPontos Quantidade de pontos a serem gerados no método de Monte Carlo.
     * @return Valor aproximado de Pi.
     * @throws RemoteException Exceção lançada em caso de falha no cálculo.
     */

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

    /**
     * Método principal que inicia o servidor RMI e registra o objeto remoto.
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        try {
            MonteCarloServidor servidor = new MonteCarloServidor();
            MonteCarloRMI stub = (MonteCarloRMI) UnicastRemoteObject.exportObject(servidor, 0);

            // Cria o registro RMI localmente na porta 1099, ou obtém o existente
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("Registry criado na porta 1099");
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(1099);
                System.out.println("Registry já existente obtido");
            }

            // Registra o stub no registry
            registry.rebind("MonteCarlo", stub);

            System.out.println("Servidor Monte Carlo pronto...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
