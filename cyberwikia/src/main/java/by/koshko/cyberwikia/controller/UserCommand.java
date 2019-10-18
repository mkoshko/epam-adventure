package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;

public abstract class UserCommand extends AbstractCommand {
    public UserCommand() {
        getRoles().add(Role.USER);
    }
}
