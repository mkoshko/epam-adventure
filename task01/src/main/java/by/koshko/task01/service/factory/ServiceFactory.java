package by.koshko.task01.service.factory;

import by.koshko.task01.service.PlanManagementService;
import by.koshko.task01.service.PlanCreatorService;
import by.koshko.task01.service.PlanCreatorServiceImpl;

public final class ServiceFactory {

    /**
     * Service factory instance.
     */
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();
    /**
     * Plan repository instance.
     */
    private final PlanFactory planFactory = new PlanFactoryImpl();
    /**
     * Plan creator service instance.
     */
    private final PlanCreatorService creatorService
            = new PlanCreatorServiceImpl();
    /**
     * Plan management service instance.
     */
    private final PlanManagementService planManagementService
            = PlanManagementService.getInstance();

    private ServiceFactory() {
    }

    /**
     * Returns {@code ServiceFactory} instance.
     * @return {@code ServiceFactory} instance.
     */
    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    /**
     * Returns {@code PlanManagementService} instance.
     * @return {@code PlanManagementService} instance.
     */
    public PlanManagementService getPlanManagementService() {
        return planManagementService;
    }

    /**
     * Returns {@code PlanFactory} instance.
     * @return {@code PlanFactory} instance.
     */
    public PlanFactory getPlanFactory() {
        return planFactory;
    }

    /**
     * Returns {@code PlanCreatorService} instance.
     * @return {@code PlanCreatorService} instance.
     */
    public PlanCreatorService getCreatorService() {
        return creatorService;
    }
}
