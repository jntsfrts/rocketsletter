package br.com.rocketsletter.repository.impl;

import br.com.rocketsletter.model.User;
import br.com.rocketsletter.service.exception.UserNotFoundException;
import br.com.rocketsletter.repository.mappers.UserRowMapper;
import br.com.rocketsletter.repository.UserDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

        var sql = "INSERT INTO user(ID, EMAIL_ADDRESS, CREATED_AT) VALUES(DEFAULT, ?, ?) ";
        jdbcTemplate.update(sql, user.getEmail(), Timestamp.valueOf(user.getCreatedAt()));

        return user;
    }

    @Override
    public List<User> findAll() {
        var sql = "SELECT id, email_address, created_at FROM user ";

        return jdbcTemplate.query(
                sql, (resultSet, rowNum) ->
                    new UserRowMapper().mapRow(resultSet, rowNum)
        );
    }

    @Override
    public boolean existsUserWith(String email) {
        var sql = "SELECT COUNT(*) FROM user WHERE email_address = ? ";

        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return result != null && result > 0;
    }

    @Override
    public ResponseEntity deleteUser(Integer id) {
        var sql = "DELETE FROM user u WHERE u.ID = ? ";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if(rowsAffected == 1)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}
