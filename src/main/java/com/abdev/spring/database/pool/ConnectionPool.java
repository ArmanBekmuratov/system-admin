package com.abdev.spring.database.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Component("pool1")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ConnectionPool {

    private final String username;
    private final Integer poolSize;

    @Autowired
    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}")Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("Pool initialization");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Clean pool");
    }
}
