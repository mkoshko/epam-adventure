package by.koshko.task01.service;

import by.koshko.task01.dao.factory.DaoFactory;
import by.koshko.task01.dao.repository.PlanRepository;
import by.koshko.task01.dao.writer.PlanDescriptionToFileWriter;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.specification.Specification;

import java.io.IOException;
import java.util.List;

public final class PlanManagementServiceImpl
        implements PlanManagementService, Subscriber {
    /**
     * Instance of {@code PlanManagementServiceImpl}.
     */
    private static final PlanManagementServiceImpl INSTANCE
            = new PlanManagementServiceImpl();
    /**
     * {@code PlanRepository} for access to REPOSITORY.
     */
    private static final PlanRepository REPOSITORY = DaoFactory.getDaoFactory()
            .getPlanRepository();
    /**
     * Indicates if service subscribed to REPOSITORY updates.
     */
    private static boolean subscribed = false;
    /**
     * Total number of customers.
     */
    private int totalNumberOfCustomers = 0;
    /**
     * Contains {@code Plan} which obtained after latest query.
     */
    private List<Plan> queried;

    static {
        init();
    }

    /**
     * Creates {@code PlanManagementServiceImpl} object.
     */
    private PlanManagementServiceImpl() {
    }

    private static void init() {
        if (!subscribed) {
            if (REPOSITORY instanceof Publisher) {
                ((Publisher) REPOSITORY).subscribe(INSTANCE);
                subscribed = true;
            }
        }
    }

    /**
     * Returns instance of this {@code PlanManagementServiceImpl}.
     *
     * @return {@code PlanManagementServiceImpl} instance.
     */
    public static PlanManagementServiceImpl getInstance() {
        return INSTANCE;
    }

    /**
     * Returns total number of customers.
     *
     * @return total number of customers.
     */
    public int getTotalNumberOfCustomers() {
        return totalNumberOfCustomers;
    }

    /**
     * Returns all existed {@code Plan} objects.
     *
     * @return {@code List} with {@code Plan} objects.
     * @throws ServiceException if {@code PlanRepository} is {@code null}.
     */
    public List<Plan> receiveAllAvailablePlans() throws ServiceException {
        if (REPOSITORY != null) {
            queried = REPOSITORY.fetchAll();
            return queried;
        } else {
            throw new ServiceException("Repository is unavailable");
        }
    }

    /**
     * Returns {@code List} of {@code Plan} according to the specification.
     *
     * @param specification {@code Specification} using for making selection
     *                      from {@code PlanRepository}.
     * @return {@code List} of {@code Plan}.
     * @throws ServiceException if {@code PlanRepository} is {@code null}.
     */
    public List<Plan> queryToPlanRepository(final Specification specification)
            throws ServiceException {
        if (REPOSITORY != null) {
            queried = REPOSITORY.query(specification);
            return queried;
        } else {
            throw new ServiceException("Repository is unavailable");
        }
    }

    /**
     * Removes all {@code Plan} objects from last query.
     *
     * @return {@code true} if {@code Plan} was removed,
     * {@code false} otherwise.
     * @throws ServiceException If {@link #queried} is null.
     */
    public boolean remove() throws ServiceException {
        if (queried == null) {
            throw new ServiceException("Make query of list of a plans first");
        }
        boolean res = REPOSITORY.remove(queried);
        queried = null;
        return res;
    }

    /**
     * Removes {@code Plan} with specified index in {@link #queried} from
     * repository.
     *
     * @param index Index of a plan in {@link #queried} to be removed from
     *              repository.
     * @return {@code true} if {@code Plan} was removed,
     * {@code false} otherwise.
     * @throws ServiceException If {@link #queried} is null
     *                          or wrong index value.
     */
    public boolean remove(final int index) throws ServiceException {
        if (queried == null) {
            throw new ServiceException("Make query of list of a plans first");
        }
        if (REPOSITORY == null) {
            throw new ServiceException("Repository is unavailable");
        }
        if (index > queried.size() - 1 || index < 0) {
            throw new ServiceException("Invalid index");
        }
        boolean res = REPOSITORY.remove(queried.get(index));
        queried = null;
        return res;
    }

    /**
     * Removes {@code Plan} with specified index in {@link #queried} from
     * repository.
     *
     * @param leftIndex  low endpoint (inclusive) of the subList
     * @param rightIndex high endpoint (exclusive) of the subList.
     * @return {@code true} if {@code Plan} from specified range was removed,
     * {@code false} otherwise.
     * @throws ServiceException If {@link #queried} is null
     *                          or wrong indexes values.
     */
    public boolean remove(final int leftIndex, final int rightIndex)
            throws ServiceException {
        if (queried == null) {
            throw new ServiceException("Make query of list of a plans first");
        }
        if (REPOSITORY == null) {
            throw new ServiceException("Repository is unavailable");
        }
        if (leftIndex > rightIndex
                || leftIndex < 0
                || (rightIndex - leftIndex) > queried.size()) {
            throw new ServiceException("Invalid range of indexes");
        }
        boolean res = REPOSITORY.remove(queried.subList(leftIndex, rightIndex));
        queried = null;
        return res;
    }

    /**
     * Writes queried plans to a specified file.
     * @param path to a file.
     * @return {@code true} if report was successfully wrote to a file.
     * @throws ServiceException if some exceptions occurs during
     *                          writing a report.
     */
    public boolean report(final String path) throws ServiceException {
        if (queried == null) {
            throw new ServiceException("Make query of list of a plans first");
        }
        try {
            PlanDescriptionToFileWriter writer
                    = new PlanDescriptionToFileWriter(path);
            writer.write(queried);
            return true;
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Invokes from {@link #onUpdate(Object)} method when {@code PlanRepository}
     * have new {@code Plan} objects.
     */
    private void updateSubscriptions() {
        REPOSITORY.fetchAll().forEach(plan -> plan.subscribe(this));
    }

    /**
     * Callback method that invokes from {@code Publisher} when some updates
     * occurs.
     */
    @Override
    public void onUpdate(final Object o) {
        if (o instanceof PlanRepository) {
            updateSubscriptions();
            recalculateCustomers();
        } else {
            recalculateCustomers();
        }
    }

    private void recalculateCustomers() {
        int newValue = 0;
        List<Plan> plans = REPOSITORY.fetchAll();
        for (Plan p : plans) {
            newValue += p.getNumberOfUsers();
        }
        totalNumberOfCustomers = newValue;
    }
}
