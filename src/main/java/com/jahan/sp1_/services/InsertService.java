package com.jahan.sp1_.services;

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

    public void insertUserData(int id, String name, String nationalCode, String email, String slevel) {
        try {
            // اعتبارسنجی و آماده‌سازی داده‌ها
            UserInput.UserDTO userDTO = userInput.validateAndPrepareInputs(id, name, nationalCode, email, slevel);

            // کوئری SQL برای درج داده‌ها در جدول
            String insertQuery = "INSERT INTO student_ (id, name, national_code, email_id, slevel) VALUES (?, ?, ?, ?, ?)";

            // اجرای کوئری
            jdbcTemplate.update(insertQuery, userDTO.getId(), userDTO.getName(), userDTO.getNationalCode(),
                    userDTO.getEmail(), userDTO.getSlevel());

            System.out.println("User data inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while saving user data: " + e.getMessage());
        }
    }
}
