package task7._synchronized;

public class Producer extends Thread {
    Store store;
    int products = 5;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            while (products > 0) {
                products -= store.putProduct();
                System.out.println("Производителю осталось добавить " + products + " товаров");
                sleep(300);
            }
        } catch (InterruptedException e) {
            System.out.println("поток производителя прерван");
        }
    }
}
