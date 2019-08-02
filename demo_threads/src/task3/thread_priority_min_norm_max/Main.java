package task3.thread_priority_min_norm_max;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread min = new Thread(new Counter(), "min");
        min.setPriority(Thread.MIN_PRIORITY);
        Thread norm = new Thread(new Counter(), "norm");
        norm.setPriority(Thread.NORM_PRIORITY);
        Thread max = new Thread(new Counter(), "max");
        max.setPriority(Thread.MAX_PRIORITY);
        min.start();
        norm.start();
        max.start();
    }
}
