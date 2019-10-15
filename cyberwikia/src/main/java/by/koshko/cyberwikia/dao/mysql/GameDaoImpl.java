package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.GameDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class GameDaoImpl extends AbstractDao implements GameDao {
    private static final String GET
            = "SELECT id, title, icon_file FROM game WHERE id=?;";
    private static final String GET_ALL
            = "SELECT id, title, icon_file FROM game;";
    private static final String SAVE
            = "INSERT INTO game (title, icon_file) VALUES (?, ?)";
    private static final String UPDATE
            = "UPDATE game SET title=?, icon_file=? WHERE id=?;";

    @Override
    public Game get(final long id) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET)) {
            statement.setLong(1, id);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find game by ID.", e);
        }
    }

    @Override
    public List<Game> getAll() throws DaoException {
        try (PreparedStatement statement
                = getConnection().prepareStatement(GET_ALL)) {
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot fetch games.", e);
        }
    }

    @Override
    public boolean save(final Game entity) throws DaoException {
        try (PreparedStatement statement
                = getConnection().prepareStatement(SAVE)) {
            setUpStatement(statement, entity);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot save game.");
        }
    }

    @Override
    public boolean update(final Game entity) throws DaoException {
        try (PreparedStatement statement
                = getConnection().prepareStatement(UPDATE)) {
            setUpStatement(statement, entity);
            statement.setLong(3, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot update game.");
        }
    }

    @Override
    public boolean delete(final Game entity) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    private Game buildSingleInstance(final ResultSet rs) throws SQLException {
        if (rs.next()) {
            return buildGame(rs);
        }
        return null;
    }

    private List<Game> buildMultipleInstances(final ResultSet rs)
            throws SQLException {
        List<Game> games = new ArrayList<>();
        while (rs.next()) {
            games.add(buildGame(rs));
        }
        return games;
    }

    private Game buildGame(final ResultSet rs) throws SQLException {
        Game game = new Game();
        game.setId(rs.getLong("id"));
        game.setTitle(rs.getString("title"));
        game.setIconFile(rs.getString("icon_file"));
        return game;
    }

    private void setUpStatement(final PreparedStatement st, final Game game)
            throws SQLException {
        st.setString(1, game.getTitle());
        st.setString(2, game.getIconFile());
    }
}
