package br.com.rocketsletter.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private Integer id;
    private Email email;
    private LocalDateTime createdAt;


    public User(Integer id, Email email) {
        this.id = id;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    //TODO: Separar entidade user para um dto
    public User(Integer id, Email email, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
    }

    public User(Email email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

     public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAtInTimestamp() {
        return Timestamp.valueOf(createdAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
