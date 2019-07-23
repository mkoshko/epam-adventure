package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.ServiceException;

import java.util.function.Function;

public class SortSpecificationBuilder {
    public enum SortBy {
        /**
         * Sorting by plan ID.
         */
        Id,
        /**
         * Sorting by plan name.
         */
        Name,
        /**
         * Sorting by cost within network.
         */
        CostIN,
        /**
         * Sorting by cost to other networks.
         */
        CostON,
        /**
         * Sorting by cost abroad.
         */
        CostAbroad,
        /**
         * Sorting by cost sms.
         */
        CostSMS,
        /**
         * Sorting by cost megabyte.
         */
        CostMegabyte
    }

    /**
     * Builds {@code PlanSortSpecification}.
     *
     * @param args Contains parameters which uses for compile sort
     *             specification.
     * @return compiled {@code PlanSortSpecification}.
     * @throws ServiceException if some arguments for specification is invalid.
     */
    public PlanSortSpecification build(final SortBy... args)
            throws ServiceException {
        if (args.length == 0 || args.length > 2) {
            throw new ServiceException("Number of arguments must be 1 or 2.");
        }
        Function<Plan, Comparable>[] f = new Function[args.length];
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case Id:
                    f[i] = Plan::getId;
                    break;
                case Name:
                    f[i] = Plan::getPlanName;
                    break;
                case CostIN:
                    f[i] = Plan::getOutgoingWithinNetwork;
                    break;
                case CostON:
                    f[i] = Plan::getOutgoingOtherNetwork;
                    break;
                case CostAbroad:
                    f[i] = Plan::getOutgoingAbroad;
                    break;
                case CostSMS:
                    f[i] = Plan::getSmsCost;
                    break;
                case CostMegabyte:
                    f[i] = Plan::getMegabyteCost;
                    break;
                default:
                    break;
            }
        }
        if (args.length == 1) {
            return new PlanSortSpecification(f[0]);
        } else {
            return new PlanSortSpecification(f[0], f[1]);
        }
    }
}
