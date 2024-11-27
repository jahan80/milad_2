package com.jahan.sp1_.DB_Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateTbl {

    private static final Logger logger = LoggerFactory.getLogger(CreateTbl.class);

    private final JdbcTemplate jdbcTemplate;
    private final TblChecker tblChecker;
    public final String tblName = "student_";

    @Autowired
    public CreateTbl(JdbcTemplate jdbcTemplate, TblChecker tblChecker) {
        this.jdbcTemplate = jdbcTemplate;
        this.tblChecker = tblChecker;
        logger.info("CreateTbl initialized");
    }

    public void createTable() {
        logger.info("Checking if table '{}' exists", tblName);
        if (tblChecker.doesTableExist(tblName)) {
            String dropTableSQL = "DROP TABLE " + tblName;
            try {
                jdbcTemplate.execute(dropTableSQL);
                logger.info("Table '{}' dropped successfully", tblName);
            } catch (Exception e) {
                logger.error("Error while dropping the table '{}'", tblName, e);
            }
        }

        String createTableSQL =
                "CREATE TABLE " + tblName + " (" +
                        "id NUMBER PRIMARY KEY, " +
                        "name VARCHAR2(50), " +
                        "national_code VARCHAR2(50), " +
                        "email_id VARCHAR2(50), " +
                        "slevel VARCHAR2(50),"+
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP )" ;

        try {
            jdbcTemplate.execute(createTableSQL);
            logger.info("Table '{}' created successfully", tblName);
        } catch (Exception e) {
            logger.error("Error while creating the table '{}'", tblName, e);
        }
    }
}
