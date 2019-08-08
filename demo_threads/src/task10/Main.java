package task10;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put("String " + i);
                    System.out.println("element " + i + " added");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println("Element " + queue.poll() + " took");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
