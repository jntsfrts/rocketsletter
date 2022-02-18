package br.com.rocketsletter.dto;

import br.com.rocketsletter.model.User;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private Integer id;
    private String email;
    private final LocalDateTime createdAt;

    public UserResponseDTO() {
        this.createdAt = null;
    }

    public UserResponseDTO(Integer id, String email, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
    }

    public UserResponseDTO(User user) {
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
