package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;

public abstract class AdminCommand extends AbstractCommand {
    public AdminCommand() {
        getRoles().add(Role.ADMINISTRATOR);
    }
}
