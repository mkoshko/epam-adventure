package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.UserDao;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.Properties;

public class UserServiceImpl extends AbstractService implements UserService {

    public UserServiceImpl() throws ServiceException {
        super();
    }

    public UserServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    @Override
    public User findByLogin(final String login) {
        return null;
    }

    public User signIn(final String login, final String password)
            throws ServiceException {
        try {
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            User user = userDao.findByLogin(login);
            if (user != null) {
                Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
                if (argon2.verify(user.getPassword(), password)) {
                    user.setPassword(null);
                    return user;
                } else {
                    return null;
                }
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException("User authorization error.");
        } finally {
            close();
        }
    }

    public void saveUser(final User user) throws ServiceException {
        if (user == null) {
            throw new ServiceException("User argument is null.");
        }
        try {
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            if (user.getId() == 0) {
                user.setPassword(Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id)
                        .hash(4, 1024 * 1024, 4, user.getPassword()));
                user.setRole(2);
                userDao.save(user);
            } else {
                userDao.update(user);
            }
        } catch (DaoException e) {
            getLogger().error(e.getMessage());
            throw new ServiceException("Cannot save the user.");
        } finally {
            getTransaction().close();
        }
    }
}
