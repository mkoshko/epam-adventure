package task6;

public class SyncThread extends Thread {
    Resource resource;

    public SyncThread(String name, Resource resource) {
        super(name);
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.write(getName(), i);
        }
    }
}
