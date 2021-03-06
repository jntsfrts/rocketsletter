package br.com.rocketsletter.repository.mappers;

import br.com.rocketsletter.dto.UserResponseDTO;
import br.com.rocketsletter.model.User;
import br.com.rocketsletter.model.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                resultSet.getString("user_id"),
                resultSet.getString("email_address"),
                resultSet.getTimestamp("created_at").toLocalDateTime()
        );

        return UserMapper.toUser(userResponseDTO);
    }
}
