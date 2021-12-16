package br.com.rocketsletter.user;

import java.time.LocalDateTime;

public class UserDTO {

    private String email;
    private final LocalDateTime createdAt;


    public UserDTO(User user) {
        this.email = user.getEmail().getAddress();
        this.createdAt = user.getCreatedAt();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
