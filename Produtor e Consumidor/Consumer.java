import java.util.Random;

/**
 * Representa a thread T2 (Consumidora).
 * Ela remove e imprime valores da lista compartilhada em um loop.
 */
public class Consumer implements Runnable {

    private final SharedList sharedList;

    public Consumer(SharedList sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // Consome os 10 valores
                int value = sharedList.consume();
                System.out.println("Consumidor (" + Thread.currentThread().getName() + ") consumiu <- " + value);
                 // Simula um tempo de processamento do item consumido
                Thread.sleep(new Random().nextInt(1500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread consumidora interrompida.");
        }
    }
}