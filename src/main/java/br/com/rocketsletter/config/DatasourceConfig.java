package br.com.rocketsletter.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DatasourceConfig {

    private String jdbcUrl;
    private String username;
    private String password;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariDataSource datasource = new HikariDataSource();
        datasource.setJdbcUrl(jdbcUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        return datasource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }
}
