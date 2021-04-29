package chess.domain.piece.strategy;

import chess.domain.order.MoveOrder;
import chess.domain.position.Direction;
import chess.exception.InvalidChessArgumentException;
import chess.exception.InvalidMoveException;

public class KingMoveStrategy extends DefaultMoveStrategy {
    public KingMoveStrategy() {
        super(Direction.everyDirection());
    }

    @Override
    public boolean canMove(MoveOrder moveOrder) {
        if (moveOrder.getRoute().size() != 0) {
            throw new InvalidMoveException("킹이 움직일 수 있는 범위를 벗어났습니다.");
        }
        return super.canMove(moveOrder);
    }
}