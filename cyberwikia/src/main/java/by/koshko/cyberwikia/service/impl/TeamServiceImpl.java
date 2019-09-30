package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.TeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.mysql.TransactionImpl;
import by.koshko.cyberwikia.service.GameService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TeamServiceImpl extends AbstractService implements TeamService {

    private Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl() throws ServiceException {
        super();
    }

    public TeamServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Team findTeamById(final long id) throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getDao(DaoTypes.TEAMDAO);
            GameService gs = new GameServiceImpl(getTransaction());
            Team team = teamDao.get(id);
            team.setGame(gs.findById(team.getGame().getId()));
            return team;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            close();
        }
    }

    public List<Team> findAll() throws ServiceException {
        try {
            Transaction transaction = new TransactionImpl();
            TeamDao teamDao = transaction.getDao(DaoTypes.TEAMDAO);
            return teamDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            close();
        }
    }

}
