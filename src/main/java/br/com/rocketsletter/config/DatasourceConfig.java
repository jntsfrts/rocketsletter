package br.com.rocketsletter.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatasourceConfig {

    private String jdbcUrl;
    private String username;
    private String password;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariDataSource datasource = new HikariDataSource();
        datasource.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        datasource.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
        datasource.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));

        return datasource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
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
