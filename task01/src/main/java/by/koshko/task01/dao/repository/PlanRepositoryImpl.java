package by.koshko.task01.dao.repository;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.Publisher;
import by.koshko.task01.service.Subscriber;
import by.koshko.task01.service.specification.FindBySpecification;
import by.koshko.task01.service.specification.SortBySpecification;
import by.koshko.task01.service.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlanRepositoryImpl implements PlanRepository, Publisher {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(this);
    /**
     * Internal super-modern database.
     */
    private static final List<Plan> DATABASE = new LinkedList<>();
    /**
     * List of {@code Subscriber} objects which will notified if some
     * updates occurs.
     */
    private final List<Subscriber> subscribers = new LinkedList<>();

    /**
     * Adds {@code Plan} object to database.
     *
     * @param plan {@code Plan} to be added to repository.
     * @return {@code true} if object successfully added,
     * {@code false} otherwise.
     */
    public boolean add(final Plan plan) {
        if (plan == null) {
            return false;
        }
        DATABASE.add(plan);
        logger.info("Plan added to repository");
        notifySubscribers();
        return true;
    }

    /**
     * Add {@code Collection} of {@code Plan} to database.
     *
     * @param plans {@code Plan}'s to be added to repository.
     * @return {@code true} if objects successfully added,
     * {@code false} otherwise.
     */
    public boolean add(final List<? extends Plan> plans) {
        if (plans == null) {
            return false;
        }
        DATABASE.addAll(plans);
        logger.info("added " + plans.size() + " plan(s) to repository");
        notifySubscribers();
        return true;
    }

    /**
     * Removes {@code Plan} object from repository.
     *
     * @param plan {@code Plan} to be removed from repository.
     * @return {@code true} if object successfully removed,
     * {@code false} otherwise.
     */
    public boolean remove(final Plan plan) {
        if (plan == null) {
            return false;
        }
        if (DATABASE.remove(plan)) {
            notifySubscribers();
            return true;
        }
        return false;
    }

    /**
     * Removes all {@code Plan} objects from given {@code List} from database.
     *
     * @param plans {@code List} with plans to be removed from repository.
     * @return {@code true} if objects successfully removed,
     * {@code false} otherwise.
     */
    public boolean remove(final List<? extends Plan> plans) {
        if (plans == null) {
            return false;
        }
        if (DATABASE.removeAll(plans)) {
            notifySubscribers();
            return true;
        }
        return false;
    }

    /**
     * Returns {@code List} with all {@code Plan} objects from database.
     *
     * @return all {@code Plan} objects from database.
     */
    public List<Plan> fetchAll() {
        return new ArrayList<>(DATABASE);
    }

    /**
     * Returns {@code List} with {@code Plan} objects that satisfied to all
     * conditions from given {@code Specification}.
     *
     * @param specification Objects contains conditions that should be met by
     *                      an {@code Plan} objects.
     * @return {@code List} with {@code Plan} objects that satisfied to given
     * {@code Specification}.
     */
    @SuppressWarnings("unchecked")
    public List<Plan> query(final Specification specification) {
        if (specification instanceof FindBySpecification) {
            List<Plan> list = new ArrayList<>();
            FindBySpecification spec = (FindBySpecification) specification;
            for (Plan plan : DATABASE) {
                if (spec.isSatisfiedBy(plan)) {
                    list.add(plan);
                }
            }
            return list;
        } else {
            ((SortBySpecification) specification).sort(DATABASE);
            return new ArrayList<>(DATABASE);
        }
    }

    /**
     * Adds {@code Subscriber} to {@link #subscribers}.
     *
     * @param subscriber {@code Subscriber} to be added to {@link #subscribers}.
     * @return {@code true} if {@code Subscriber} was successfully added,
     * {@code false} otherwise.
     */
    @Override
    public boolean subscribe(final Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            logger.info(subscriber
                    + " subscribed for plan repository updates");
            return subscribers.add(subscriber);
        }
        return false;
    }

    /**
     * Removes {@code Subscriber} from {@link #subscribers}.
     *
     * @param subscriber {@code Subscriber} to be removed from
     *                   {@link #subscribers}.
     * @return {@code true} if {@code Subscriber} was successfully removed,
     * {@code false} otherwise.
     */
    @Override
    public boolean unsubscribe(final Subscriber subscriber) {
        if (subscribers.remove(subscriber)) {
            logger.info(subscriber.getClass().getName()
                    + " just unsubscribed for plan repository updates");
            return true;
        }
        return false;
    }

    /**
     * Notifies all {@code Subscriber} from {@link #subscribers}.
     */
    @Override
    public void notifySubscribers() {
        subscribers.forEach(subscriber -> subscriber.onUpdate(this));
    }
}
