package chess.domain.piece;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.piece.Piece.createBlackPiece;
import static chess.domain.piece.Piece.createWhitePiece;

import chess.domain.Position;
import chess.domain.piece.multiple.Bishop;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.multiple.Rook;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.single.King;
import chess.domain.piece.single.Knight;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private PieceFactory() {
        throw new AssertionError();
    }

    public static Map<Position, Piece> createNewChessBoard(long chessGameId) {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createNewBlackPieces(chessGameId));
        pieces.putAll(createNewWhitePieces(chessGameId));
        return pieces;
    }

    private static Map<Position, Piece> createNewBlackPieces(long chessGameId) {
        Map<Position, Piece> blackPieces = new HashMap<>(Map.of(
                Position.of('a', '8'), createBlackPiece(chessGameId, new Rook()),
                Position.of('b', '8'), createBlackPiece(chessGameId, new Knight()),
                Position.of('c', '8'), createBlackPiece(chessGameId, new Bishop()),
                Position.of('d', '8'), createBlackPiece(chessGameId, new Queen()),
                Position.of('e', '8'), createBlackPiece(chessGameId, new King()),
                Position.of('f', '8'), createBlackPiece(chessGameId, new Bishop()),
                Position.of('g', '8'), createBlackPiece(chessGameId, new Knight()),
                Position.of('h', '8'), createBlackPiece(chessGameId, new Rook())
        ));
        for (char i = 0; i < 8; i++) {
            blackPieces.put(Position.of((char) ('a' + i), '7'), createBlackPiece(chessGameId, new Pawn(BLACK)));
        }
        return blackPieces;
    }

    private static Map<Position, Piece> createNewWhitePieces(long chessGameId) {
        Map<Position, Piece> whitePieces = new HashMap<>(Map.of(
                Position.of('a', '1'), createWhitePiece(chessGameId, new Rook()),
                Position.of('b', '1'), createWhitePiece(chessGameId, new Knight()),
                Position.of('c', '1'), createWhitePiece(chessGameId, new Bishop()),
                Position.of('d', '1'), createWhitePiece(chessGameId, new Queen()),
                Position.of('e', '1'), createWhitePiece(chessGameId, new King()),
                Position.of('f', '1'), createWhitePiece(chessGameId, new Bishop()),
                Position.of('g', '1'), createWhitePiece(chessGameId, new Knight()),
                Position.of('h', '1'), createWhitePiece(chessGameId, new Rook())
        ));
        for (char i = 0; i < 8; i++) {
            whitePieces.put(Position.of((char) ('a' + i), '2'), createWhitePiece(chessGameId, new Pawn(WHITE)));
        }
        return whitePieces;
    }
}
