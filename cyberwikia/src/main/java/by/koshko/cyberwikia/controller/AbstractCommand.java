package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCommand {
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> newRoles) {
        roles = newRoles;
    }

    abstract void execute(HttpServletRequest request, HttpServletResponse response);
}
