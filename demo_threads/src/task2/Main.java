package task2;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new PersonRunner("Alice"));
        Thread t2 = new Thread(new PersonRunner("Bob"));
        t1.start();
        t2.start();
    }
}
