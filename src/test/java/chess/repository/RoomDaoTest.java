package chess.repository;

import chess.entity.RoomEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @DisplayName("체스방을 추가한다.")
    @Test
    void insert() {
        final RoomEntity roomEntity = new RoomEntity(1L, "room1", "1234");
        final RoomEntity insertRoom = roomDao.insert(roomEntity);

        Assertions.assertThat(insertRoom).isEqualTo(roomEntity);
    }

    @DisplayName("id로 체스방을 조회한다.")
    @Test
    void findById() {
        // given
        final RoomEntity expected = roomDao.insert(new RoomEntity(1L, "room1", "1234"));
        // when
        RoomEntity actual = roomDao.findById(expected.getId());
        // then
        Assertions.assertThat(actual.getName()).isEqualTo("room1");
        Assertions.assertThat(actual.getPassword()).isEqualTo("1234");

    }
}
