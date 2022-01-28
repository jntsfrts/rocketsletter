package br.com.rocketsletter.domain.dto;

import br.com.rocketsletter.domain.model.User;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private Integer id;
    private String email;
    private final LocalDateTime createdAt;

    public UserResponseDTO() {
        this.createdAt = null;
    }

    public UserResponseDTO(User user) {
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
