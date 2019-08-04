package task6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Resource resource = null;
        try {
            resource = new Resource("data/out.txt");
            SyncThread t1 = new SyncThread("[Writer 1]-", resource);
            SyncThread t2 = new SyncThread("[Writer 2]-", resource);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }
}
