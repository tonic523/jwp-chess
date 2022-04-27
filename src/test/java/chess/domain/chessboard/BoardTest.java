package chess.domain.chessboard;

import chess.domain.chessboard.Board;
import chess.domain.chessboard.position.File;
import chess.domain.chessboard.position.Position;
import chess.domain.chessboard.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @DisplayName("8x8의 보드판이 생성되는지 확인한다.")
    @Test
    void construct_board() {
        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @DisplayName("선택한 위치에 기물이 없으면 예외를 발생 시킨다.")
    @Test
    void move_none_exception() {
        assertThatThrownBy(() -> board.move(Position.of(Rank.THREE, File.D), Position.of(Rank.FOUR, File.D)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 위치에 기물이 없습니다.");
    }
}
