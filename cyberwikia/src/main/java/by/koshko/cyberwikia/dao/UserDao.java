package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.User;

public interface UserDao extends Dao<User> {
    User findByLogin(String login) throws DaoException;
    boolean hasLogin(String login) throws DaoException;
    boolean hasEmail(String email) throws DaoException;
}
