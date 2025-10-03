import java.util.Random;

/**
 * Representa a thread T1 (Produtora).
 * Ela adiciona valores inteiros à lista compartilhada em um loop.
 */
public class Producer implements Runnable {

    private final SharedList sharedList;

    public Producer(SharedList sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // Produz 10 valores
                sharedList.produce(i);
                // Simula um tempo de processamento para produzir o próximo item
                Thread.sleep(new Random().nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread produtora interrompida.");
        }
    }
}