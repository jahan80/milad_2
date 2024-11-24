package com.jahan.sp1_.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BulkInsertService {

    private final JdbcTemplate jdbcTemplate;
    private final GenerateData generateData;

    @Autowired
    public BulkInsertService(JdbcTemplate jdbcTemplate, GenerateData generateData) {
        this.jdbcTemplate = jdbcTemplate;
        this.generateData = generateData;
    }

    public void insertGeneratedData(int recordCount) {
        String insertQuery = "INSERT INTO student_ (id, name, national_code, email_id, slevel) VALUES (?, ?, ?, ?, ?)";
        for (int i = 0; i < recordCount; i++) {
            Object[] data = new Object[]{
                    generateData.generateUniqueId(),
                    generateData.generateRandomName(),
                    generateData.generateRandomNationalCode(),
                    generateData.generateRandomEmail(),
                    generateData.generateRandomLevel()
            };
            jdbcTemplate.update(insertQuery, data);
        }
    }
}
