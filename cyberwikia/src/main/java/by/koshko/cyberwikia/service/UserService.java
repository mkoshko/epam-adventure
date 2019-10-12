package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.User;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;
    int sighUp(User user) throws ServiceException;
    int update(User user) throws ServiceException;
    boolean updatePassword(User user, String oldPass) throws ServiceException;
}
