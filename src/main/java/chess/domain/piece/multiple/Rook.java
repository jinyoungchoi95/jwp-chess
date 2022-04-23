package chess.domain.piece.multiple;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.LEFT;
import static chess.domain.direction.Direction.RIGHT;
import static chess.domain.direction.Direction.UP;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Rook extends MultipleMovePiece {

    private static final String ROOK_NAME = "rook";
    private static final double ROOK_SCORE = 5;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP, DOWN, RIGHT, LEFT);

    public Rook() {
        super(MOVE_DIRECTIONS);
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public String name() {
        return ROOK_NAME;
    }
}
