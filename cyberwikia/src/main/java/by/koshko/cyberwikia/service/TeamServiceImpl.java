package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.TeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.TransactionImpl;

import java.util.Optional;

public class TeamServiceImpl extends AbstractService {

    public TeamServiceImpl() throws ServiceException {
        super();
    }

    public TeamServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Team findTeamById(final long id) throws ServiceException {
        try {
            Transaction transaction = new TransactionImpl();
            TeamDao teamDao = transaction.getDao(DaoTypes.TEAMDAO);
            Optional<Team> team = teamDao.get(id);
            return team.orElse(null);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
