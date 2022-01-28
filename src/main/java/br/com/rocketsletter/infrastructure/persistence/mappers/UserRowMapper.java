package br.com.rocketsletter.infrastructure.persistence.mappers;

import br.com.rocketsletter.domain.model.Email;
import br.com.rocketsletter.domain.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                new Email(resultSet.getString("email_address")),
                resultSet.getTimestamp("created_at").toLocalDateTime()
        );
    }
}
