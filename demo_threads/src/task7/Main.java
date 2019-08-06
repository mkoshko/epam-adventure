package task7;

//import task7._synchronized.Consumer;
//import task7._synchronized.Producer;
//import task7._synchronized.Store;

import task7.wait_notify.Consumer;
import task7.wait_notify.Producer;
import task7.wait_notify.Store;

public class Main {

    public static void main(String[] args) {
        Store store = new Store();
        Thread t1 = new Thread(new Consumer(store));
        Thread t2 = new Thread(new Producer(store));
        t1.start();
        t2.start();
    }

}
