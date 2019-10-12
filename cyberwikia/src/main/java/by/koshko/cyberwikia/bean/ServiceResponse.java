package by.koshko.cyberwikia.bean;

import java.util.LinkedList;

public class ServiceResponse {

    private LinkedList<String> errors = new LinkedList<>();
    private boolean hasErrors = false;

    public ServiceResponse(final boolean hasErrors) {
        this.hasErrors = hasErrors;
    }
}
