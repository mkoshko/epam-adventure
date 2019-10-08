package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;

import java.util.Arrays;

public abstract class UserCommand extends AbstractCommand {
    public UserCommand() {
        getRoles().addAll(Arrays.asList(Role.values()));
    }
}
