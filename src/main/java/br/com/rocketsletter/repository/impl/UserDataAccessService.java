package br.com.rocketsletter.repository.impl;

import br.com.rocketsletter.model.Email;
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

import java.util.List;

@Repository
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User saveUser(User user) throws DataAccessException {

        var sql = "INSERT INTO user(ID, EMAIL_ADDRESS, CREATED_AT) VALUES(DEFAULT, ?, ?) ";
        jdbcTemplate.update(sql, user.getEmail().getAddress(), user.getCreatedAtInTimestamp());

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

    public User findBy(Email email) throws UserNotFoundException {
        var sql = "SELECT id, email_address, created_at FROM user WHERE email_address = ? ";

        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) ->
                    new UserRowMapper().mapRow(resultSet, rowNum),
                    email.getAddress());

        } catch (EmptyResultDataAccessException exception) {
            throw new UserNotFoundException();
        }
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
