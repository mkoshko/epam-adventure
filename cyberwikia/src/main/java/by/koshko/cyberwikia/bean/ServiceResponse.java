package by.koshko.cyberwikia.bean;

import by.koshko.cyberwikia.controller.ServiceErrorsMapper;

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

    public List<String> errorList() {
        List<String> keys = new ArrayList<>();
        errors.forEach(error -> {
            keys.add(ServiceErrorsMapper.getLocalizedErrorKey(error));
        });
        return keys;
    }
}
