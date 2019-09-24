package by.koshko.cyberwikia.main;

public class Main {
    long id;

    public long getId() {
        return id;
    }

    public static void main(String[] args) {
        Main main = new Main();
        long id = main.getId();
        System.out.println(id);
    }

}
