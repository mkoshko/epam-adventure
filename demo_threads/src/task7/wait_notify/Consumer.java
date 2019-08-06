package task7.wait_notify;

public class Consumer extends Thread {
    Store store;
    int product = 0;
    final int N = 5;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            store.getProduct();
        }
    }
}
