package com.jahan.sp1_.DB_Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TblChecker {

    private static final Logger logger = LoggerFactory.getLogger(TblChecker.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TblChecker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        logger.info("TblChecker initialized");
    }

    public boolean doesTableExist(String tableName) {
        String query = "SELECT COUNT(*) FROM all_tables WHERE table_name = ?";
        try {
            logger.info("Checking existence of table '{}'", tableName);
            Integer count = jdbcTemplate.queryForObject(query, new Object[]{tableName.toUpperCase()}, Integer.class);
            boolean exists = count != null && count > 0;
            logger.info("Table '{}' exists: {}", tableName, exists);
            return exists;
        } catch (Exception e) {
            logger.error("Error while checking existence of table '{}'", tableName, e);
            return false;
        }
    }
}
