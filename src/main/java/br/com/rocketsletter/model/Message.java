package br.com.rocketsletter.model;

import java.time.LocalDateTime;

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
