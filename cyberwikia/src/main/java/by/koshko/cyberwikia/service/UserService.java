package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;
    void sighUp(User user) throws ServiceException;
    void update(User user) throws ServiceException;
}
