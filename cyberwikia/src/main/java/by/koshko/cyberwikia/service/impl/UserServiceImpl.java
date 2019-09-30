package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Role;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.UserDao;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.UserService;
import by.koshko.cyberwikia.service.validation.UserValidator;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class UserServiceImpl extends AbstractService implements UserService {

    private Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    private static final int ITERATION = 4;
    private static final int MEMORY = 1024 * 1024;
    private static final int THREADS = 4;
    private static final Role DEFAULT_ROLE = Role.USER;

    public UserServiceImpl() throws ServiceException {
        super();
    }

    public UserServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public User signIn(final String login, final String password)
            throws ServiceException {
        try {
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            User user = userDao.findByLogin(login);
            if (user != null) {
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

    public void create(final User user) throws ServiceException {
        getLogger().debug("service start");
        if (!UserValidator.test(user, true)) {
            throw new ServiceException("Invalid user parameters.");
        }
        getLogger().debug("validation passed");
        try {
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            getLogger().debug("preparing to hashing password");
            user.setPassword(argon2
                    .hash(ITERATION, MEMORY, THREADS, user.getPassword()));
            user.setRole(DEFAULT_ROLE.ordinal());
            getLogger().debug("saving user");
            userDao.save(user);
            getLogger().debug("saved.");
        } catch (DaoException e) {
            getLogger().error(e.getMessage());
            throw new ServiceException("Cannot save the user.");
        } finally {
            close();
        }
    }

    public void update(final User user) throws ServiceException {
        if (!UserValidator.test(user, false)) {
            throw new ServiceException("Invalid user parameters.");
        }
        try {
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            userDao.update(user);
            getLogger().debug("User '{}' was successfully updated.", user.getLogin());
        } catch (DaoException e) {
            getLogger().error("Cannot update user. {}", e.getMessage());
            throw new ServiceException("Cannot update user.");
        } finally {
            close();
        }
    }

    public void updatePassword(final User user, final String oldPass)
            throws ServiceException {
        if (!UserValidator.test(user, true)) {
            throw new ServiceException("Invalid user parameters.");
        }
        try {
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);

        } catch (DaoException e) {

        } finally {
            close();
        }
    }
}
