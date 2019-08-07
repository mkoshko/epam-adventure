package task7._synchronized;

public class Store {
    private int stored;
    private final int capacity = 5;

    public synchronized int getProduct() {
        if (stored > 0) {
            stored--;
            return 1;
        } else {
            return 0;
        }
    }

    public synchronized int putProduct() {
        if (stored < capacity) {
            stored++;
            return 1;
        } else {
            return 0;
        }
    }
}
