package com.mycompany.projeto.sd;

public class ProjetoSd {
    public static void main(String[] args) {
        long numPontos = 100_000_000L; // 100 milhões de pontos
        int numThreads = Runtime.getRuntime().availableProcessors();

        // 1. IMPLEMENTAÇÃO SEQUENCIAL
        System.out.println("1. IMPLEMENTAÇÃO SEQUENCIAL");
        long inicioSeq = System.currentTimeMillis();
        double piSeq = MonteCarloSequencial.calcularPi(numPontos);
        long tempoSeq = System.currentTimeMillis() - inicioSeq;

        System.out.println("Pi calculado: " + piSeq);
        System.out.println("Precisão (diferença do Pi real): " + String.format("%.8f", Math.abs(Math.PI - piSeq)));
        System.out.println("Tempo de execução: " + tempoSeq + " ms\n");

        // 2. IMPLEMENTAÇÃO PARALELA
        System.out.println("2. IMPLEMENTAÇÃO PARALELA (" + numThreads + " threads)");
        try {
            long inicioParalelo = System.currentTimeMillis();
            double piParalelo = MonteCarloParalelo.calcularPi(numPontos, numThreads);
            long tempoParalelo = System.currentTimeMillis() - inicioParalelo;

            System.out.println("Pi calculado: " + piParalelo);
            System.out.println("Precisão (diferença do Pi real): " + String.format("%.8f", Math.abs(Math.PI - piParalelo)));
            System.out.println("Tempo de execução: " + tempoParalelo + " ms");
            System.out.println("Speedup: " + String.format("%.2f", (double) tempoSeq / tempoParalelo) + "x");
        } catch (Exception e) {
            System.err.println("Erro na execução paralela: " + e.getMessage());
        }
    }
}