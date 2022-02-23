package br.com.rocketsletter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConfigurationProperties("spring.mail")
public class JavaMailSenderConfig {

    private String host;
    private int port;
    private String username;
    private String password;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(getHost());
        mailSender.setPort(getPort());
        mailSender.setUsername(getUsername());
        mailSender.setPassword(getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.connectiontimeout","5000");
        props.put("mail.smtp.timeout","5000");
        props.put("mail.smtp.writetimeout","5000");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
