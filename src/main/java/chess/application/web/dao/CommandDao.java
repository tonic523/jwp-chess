package chess.application.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommandDao {

    private final JdbcTemplate jdbcTemplate;

    public CommandDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(String command) {
        String sql = "insert into command (command) values (?)";
        jdbcTemplate.update(sql, command);
    }

    public List<String> findAll() {
        String sql = "select * from command";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            return resultSet.getString("command");
        });
    }

    public void clear() {
        String sql = "truncate table command";
        jdbcTemplate.update(sql);
    }
}
