package chess.dao;

import chess.domain.ChessGameRoom;
import chess.domain.state.Turn;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long createChessGame(ChessGameRoom chessGameRoom) {
        String sql = "insert into chess_game (title, password, turn) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
                    PreparedStatement statement = con.prepareStatement(sql, new String[]{"id"});
                    statement.setString(1, chessGameRoom.getTitle());
                    statement.setString(2, chessGameRoom.getPassword());
                    statement.setString(3, chessGameRoom.getTurn().name());
                    return statement;
                },
                keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<ChessGameRoom> findAllChessGames() {
        String sql = "select id, title, password, turn from chess_game";
        return jdbcTemplate.query(sql, chessGameRoomRowMapper);
    }

    private RowMapper<ChessGameRoom> chessGameRoomRowMapper = (resultSet, rowNum) -> new ChessGameRoom(
            resultSet.getLong("id"),
            resultSet.getString("title"),
            resultSet.getString("password"),
            Turn.valueOf(resultSet.getString("turn")));

    public Turn findChessGameTurn(long id) {
        String sql = "select turn from chess_game where id = ?";

        return jdbcTemplate.queryForObject(sql, Turn.class, id);
    }

    public ChessGameRoom findChessGameRoom(long id) {
        String sql = "select id, title, password, turn from chess_game where id = ?";

        return jdbcTemplate.queryForObject(sql, chessGameRoomRowMapper, id);
    }

    public boolean isExistGameTitle(String title) {
        String sql = "select exists(select * from chess_game where title = ?)";

        return jdbcTemplate.queryForObject(sql, Boolean.class, title);
    }

    public int changeChessGameTurn(long id, Turn turn) {
        String sql = "update chess_game set turn = ? where id = ?";

        return jdbcTemplate.update(sql, turn.name(), id);
    }

    public int deleteChessGame(long id) {
        String sql = "delete from chess_game where id = ?";

        return jdbcTemplate.update(sql, id);
    }
}
