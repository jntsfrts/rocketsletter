package br.com.rocketsletter.message;

import br.com.rocketsletter.launch.Launch;
import br.com.rocketsletter.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class Message {

    private Integer id;
    private LocalDateTime sentDate;
    private String content;


    public Integer getId() {
        return id;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
