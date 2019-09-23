package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.UserDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

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
            UserDao userDao = transaction.getDao(DaoTypes.USERDAO);
            Optional<User> user = userDao.findByLogin(login);
            if (user.isPresent()) {
                User u = user.get();
                Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
                if (argon2.verify(u.getPassword(), password)) {
                    return u;
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
}
