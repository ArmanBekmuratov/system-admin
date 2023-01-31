package com.abdev.spring.config;

import com.abdev.spring.database.pool.ConnectionPool;
import com.abdev.spring.database.repository.UserRepository;
import com.abdev.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
public class ApplicationConfiguration {

        @Bean("pool2")
        public ConnectionPool pool2(@Value("${db.username}") String username) {
                return new ConnectionPool(username, 20);
        }

        @Bean()
        public ConnectionPool pool3() {
                return new ConnectionPool("test-pool", 25);
        }

}
