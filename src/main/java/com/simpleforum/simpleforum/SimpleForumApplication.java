package com.simpleforum.simpleforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class SimpleForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleForumApplication.class, args);
    }
}
