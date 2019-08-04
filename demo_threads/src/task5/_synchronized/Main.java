package task5._synchronized;

public class Main {
    public static void main(String[] args) {
        CommonResource cr = new CommonResource();
        Thread t1 = new Thread(new Counter(cr), "Counter_1");
        Thread t2 = new Thread(new Counter(cr), "Counter_2");
        t1.start();
        t2.start();
    }
}
