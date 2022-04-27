package chess.domain.piece;

import chess.domain.chessboard.position.Direction;
import chess.domain.game.Player;
import chess.domain.chessboard.position.Position;

import java.util.*;

import static chess.domain.chessboard.position.Direction.*;

public final class Queen extends Piece {

    private static final double SCORE = 9;

    public Queen(final Player player, final Symbol symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> board) {
        final Set<Position> positions = new HashSet<>();
        for (final Direction direction : getDirections()) {
            positions.addAll(createMovablePositions(direction, source, board));
        }
        return positions.contains(target);
    }

    private List<Position> createMovablePositions(final Direction direction, final Position source, final Map<Position, Piece> board) {
        final List<Position> positions = new ArrayList<>();
        if (!source.isInBoardAfterMoved(direction)) {
            return positions;
        }
        Position nextPosition = source.createMovablePosition(direction);
        while (board.get(nextPosition).isSame(Player.NONE) && nextPosition.isInBoardAfterMoved(direction)) {
            positions.add(nextPosition);
            nextPosition = nextPosition.createMovablePosition(direction);
        }
        if (!board.get(nextPosition).isSame(this.player)) {
            positions.add(nextPosition);
        }
        return positions;
    }

    @Override
    protected List<Direction> getDirections() {
        return List.of(EAST, WEST, SOUTH, NORTH, SOUTHEAST, SOUTHWEST, NORTHEAST, NORTHWEST);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
