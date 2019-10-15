package by.koshko.cyberwikia.controller;

import javax.servlet.http.HttpServletRequest;

public class Pagination {

    private static final int LIMIT = 10;

    public static int getLimit() {
        return LIMIT;
    }

    //TODO rename method.
    public static int makePagination(final HttpServletRequest request,
                                         final int total) {
        try {
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int lastPage = calculateLastPage(total);
            if (page <= lastPage && page > 0) {
                request.setAttribute("lastPage", lastPage);
                request.setAttribute("page", page);
                return page;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }

    }

    private static int calculateLastPage(final int total) {
        if (total % LIMIT == 0) {
            return total / LIMIT;
        } else {
            return total / LIMIT + 1;
        }
    }
}
