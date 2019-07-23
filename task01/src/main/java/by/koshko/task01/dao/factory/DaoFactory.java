package by.koshko.task01.dao.factory;

import by.koshko.task01.dao.repository.PlanRepository;
import by.koshko.task01.dao.repository.PlanRepositoryImpl;

public final class DaoFactory {
    /**
     * Instance of {@code DaoFactory}.
     */
    private static final DaoFactory DAO_FACTORY = new DaoFactory();
    /**
     * Instance of {@code PlanRepository}.
     */
    private final PlanRepository planRepository = new PlanRepositoryImpl();

    private DaoFactory() {
    }

    /**
     * Returns instance of {@code DaoFactory}.
     *
     * @return instance of {@code DaoFactory}.
     */
    public static DaoFactory getDaoFactory() {
        return DAO_FACTORY;
    }

    /**
     * Returns instance of {@code PlanRepository}.
     *
     * @return instance of {@code PlanRepository}.
     */
    public PlanRepository getPlanRepository() {
        return planRepository;
    }

}
