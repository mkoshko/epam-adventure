package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Role;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.UserDao;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.UserService;
import by.koshko.cyberwikia.service.validation.UserValidator;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl extends AbstractService implements UserService {

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    private static final int ITERATION = 4;
    private static final int MEMORY = 1024 * 1024;
    private static final int THREADS = 4;
    private static final Role DEFAULT_ROLE = Role.USER;

    public UserServiceImpl(final Transaction transaction,
                           final ServiceFactory factory) {
        super(transaction, factory);
    }

    public User signIn(final String login, final String password)
            throws ServiceException {
        try {
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            User user = userDao.findByLogin(login);
            if (user != null && argon2.verify(user.getPassword(), password)) {
                user.setPassword(null);
                return user;
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException("User authorization error.");
        }
    }

    public ServiceResponse sighUp(final User user) throws ServiceException {
        UserValidator userValidator = ValidationFactory.getUserValidator();
        try {
            ServiceResponse response = new ServiceResponse();
            if (!userValidator.test(user, true)) {
                logger.debug("Invalid user parameters.");
                response.addErrorMessage("registration.generic-error");
                return response;
            }
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            if (userDao.hasLogin(user.getLogin())) {
                response.addErrorMessage("duplicate.login");
            }
            if (userDao.hasEmail(user.getEmail())) {
                response.addErrorMessage("duplicate.email");
            }
            if (response.hasErrors()) {
                return response;
            }
            user.setPassword(argon2
                    .hash(ITERATION, MEMORY, THREADS, user.getPassword()));
            user.setRole(DEFAULT_ROLE.ordinal());
            if (userDao.save(user)) {
                return response;
            } else {
                response.addErrorMessage("registration.generic-error");
                return response;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot save the user.", e);
        }
    }

    public int update(final User user) throws ServiceException {
        UserValidator userValidator = ValidationFactory.getUserValidator();
        try {
            if (!userValidator.test(user, false)) {
                logger.debug("Invalid user parameters.");
                return -1;
            }
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            if (userDao.update(user)) {
                return 0;
            }
            return -1;
        } catch (DaoException e) {
            throw new ServiceException("Cannot update user.", e);
        }
    }

    public boolean updatePassword(final User user, final String oldPass)
            throws ServiceException {
        UserValidator userValidator = ValidationFactory.getUserValidator();
        try {
            if (!userValidator.test(user, true)) {
                logger.debug("Invalid user parameters.");
                return false;
            }
            UserDao userDao = getTransaction().getDao(DaoTypes.USERDAO);
            User oldUser = userDao.get(user.getId());
            if (argon2.verify(oldUser.getPassword(), oldPass)) {
                String newPassword = argon2.hash(ITERATION, MEMORY, THREADS,
                        user.getPassword());
                user.setPassword(newPassword);
                return userDao.update(user);
            }
            return false;
        } catch (DaoException e) {
            throw new ServiceException("Cannot update user", e);
        }
    }
}
