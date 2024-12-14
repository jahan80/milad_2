package com.jahan.sp1_.services;
import com.jahan.sp1_.DB_Action.CreateTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BulkInsertService {

    private static final Logger logger = LoggerFactory.getLogger(BulkInsertService.class);
    private final JdbcTemplate jdbcTemplate;
    private final GenerateData generateData;
    private final CreateTbl createTbl;

    @Autowired
    public BulkInsertService(JdbcTemplate jdbcTemplate, GenerateData generateData, CreateTbl createTbl) {
        this.jdbcTemplate = jdbcTemplate;
        this.generateData = generateData;
        this.createTbl = createTbl;
        logger.info("BulkInsertService initialized");
    }

    public void insertGeneratedData(int recordCount) {
        logger.info("Bulk inserting {} records", recordCount);
        createTbl.createTable();

        String insertQuery = "INSERT INTO  "+  createTbl.tblName+"(name, national_code, email_id, slevel) VALUES (?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();

        for (int i = 0; i < recordCount; i++) {
            Object[] data = new Object[]{
 //                   generateData.generateUniqueId(),
                    generateData.generateRandomName(),
                    generateData.generateRandomNationalCode(),
                    generateData.generateRandomEmail(),
                    generateData.generateRandomLevel()
            };
            batchArgs.add(data);
        }

        jdbcTemplate.batchUpdate(insertQuery, batchArgs);
        logger.info("{} records inserted successfully", recordCount);
    }
}
