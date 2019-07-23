package by.koshko.task01.service;

public interface Publisher {
    /**
     * Adds {@code Subscriber} to list of subscribers.
     * @param subscriber {@code Subscriber} to be subscribe for updates.
     * @return {@code true} if {@code Subscriber} was successfully added,
     *         {@code false} otherwise.
     */
    boolean subscribe(Subscriber subscriber);
    /**
     * Removes {@code Subscriber} from list of subscribers.
     * @param subscriber {@code Subscriber} to be removed from list of
     * @return {@code true} if {@code Subscriber} was successfully removed,
     *         {@code false} otherwise.
     */
    boolean unsubscribe(Subscriber subscriber);
    /**
     * Notifies all {@code Subscriber}.
     */
    void notifySubscribers();
}
