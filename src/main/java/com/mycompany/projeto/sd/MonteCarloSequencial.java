package com.mycompany.projeto.sd;

import java.util.Random;

/**
 * Implementação sequencial do cálculo de Pi usando o método de Monte Carlo.
 * Código desenvolvido por Felipe Kauã e Vinicius.
 * 
 * Adaptado por Evelise Ribino para integração com os testes comparativos.
 */
public class MonteCarloSequencial {
    public static double calcularPi(long numPontos) {
        Random random = new Random();
        long pontosNaCircunferencia = 0;
        
        for (long i = 0; i < numPontos; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            
            if (x * x + y * y <= 1.0) {
                pontosNaCircunferencia++;
            }
        }
        
        return 4.0 * pontosNaCircunferencia / numPontos;
    }
}