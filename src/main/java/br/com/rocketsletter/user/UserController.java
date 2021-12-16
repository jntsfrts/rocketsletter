package br.com.rocketsletter.user;

import br.com.rocketsletter.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @PostMapping("/new")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserCreationDTO userCreationDTO) {

        User user = null;
        try {
            user = service.saveUser(mapper.toUser(userCreationDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(mapper.toDTO(user), HttpStatus.OK);
    }
}