package com.mycompany.projeto.sd;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Interface RMI para cálculo de Pi usando o método de Monte Carlo.
 * Desenvolvido por Evelise Ribino.
 */
public interface MonteCarloRMI extends Remote {
    /**
     * Método remoto para calcular o valor aproximado de Pi.
     * @param numPontos Quantidade de pontos a serem gerados no método de Monte Carlo.
     * @return Valor aproximado de Pi.
     * @throws RemoteException Exceção lançada em caso de falha na comunicação remota.
     */
    double calcularPi(long numPontos) throws RemoteException;
}
