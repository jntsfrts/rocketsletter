package br.com.rocketsletter.controller;

import br.com.rocketsletter.service.UserService;
import br.com.rocketsletter.model.User;
import br.com.rocketsletter.service.exception.InvalidEmailException;
import br.com.rocketsletter.service.exception.UserAlreadyExistsException;
import br.com.rocketsletter.dto.UserCreationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserCreationDTO userCreationDTO) {

        try {
            User user = new User();
            BeanUtils.copyProperties(userCreationDTO, user);

            User response = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch(UserAlreadyExistsException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"Conflict\": \"" + exception.getMessage() + "\"}");

        } catch(InvalidEmailException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Bad request\": \"" + exception.getMessage() + "\"}");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {

        return userService.deleteUser(id);
    }
}