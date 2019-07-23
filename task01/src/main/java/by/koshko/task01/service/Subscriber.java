package by.koshko.task01.service;

public interface Subscriber {
    /**
     * Callback method which invokes by {@code Publisher}.
     * @param o {@code Publisher} object which invoked the method.
     */
    void onUpdate(Object o);
}
