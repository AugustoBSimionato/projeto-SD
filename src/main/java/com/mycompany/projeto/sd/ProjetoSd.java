package com.mycompany.projeto.sd;

/**
 * Projeto principal para execução dos testes comparativos entre:
 * - Implementação sequencial
 * - Implementação paralela
 * - Implementação distribuída (RMI)
 * 
 * Estruturado inicialmente por Augusto B. Simionato.
 * Baseado nos testes desenvolvidos por Felipe Kauã e Vinicius.
 * Adaptado por Evelise Ribino.
 */
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

            //RMI
            System.out.println("3. IMPLEMENTAÇÃO DISTRIBUÍDA (RMI)");
            try {
                long inicioRmi = System.currentTimeMillis();

                //Conectando ao servidor RMI
                MonteCarloRMI stub = (MonteCarloRMI) java.rmi.Naming.lookup("rmi://localhost:1099/MonteCarlo");

                // Chamando o método remoto
                double piRmi = stub.calcularPi(numPontos);

                long tempoRmi = System.currentTimeMillis() - inicioRmi;

                System.out.println("Pi calculado: " + piRmi);
                System.out.println("Precisão: " + String.format("%.8f", Math.abs(Math.PI - piRmi)));
                System.out.println("Tempo de execução: " + tempoRmi + " ms");
                System.out.println("Speedup (vs sequencial): " + String.format("%.2f", (double) tempoSeq / tempoRmi) + "x\n");

            } catch (Exception e) {
                System.err.println("Erro na execução via RMI: " + e.getMessage());
            }
            System.out.println("=============================================\n");
        }
    }
}
