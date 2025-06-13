package com.mycompany.projeto.sd;

/**
 *
 * @author augustosimionato
 */
public class ProjetoSd {

    public static void main(String[] args) {
        // Parâmetros do teste
        long numPontos = 100_000_000L; // 100 milhões de pontos
        int numThreads = Runtime.getRuntime().availableProcessors();
        
//        long inicioSeq = System.currentTimeMillis();
//        long tempoSeq = System.currentTimeMillis() - inicioSeq;
        
        System.out.println("2. IMPLEMENTAÇÃO PARALELA (" + numThreads + " threads)");
        try {
            long inicioParalelo = System.currentTimeMillis();
            double piParalelo = MonteCarloParalelo.calcularPi(numPontos, numThreads);
            long tempoParalelo = System.currentTimeMillis() - inicioParalelo;
            
            System.out.println("Pi calculado: " + piParalelo);
            System.out.println("Precisão (diferença do Pi real): " + String.format("%.8f", Math.abs(Math.PI - piParalelo)));
            System.out.println("Tempo de execução: " + tempoParalelo + " ms");
//            System.out.println("Speedup: " + String.format("%.2f", (double)tempoSeq / tempoParalelo) + "x");
            System.out.println();
            
        } catch (Exception e) {
            System.err.println("Erro na execução paralela: " + e.getMessage());
        }
    }
}
