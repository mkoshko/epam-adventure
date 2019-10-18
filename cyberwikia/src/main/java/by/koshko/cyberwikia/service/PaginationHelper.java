package by.koshko.cyberwikia.service;

public class PaginationHelper {

    /**
     * Private constructor.
     */
    private PaginationHelper() {
    }
    /**
     * Calculates offset for database query.
     *
     * @param page  Page number.
     * @param limit Limit of records to show on page.
     * @return Index of the record from which selection will start.
     */
    public static int calculateOffset(final int page, final int limit) {
        if (page <= 0) {
            return 0;
        }
        if (page == 1) {
            return 0;
        } else {
            return (page - 1) * limit;
        }
    }
}
