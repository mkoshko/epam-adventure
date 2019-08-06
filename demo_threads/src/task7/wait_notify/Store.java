package task7.wait_notify;

public class Store {
    private int stored = 0;
    private final int capacity = 3;

    public synchronized void getProduct() {
        try {
            while(stored < 1) {
                wait();
            }
            stored--;
            System.out.println("покупатель купил 1 товар");
            System.out.println("товаров на складе " + stored);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void putProduct() {
        try {
            while(stored == capacity) {
                wait();
            }
            stored++;
            System.out.println("производитель добавил 1 товар на склад");
            System.out.println("товаров на складе " + stored);
            notify();
        } catch (InterruptedException e) {
            System.out.println("что-то пошло не так");
        }
    }
}
