package com.mycompany.projeto.sd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MonteCarloCliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MonteCarloRMI stub = (MonteCarloRMI) registry.lookup("MonteCarlo");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a quantidade de pontos para calcular Pi: ");
            long numPontos = scanner.nextLong();

            System.out.println("Calculando Pi no servidor remoto...");
            double pi = stub.calcularPi(numPontos);
            System.out.println("Valor aproximado de Pi: " + pi);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
