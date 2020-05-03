package chess.dto.repository;

public class GameIdDto {

    private final Integer gameId;

    public GameIdDto(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getGameId() {
        return gameId;
    }
}
