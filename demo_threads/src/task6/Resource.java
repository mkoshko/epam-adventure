package task6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Resource {
    private FileOutputStream writer;

    public Resource(String path) throws IOException {
        writer = new FileOutputStream(path);
    }

    public synchronized void write(String str, int i) {
        try {
            writer.write((str + i).getBytes());
            System.out.println(str + i);
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 50));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
