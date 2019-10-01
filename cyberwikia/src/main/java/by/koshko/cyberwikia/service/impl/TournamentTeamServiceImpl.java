package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentTeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.TournamentTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.TOURNAMENTTEAMDAO;

public class TournamentTeamServiceImpl extends AbstractService implements TournamentTeamService {

    private Logger logger = LogManager.getLogger(TournamentTeamServiceImpl.class);

    public TournamentTeamServiceImpl() throws ServiceException {
    }

    public TournamentTeamServiceImpl(final Transaction externalTransaction) {
        super(externalTransaction);
    }

    public List<TournamentTeam> findTournamentsForTeam(final Team team)
            throws ServiceException {
        if (team == null) {
            logger.warn("Method argument 'team' is null."
                        + " Empty 'TournamentTeam' list will returned.");
            return new ArrayList<>();
        }
        try {
            Transaction transaction = getTransaction();
            TournamentTeamDao tournamentTeamDao
                    = transaction.getDao(TOURNAMENTTEAMDAO);
            return tournamentTeamDao.findTournamentTeam(team);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get tournament list for team.");
        } finally {
            close();
        }
    }
}
