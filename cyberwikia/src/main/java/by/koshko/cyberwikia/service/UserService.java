package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.User;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;
    boolean sighUp(User user) throws ServiceException;
    void update(User user) throws ServiceException;
}
