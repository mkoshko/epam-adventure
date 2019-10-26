package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.EntityError;
import by.koshko.cyberwikia.bean.Role;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
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

/**
 * Class contains method for working with {@code User} objects.
 */
public final class UserServiceImpl extends AbstractService
        implements UserService {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    /**
     * Object for password hashing.
     */
    private static Argon2 argon2 = Argon2Factory
            .create(Argon2Factory.Argon2Types.ARGON2id);
    /**
     * Number of iterations.
     */
    private static final int ITERATION = 4;
    /**
     * Memory usage (kibibytes).
     */
    private static final int MEMORY = 1024 * 1024;
    /**
     * Number of threads and compute lanes.
     */
    private static final int THREADS = 4;
    /**
     * Default role for every newly created {@code User}.
     */
    private static final Role DEFAULT_ROLE = Role.USER;

    /**
     * Constructor which used by {@code ServiceFactory} if we want to obtain
     * some instance of any service with the same {@code Connection}.
     *
     * @param transaction object for obtaining {@code Dao} objects with the
     *                    same {@code Connection}.
     * @param factory     Factory for obtaining services with the same
     *                    {@code Connection}.
     */
    public UserServiceImpl(final Transaction transaction,
                           final ServiceFactory factory) {
        super(transaction, factory);
    }

    @Override
    public User signIn(final String login, final String password)
            throws ServiceException {
        try {
            UserDao userDao = getTransaction().getUserDao();
            User user = userDao.findByLogin(login);
            if (user != null
                && password != null
                && argon2.verify(user.getPassword(), password)) {
                user.setPassword(null);
                return user;
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException("User authorization error.", e);
        }
    }

    @Override
    public ServiceResponse sighUp(final User user) throws ServiceException {
        UserValidator userValidator = ValidationFactory.getUserValidator();
        try {
            ServiceResponse response = new ServiceResponse();
            if (!userValidator.test(user, true)) {
                logger.debug("Invalid user parameters.");
                response.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
                return response;
            }
            UserDao userDao = getTransaction().getUserDao();
            if (userDao.hasLogin(user.getLogin())) {
                response.addErrorMessage(EntityError.DUPLICATE_LOGIN);
            }
            if (userDao.hasEmail(user.getEmail())) {
                response.addErrorMessage(EntityError.DUPLICATE_EMAIL);
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
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot save the user.", e);
        }
    }

    @Override
    public User get(final long userId) throws ServiceException {
        try {
            UserDao userDao = getTransaction().getUserDao();
            return userDao.get(userId);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get user by id.", e);
        }
    }

    @Override
    public ServiceResponse updatePassword(final long userId,
                                          final String oldPassword,
                                          final String newPassword) {
        ServiceResponse response = new ServiceResponse();
        UserValidator userValidator = ValidationFactory.getUserValidator();
        if (!userValidator.testPassword(newPassword)) {
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
        try {
            UserDao userDao = getTransaction().getUserDao();
            User user = userDao.get(userId);
            if (argon2.verify(user.getPassword(), oldPassword)) {
                String newHash = argon2.hash(ITERATION, MEMORY, THREADS,
                        newPassword);
                user.setPassword(newHash);
                userDao.update(user);
                return response;
            } else {
                response.addErrorMessage(EntityError.INVALID_PASSWORD);
                return response;
            }
        } catch (DaoException e) {
            logger.error("Cannot update user password. {}",
                    e.getCause().getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
    }
}
