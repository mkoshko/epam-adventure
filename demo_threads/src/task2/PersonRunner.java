package task2;

import java.util.concurrent.TimeUnit;

public class PersonRunner extends Person implements Runnable {
    PersonRunner(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + " Hello, World");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
