import java.util.LinkedList;
import java.util.List;

/**
 * Esta classe gerencia uma lista compartilhada que será acessada
 * por múltiplas threads (Produtor e Consumidor).
 * Ela utiliza mecanismos de sincronização para garantir que as operações
 * de adição e remoção sejam seguras (thread-safe).
 */
public class SharedList {

    private final List<Integer> list = new LinkedList<>();
    private final int CAPACITY = 5; // Define uma capacidade máxima para a lista

    // --- INÍCIO DA REGIÃO CRÍTICA ---
    // O método 'produce' é uma região crítica porque modifica o estado
    // do recurso compartilhado (a lista). O 'synchronized' garante que
    // apenas uma thread possa executar este método por vez neste objeto.
    public synchronized void produce(int value) throws InterruptedException {
        // Se a lista estiver cheia, a thread produtora deve esperar.
        // Usamos 'while' em vez de 'if' para nos proteger contra "despertares espúrios".
        while (list.size() == CAPACITY) {
            System.out.println("Lista cheia, thread Produtora (" + Thread.currentThread().getName() + ") esperando...");
            wait(); // Libera o lock e coloca a thread em estado de espera.
        }

        list.add(value);
        System.out.println("Produtor (" + Thread.currentThread().getName() + ") produziu -> " + value);

        // Notifica TODAS as threads que estão esperando (neste caso, as consumidoras)
        // que algo mudou no estado do objeto (um item foi adicionado).
        notifyAll();
    }
    // --- FIM DA REGIÃO CRÍTICA ---


    // --- INÍCIO DA REGIÃO CRÍTICA ---
    // O método 'consume' também é uma região crítica porque também modifica
    // o estado da lista. O 'synchronized' previne que a consumidora e a
    // produtora (ou outra consumidora) acessem a lista ao mesmo tempo.
    public synchronized int consume() throws InterruptedException {
        // Se a lista estiver vazia, a thread consumidora deve esperar.
        while (list.isEmpty()) {
            System.out.println("Lista vazia, thread Consumidora (" + Thread.currentThread().getName() + ") esperando...");
            wait(); // Libera o lock e aguarda a notificação da produtora.
        }

        // A remoção do item (list.remove(0)) é a operação principal.
        int value = list.remove(0);

        // Notifica TODAS as threads que estão esperando (neste caso, as produtoras)
        // que um espaço foi liberado na lista.
        notifyAll();
        return value;
    }
    // --- FIM DA REGIÃO CRÍTICA ---
}