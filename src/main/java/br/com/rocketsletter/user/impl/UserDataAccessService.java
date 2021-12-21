package br.com.rocketsletter.user.impl;

import br.com.rocketsletter.email.Email;
import br.com.rocketsletter.user.User;
import br.com.rocketsletter.user.UserAlreadyExistsException;
import br.com.rocketsletter.user.UserDAO;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User saveUser(User user) throws DataAccessException, UserAlreadyExistsException {

        User result = findBy(user.getEmail());

        if(result.getId() == null) {
            var sql = "INSERT INTO user(ID, EMAIL_ADDRESS, CREATED_AT) VALUES(DEFAULT, ?, ?) ";
            jdbcTemplate.update(sql, user.getEmail().getAddress(), user.getCreatedAtInTimestamp());

            return user;
        }

        throw new UserAlreadyExistsException(user);
    }

    public User findBy(Email email) {
        var sql = "SELECT id, email_address, created_at FROM user WHERE email_address = ? ";

        try {
            return jdbcTemplate.queryForObject(sql, ((resultSet, rowNum) -> new User(resultSet.getInt("id"),
                    new Email(resultSet.getString("email_address")),
                    resultSet.getTimestamp("created_at").toLocalDateTime()
            )), email.getAddress());

        } catch (EmptyResultDataAccessException exception) {
            return new User();
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
