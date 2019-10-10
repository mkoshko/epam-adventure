package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;

public abstract class ModeratorCommand extends AbstractCommand {

    public ModeratorCommand() {
        getRoles().add(Role.EVENT_MODERATOR);
    }
}
