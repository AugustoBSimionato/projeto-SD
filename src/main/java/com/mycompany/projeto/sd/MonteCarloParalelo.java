package com.mycompany.projeto.sd;

import java.util.Random;
import java.util.concurrent.*;

public class MonteCarloParalelo {
    
    /**
     * Calcula Pi usando o método Monte Carlo paralelo
     * @param numPontos número total de pontos aleatórios
     * @param numThreads número de threads a serem utilizadas
     * @return valor aproximado de Pi
     */
    public static double calcularPi(long numPontos, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        long pontosPorThread = numPontos / numThreads;
        
        Future<Long>[] futures = new Future[numThreads];
        
        // Submete as tasks para as threads
        for (int i = 0; i < numThreads; i++) {
            final long inicio = i * pontosPorThread;
            final long fim = (i == numThreads - 1) ? numPontos : (i + 1) * pontosPorThread;
            
            futures[i] = executor.submit(new MonteCarloTask(fim - inicio));
        }
        
        // Coleta os resultados
        long totalPontosNaCircunferencia = 0;
        for (Future<Long> future : futures) {
            totalPontosNaCircunferencia += future.get();
        }
        
        executor.shutdown();
        
        return 4.0 * totalPontosNaCircunferencia / numPontos;
    }
    
    /**
     * Task para execução paralela do Monte Carlo
     */
    static class MonteCarloTask implements Callable<Long> {
        private final long numPontos;
        
        public MonteCarloTask(long numPontos) {
            this.numPontos = numPontos;
        }
        
        @Override
        public Long call() {
            Random random = new Random();
            long pontosNaCircunferencia = 0;
            
            for (long i = 0; i < numPontos; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                
                if (x * x + y * y <= 1.0) {
                    pontosNaCircunferencia++;
                }
            }
            
            return pontosNaCircunferencia;
        }
    }
}