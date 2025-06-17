package com.mycompany.projeto.sd;

import java.util.Scanner;

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
    private static final int numThreads = Runtime.getRuntime().availableProcessors();
    private static final long[] proporcoes = {1_000_000L, 10_000_000L, 50_000_000L, 100_000_000L};
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CONFIGURAÇÃO DA MÁQUINA:");
        System.out.println("- Núcleos disponíveis: " + numThreads);
        System.out.println("- Memória máxima (MB): " + Runtime.getRuntime().maxMemory() / (1024 * 1024));
        System.out.println();

        while (true) {
            exibirMenu();
            int opcao = lerOpcao(scanner);
            
            if (opcao == 0) {
                System.out.println("Encerrando aplicação...");
                break;
            }
            
            executarOpcao(opcao);
            
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        }
        
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("========== MENU DE EXECUÇÃO ==========");
        System.out.println("1. Execução Sequencial");
        System.out.println("2. Execução Paralela");
        System.out.println("3. Execução Distribuída");
        System.out.println("4. Executar Todos os Métodos");
        System.out.println("0. Sair");
        System.out.println("======================================");
        System.out.print("Escolha o método de execução: ");
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            return opcao;
        } catch (Exception e) {
            scanner.nextLine(); // Limpar buffer
            return -1; // Opção inválida
        }
    }
    
    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                executarSequencial();
                break;
            case 2:
                executarParalelo();
                break;
            case 3:
                executarDistribuido();
                break;
            case 4:
                executarSequencial();
                executarParalelo();
                executarDistribuido();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void executarSequencial() {
        System.out.println("\n========== EXECUÇÃO SEQUENCIAL ==========");
        
        for (long numPontos : proporcoes) {
            System.out.println("====== Testando com " + numPontos + " pontos ======");
            
            System.out.println("1. IMPLEMENTAÇÃO SEQUENCIAL");
            long inicioSeq = System.currentTimeMillis();
            double piSeq = MonteCarloSequencial.calcularPi(numPontos);
            long tempoSeq = System.currentTimeMillis() - inicioSeq;

            System.out.println("Pi calculado: " + piSeq);
            System.out.println("Precisão: " + String.format("%.8f", Math.abs(Math.PI - piSeq)));
            System.out.println("Tempo de execução: " + tempoSeq + " ms");
            System.out.println("=============================================\n");
        }
    }

    private static void executarParalelo() {
        System.out.println("\n========== EXECUÇÃO PARALELA (" + numThreads + " threads) ==========");
        
        for (long numPontos : proporcoes) {
            System.out.println("====== Testando com " + numPontos + " pontos ======");
            
            System.out.println("2. IMPLEMENTAÇÃO PARALELA (" + numThreads + " threads)");
            try {
                long inicioPar = System.currentTimeMillis();
                double piPar = MonteCarloParalelo.calcularPi(numPontos, numThreads);
                long tempoPar = System.currentTimeMillis() - inicioPar;

                System.out.println("Pi calculado: " + piPar);
                System.out.println("Precisão: " + String.format("%.8f", Math.abs(Math.PI - piPar)));
                System.out.println("Tempo de execução: " + tempoPar + " ms");

            } catch (Exception e) {
                System.err.println("Erro na execução paralela: " + e.getMessage());
            }
            System.out.println("=============================================\n");
        }
    }

    private static void executarDistribuido() {
        System.out.println("\n========== EXECUÇÃO DISTRIBUÍDA (RMI) ==========");
        
        for (long numPontos : proporcoes) {
            System.out.println("====== Testando com " + numPontos + " pontos ======");
            
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

            } catch (Exception e) {
                System.err.println("Erro na execução via RMI: " + e.getMessage());
            }
            System.out.println("=============================================\n");
        }
    }
  
}