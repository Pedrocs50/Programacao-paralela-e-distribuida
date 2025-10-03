/**
 * Classe principal para iniciar a simulação do Produtor-Consumidor.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Cria a instância única do recurso que será compartilhado entre as threads.
        SharedList sharedList = new SharedList();

        // 2. Cria as tarefas (Runnables) do Produtor e do Consumidor.
        Producer producerTask = new Producer(sharedList);
        Consumer consumerTask = new Consumer(sharedList);

        // 3. Cria as threads, associando cada uma a uma tarefa e dando nomes.
        Thread t1 = new Thread(producerTask, "T1-Producer");
        Thread t2 = new Thread(consumerTask, "T2-Consumer");

        // 4. Inicia a execução das threads. A partir daqui, elas rodam "em paralelo".
        System.out.println("Iniciando threads...");
        t1.start();
        t2.start();
    }
}