package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    User findByLogin(String login) throws DaoException;
}
