package br.com.rocketsletter.repository.mappers;

import br.com.rocketsletter.model.Email;
import br.com.rocketsletter.model.User;
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
