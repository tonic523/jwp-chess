package chess.domain.piece;

import static chess.domain.chessboard.position.Direction.*;

import chess.domain.chessboard.position.Direction;
import chess.domain.game.Player;
import chess.domain.chessboard.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class King extends Piece {

    private static final double SCORE = 0;

    public King(final Player player, final Symbol symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> board) {
        final List<Position> positions = getDirections().stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> !board.get(position).isSame(player))
                .collect(Collectors.toUnmodifiableList());
        return positions.contains(target);
    }

    @Override
    protected List<Direction> getDirections() {
        return List.of(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double addTo(final double score) {
        return score + SCORE;
    }
}
