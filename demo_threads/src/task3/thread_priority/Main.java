package task3.thread_priority;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new PersonRunner("Alice"));
        Thread t2 = new Thread(new PersonRunner("Bob"));
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
