package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;

public interface UserService {

    User findByLogin(String login) throws ServiceException;
    User signIn(String login, String password) throws ServiceException;
    void saveUser(User user) throws ServiceException;

}
