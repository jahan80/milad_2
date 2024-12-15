package com.jahan.sp1_.services;

import com.jahan.sp1_.DB_Action.CreateTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InsertService {

    private final JdbcTemplate jdbcTemplate;
    private final UserInput userInput;

    @Autowired
    public InsertService(JdbcTemplate jdbcTemplate, UserInput userInput) {
        this.jdbcTemplate = jdbcTemplate;
        this.userInput = userInput;
    }

    public void insertUserData(String name, String nationalCode, String email, String slevel) {
        try {
            UserInput.UserDTO userDTO = userInput.validateAndPrepareInputs(name, nationalCode, email, slevel);
            String insertQuery = "INSERT INTO " + CreateTbl.tblName + " (name, national_code, email_id, slevel) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertQuery, userDTO.getName(), userDTO.getNationalCode(), userDTO.getEmail(), userDTO.getSlevel());
            System.out.println("User data inserted successfully");
        } catch (Exception e) {
            System.err.println("Error while saving user data: " + e.getMessage());
            // پرتاب مجدد خطا
            throw new RuntimeException("Error during user data insertion: " + e.getMessage(), e);
        }
    }
}
