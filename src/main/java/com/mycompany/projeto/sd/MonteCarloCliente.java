package com.mycompany.projeto.sd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Cliente RMI para cálculo de Pi usando o método de Monte Carlo.
 * Desenvolvido por Evelise Ribino.
 * 
 * Este código utiliza o cálculo paralelo desenvolvido por Augusto B. Simionato e
 * a estrutura de teste adaptada do projeto de Felipe Dauã e Vinicius.
 */
public class MonteCarloCliente {
    /**
     * Método principal que conecta ao servidor RMI, solicita o cálculo de Pi e exibe o resultado.
     */
    public static void main(String[] args) {
        try {
            // Localiza o registro RMI no localhost na porta padrão 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Obtém a referência remota do objeto MonteCarlo
            MonteCarloRMI stub = (MonteCarloRMI) registry.lookup("MonteCarlo");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a quantidade de pontos para calcular Pi: ");
            long numPontos = scanner.nextLong();

            System.out.println("Calculando Pi no servidor remoto...");
            long inicio = System.currentTimeMillis();

            // Chamada remota para cálculo de Pi
            double pi = stub.calcularPi(numPontos);
            
            long fim = System.currentTimeMillis();

            System.out.println("Valor aproximado de Pi: " + pi);
            System.out.println("Tempo de execução (Distribuído - RMI): " + (fim - inicio) + " ms");

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
