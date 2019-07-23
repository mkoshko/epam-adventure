package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.validation.Validator;

import java.util.function.Function;

public class FindSpecificationBuilder {
    public enum SearchType {
        /**
         * Search by plan id.
         */
        FindByID,
        /**
         * Search by plan name.
         */
        FindByName,
        /**
         * Search plan with name start with given value.
         */
        NameStartWith,
        /**
         * Search by cost within network.
         */
        FindByCostWN,
        /**
         * Search by cost to other networks.
         */
        FindByCostON,
        /**
         * Search by cost abroad.
         */
        FindByCostAbroad,
        /**
         * Search by cost sms.
         */
        FindByCostSms,
        /**
         * Search by cost megabyte.
         */
        FindByCostMegabyte,
    }

    /**
     * {@code Specification} to be compiled.
     */
    private AbstractFindBySpecification<Plan> spec = null;

    /**
     * Builds specifications.
     *
     * @param type Type of specification.
     * @param args Arguments to pass into specification.
     * @throws ServiceException if some arguments for specification is invalid.
     */
    public final void compose(final SearchType type, final String... args)
            throws ServiceException {
        if (args.length == 0) {
            throw new ServiceException("No arguments.");
        }
        switch (type) {
            case FindByID:
                if (args.length == 1) {
                    findByID(args[0]);
                } else {
                    longBetween(Plan::getId, args);
                }
                break;
            case FindByName:
                findByName(args[0]);
                break;
            case NameStartWith:
                nameStartWith(args[0]);
                break;
            case FindByCostWN:
                if (args.length == 1) {
                    findByCostWN(args[0]);
                } else {
                    costBetween(Plan::getOutgoingWithinNetwork, args);
                }
                break;
            case FindByCostON:
                if (args.length == 1) {
                    findByCostON(args[0]);
                } else {
                    costBetween(Plan::getOutgoingOtherNetwork, args);
                }
                break;
            case FindByCostAbroad:
                if (args.length == 1) {
                    findByCostAbr(args[0]);
                } else {
                    costBetween(Plan::getOutgoingAbroad, args);
                }
                break;
            case FindByCostSms:
                if (args.length == 1) {
                    findByCostSms(args[0]);
                } else {
                    costBetween(Plan::getSmsCost, args);
                }
                break;
            case FindByCostMegabyte:
                if (args.length == 1) {
                    findByCostMB(args[0]);
                } else {
                    costBetween(Plan::getMegabyteCost, args);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Returns compiled specification.
     *
     * @return compiled specification.
     */
    public final AbstractFindBySpecification<Plan> build() {
        return spec;
    }

    private void findByID(final String arg) throws ServiceException {
        long id = checkLong(arg);
        spec = spec != null ? spec.and(new PlanIdSpecification(id))
                : new PlanIdSpecification(id);
    }

    private void longBetween(final Function<Plan, Long> f,
                             final String... args) throws ServiceException {
        checkLength(args);
        long min = checkLong(args[0]);
        long max = checkLong(args[1]);
        AbstractFindBySpecification<Plan> s
                = new PlanLongBetweenSpecification(
                min, max, f
        );
        spec = spec != null ? spec.and(s) : s;
    }

    private void findByName(final String arg) throws ServiceException {
        checkString(arg);
        spec = spec != null ? spec.and(new PlanNameSpecification(arg))
                : new PlanNameSpecification(arg);
    }

    private void nameStartWith(final String arg) throws ServiceException {
        checkString(arg);
        spec = spec != null ? spec.and(new PlanNameStartWithSpecification(arg))
                : new PlanNameStartWithSpecification(arg);
    }

    private void findByCostWN(final String arg) throws ServiceException {
        double val = checkDouble(arg);
        spec = spec != null ? spec.and(new PlanCostInSpecification(val))
                : new PlanCostInSpecification(val);
    }

    private void findByCostON(final String arg) throws ServiceException {
        double val = checkDouble(arg);
        spec = spec != null ? spec.and(new PlanCostOtherSpecification(val))
                : new PlanCostOtherSpecification(val);
    }

    private void findByCostAbr(final String arg) throws ServiceException {
        double val = checkDouble(arg);
        spec = spec != null ? spec.and(new PlanCostAbroadSpecification(val))
                : new PlanCostAbroadSpecification(val);
    }

    private void findByCostSms(final String arg) throws ServiceException {
        double val = checkDouble(arg);
        spec = spec != null ? spec.and(new PlanCostSmsSpecification(val))
                : new PlanCostSmsSpecification(val);
    }

    private void findByCostMB(final String arg) throws ServiceException {
        double val = checkDouble(arg);
        spec = spec != null ? spec.and(new PlanCostMegabyteSpecification(val))
                : new PlanCostMegabyteSpecification(val);
    }

    private void costBetween(final Function<Plan, Double> f,
                             final String... args) throws ServiceException {
        checkLength(args);
        double min = checkDouble(args[0]);
        double max = checkDouble(args[1]);
        AbstractFindBySpecification<Plan> s
                = new PlanDoubleBetweenSpecification(
                min, max, f
        );
        spec = spec != null ? spec.and(s) : s;
    }

    private void checkLength(final String[] args) throws ServiceException {
        if (args.length != 2) {
            throw new ServiceException("Not enough arguments."
                    + " Min and max value required");
        }
    }

    private long checkLong(final String arg) throws ServiceException {
        if (Validator.validateLong(arg)) {
            return Long.valueOf(arg);
        } else {
            throw new ServiceException("Invalid parameter. Number required.");
        }
    }

    private double checkDouble(final String arg) throws ServiceException {
        if (Validator.validateDouble(arg)) {
            return Double.valueOf(arg);
        } else {
            throw new ServiceException("Invalid parameter. Number required.");
        }
    }

    private void checkString(final String arg) throws ServiceException {
        if (arg == null || arg.length() == 0) {
            throw new ServiceException("Empty string");
        }
    }
}
