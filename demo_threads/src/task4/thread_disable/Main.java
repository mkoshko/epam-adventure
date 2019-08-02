package task4.thread_disable;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ForrestGump forrestGump = new ForrestGump();
        Thread t1 = new Thread(forrestGump, "Gump");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
            forrestGump.disable();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread is done. Good bye.");
    }
}
