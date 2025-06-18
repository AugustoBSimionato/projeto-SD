# Projeto Final - Sistemas Distribuídos
> Cálculo de Pi por Monte Carlo

## Grupo 3
- Augusto B. Simionato
- Evelise Aparecida Ribino
- Felipe Kauã de Lima
- Gustavo José da Silveira Mello
- Vinicius Sussumu Vieira Ogawa

⚙️ Tecnologias Utilizadas
Java (versão recomendada: 24)

Programação sequencial

Programação paralela com Threads

Programação distribuída com RMI

IDE: VSCode (ou qualquer IDE de preferência)

🚀 Compilação e Execução do Projeto

Compilar:
> javac src/main/java/com/mycompany/projeto/sd/*.java

▶️ Execução das Soluções

1️⃣ Execução Sequencial
> java -cp src/main/java com.mycompany.projeto.sd.ProjetoSd
➡️ No menu que será exibido, selecione a opção "Sequencial".

2️⃣ Execução Paralela
> java -cp src/main/java com.mycompany.projeto.sd.ProjetoSd
➡️ No menu que será exibido, selecione a opção "Paralela".

3️⃣ Execução Distribuída (RMI)
Inicie o servidor:
> java -cp src/main/java com.mycompany.projeto.sd.MonteCarloServidor

Em seguida, inicie o cliente ou execute o projeto diretamente:
> java -cp src/main/java com.mycompany.projeto.sd.MonteCarloCliente

ou

> java -cp src/main/java com.mycompany.projeto.sd.ProjetoSd
➡️ No menu que será exibido, selecione a opção "Distribuída".

✅ Descrição do Projeto
Este projeto tem como objetivo comparar o tempo de execução de três abordagens:

Implementação sequencial
Implementação paralela com Threads
Implementação distribuída com RMI

O cálculo utilizado é o método de Monte Carlo para aproximação de Pi.
Foram realizados testes com diferentes quantidades de pontos para verificar o desempenho de cada abordagem.

📊 Tabela Comparativa

| Método       | 1M Pontos      | 10M Pontos     | 50M Pontos     | 100M Pontos    |
|--------------|---------------|---------------|---------------|---------------|
| Sequencial   | 14,30 ± 1,35  | 134,20 ± 1,17 | 667,40 ± 2,69 | 1336,30 ± 6,68|
| Paralelo     | 8,80 ± 11,22  | 20,50 ± 2,77  | 85,60 ± 8,09  | 144,00 ± 9,27 |
| Distribuído  | 14,80 ± 29,76 | 20,80 ± 1,94  | 83,00 ± 6,88  | 145,10 ± 6,09 |

💻 Configuração da Máquina
Exemplo de máquina utilizada nos testes de Evelise Aparecida Ribino:

Processador: AMD Ryzen 7 5700x; 8 núcleos; 16 threads.  
Memória RAM: 32GB.  
Sistema Operacional: Windows 11 IoT Enterprise LTSC; 64 bits.  
Ambiente de execução: Java 24.  
Número de hosts: 1.

🔍 Observações
O projeto foi desenvolvido colaborativamente com a seguinte divisão de atividades:

✅ Parte paralela: Desenvolvida por Augusto B. Simionato (incluindo o método de cálculo de Pi com Threads e a estrutura inicial da classe principal ProjetoSd).

✅ Parte sequencial: Desenvolvida por Vinicius Sussumu Vieira Ogawa e Felipe Kauã de Lima.

✅ Parte distribuída (RMI): Desenvolvida por Evelise Aparecida Ribino e integrada ao projeto original.

✅ Menu: Gustavo José da Silveira Mello adicionou o menu de seleção das abordagens e realizou os testes finais.

Todos os integrantes:

Testaram as suas soluções.
Compararam os tempos de execução.
Analisaram a escalabilidade e eficiência das soluções paralela e distribuída.
Identificaram gargalos e discutiram possíveis melhorias.

Gustavo também ficou responsável pela elaboração da apresentação em PDF, incluindo:

Explicação do problema.
Tabela comparativa dos tempos das soluções.
Gráficos.
Número de threads e hosts utilizados.

🗃️ Organização do Projeto

O projeto está organizado no formato padrão Maven.

O arquivo .gitignore está configurado para ignorar arquivos compilados e pacotes desnecessários.