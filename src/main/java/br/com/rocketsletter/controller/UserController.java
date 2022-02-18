package br.com.rocketsletter.controller;

import br.com.rocketsletter.service.UserService;
import br.com.rocketsletter.model.User;
import br.com.rocketsletter.service.exception.UserAlreadyExistsException;
import br.com.rocketsletter.dto.UserCreationDTO;
import br.com.rocketsletter.dto.UserMapper;
import br.com.rocketsletter.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;


    @PostMapping("/new")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserCreationDTO userCreationDTO) {

        try {
            User user = service.saveUser(mapper.toUser(userCreationDTO));
            return new ResponseEntity<>(mapper.toDTO(user), HttpStatus.CREATED);

        } catch (DataAccessException | UserAlreadyExistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {

        return service.deleteUser(id);
    }
}