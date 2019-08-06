package task7._synchronized;

public class Consumer extends Thread {
    Store store;
    int product = 0;
    final int N = 5;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            while(product < N) {
                product += store.getProduct();
                System.out.println("потребитель купил " + product + " товаров");
                sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("поток потребителя прерван");
        }
    }
}
