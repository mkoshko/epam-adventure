package task3.thread_priority;

public class PersonRunner extends Person implements Runnable {
    PersonRunner(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + " Hello, World");
        }
    }
}
