package by.koshko.cyberwikia.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceResponse {

    private Set<String> errors = new HashSet<>();

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean addErrorMessage(final String msg) {
        return errors.add(msg);
    }

    public void clearErrors() {
        errors.clear();
    }

    public List<String> errorList() {
        return new ArrayList<>(errors);
    }
}
