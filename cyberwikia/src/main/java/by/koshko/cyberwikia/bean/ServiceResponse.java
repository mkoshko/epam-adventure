package by.koshko.cyberwikia.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceResponse {

    private Set<EntityError> errors = new HashSet<>();

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean addErrorMessage(final EntityError error) {
        return errors.add(error);
    }

    public void clearErrors() {
        errors.clear();
    }

    public List<EntityError> errorList() {
        return new ArrayList<>(errors);
    }
}
