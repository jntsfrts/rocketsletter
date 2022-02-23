package br.com.rocketsletter.repository.impl;

import br.com.rocketsletter.dto.UserResponseDTO;
import br.com.rocketsletter.model.User;
import br.com.rocketsletter.model.UserMapper;
import br.com.rocketsletter.service.exception.UserNotFoundException;
import br.com.rocketsletter.repository.mappers.UserRowMapper;
import br.com.rocketsletter.repository.UserDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User saveUser(User user) throws DataAccessException {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        var sql = "INSERT INTO \"user\"(USER_ID, EMAIL_ADDRESS, CREATED_AT) VALUES(DEFAULT, ?, ?) ";

        jdbcTemplate.update(sql, user.getEmail(), Timestamp.valueOf(user.getCreatedAt()));
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] {"user_id"});
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(user.getCreatedAt()));

            return preparedStatement;
        }, keyHolder);

        String generatedId = keyHolder.getKeys().get("USER_ID").toString();
        UserResponseDTO dto = new UserResponseDTO(generatedId, user.getEmail(), user.getCreatedAt());

        return UserMapper.toUser(dto);
    }

    @Override
    public List<User> findAll() {
        var sql = "SELECT user_id, email_address, created_at FROM \"user\" ";

        return jdbcTemplate.query(
                sql, (resultSet, rowNum) ->
                    new UserRowMapper().mapRow(resultSet, rowNum)
        );
    }

    @Override
    public boolean existsUserWith(String email) {
        var sql = "SELECT COUNT(*) FROM \"user\" u WHERE u.email_address = ? ";

        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, email);

        return result > 0;
    }

    @Override
    public Integer deleteUser(String id) {

        var sql = "DELETE FROM \"user\" u WHERE u.USER_ID = '" +id +"'";

        int rowsAffected = jdbcTemplate.update(sql);

        if(rowsAffected == 1)
            return rowsAffected;

        throw new UserNotFoundException();
    }


}
