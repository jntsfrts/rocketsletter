package br.com.rocketsletter.presentation;

import br.com.rocketsletter.application.service.UserService;
import br.com.rocketsletter.domain.model.User;
import br.com.rocketsletter.application.exception.UserAlreadyExistsException;
import br.com.rocketsletter.domain.dto.UserCreationDTO;
import br.com.rocketsletter.domain.dto.UserMapper;
import br.com.rocketsletter.domain.dto.UserResponseDTO;
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

    /*
    A ideia é essa URL funcionar com um botao 'unsubscribe'
    contendo um ID que acompanhará o email diário.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {

        return service.deleteUser(id);
    }
}