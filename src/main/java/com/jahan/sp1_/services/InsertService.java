package com.jahan.sp1_.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class InsertService {
    private static final Logger logger = LoggerFactory.getLogger(InsertService.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InsertService(JdbcTemplate jdbcTemplate) {
        logger.info("InsertService ok");
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUserData(Object[] userData) {
        logger.info("insertUserData ok");
        String insertQuery = "INSERT INTO student_ (id, name, national_code, email_id, slevel) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery, userData);
    }
}
