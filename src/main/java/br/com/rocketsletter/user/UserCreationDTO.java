package br.com.rocketsletter.user;

public class UserCreationDTO {

    private String email;

    public UserCreationDTO() {
    }

    public UserCreationDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
