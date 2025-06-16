package com.mycompany.projeto.sd;

public class ProjetoSd {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        long[] proporcoes = {1_000_000L, 10_000_000L, 50_000_000L, 100_000_000L};

        System.out.println("CONFIGURAÇÃO DA MÁQUINA:");
        System.out.println("- Núcleos disponíveis: " + numThreads);
        System.out.println("- Memória máxima (MB): " + Runtime.getRuntime().maxMemory() / (1024 * 1024));
        System.out.println();

        for (long numPontos : proporcoes) {
            System.out.println("====== Testando com " + numPontos + " pontos ======");

            // SEQUENCIAL
            System.out.println("1. IMPLEMENTAÇÃO SEQUENCIAL");
            long inicioSeq = System.currentTimeMillis();
            double piSeq = MonteCarloSequencial.calcularPi(numPontos);
            long tempoSeq = System.currentTimeMillis() - inicioSeq;

            System.out.println("Pi calculado: " + piSeq);
            System.out.println("Precisão: " + String.format("%.8f", Math.abs(Math.PI - piSeq)));
            System.out.println("Tempo de execução: " + tempoSeq + " ms\n");

            // PARALELO
            System.out.println("2. IMPLEMENTAÇÃO PARALELA (" + numThreads + " threads)");
            try {
                long inicioPar = System.currentTimeMillis();
                double piPar = MonteCarloParalelo.calcularPi(numPontos, numThreads);
                long tempoPar = System.currentTimeMillis() - inicioPar;

                System.out.println("Pi calculado: " + piPar);
                System.out.println("Precisão: " + String.format("%.8f", Math.abs(Math.PI - piPar)));
                System.out.println("Tempo de execução: " + tempoPar + " ms");
                System.out.println("Speedup: " + String.format("%.2f", (double) tempoSeq / tempoPar) + "x\n");

            } catch (Exception e) {
                System.err.println("Erro na execução paralela: " + e.getMessage());
            }

            System.out.println("=============================================\n");
        }
    }
}
