package task7._stampedlock;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        CommonResource resource = new CommonResource();
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 3; i++) {
            service.execute(() -> {
                while(true) {
                    System.out.println("value get by " + Thread.currentThread().getName() + " " + resource.getI());
                    try {
                        TimeUnit.MILLISECONDS.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.execute(() -> {
            while(true) {
                resource.putI();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
