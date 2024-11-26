package com.jahan.sp1_.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String searchByPartialNationalCode(String partialNationalCode) {
        String searchQuery = "SELECT * FROM student_ WHERE national_code LIKE ?";
        StringBuilder result = new StringBuilder();

        jdbcTemplate.query(searchQuery, new Object[]{"%" + partialNationalCode + "%"}, rs -> {
            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("id"))
                        .append(", Name: ").append(rs.getString("name"))
                        .append(", National Code: ").append(rs.getString("national_code"))
                        .append(", Email: ").append(rs.getString("email_id"))
                        .append(", Level: ").append(rs.getString("slevel"))
                        .append("\n");
            }
        });

        // بررسی اینکه آیا هیچ نتیجه‌ای یافت نشده است
        return result.length() == 0 ? "No records found" : result.toString();
    }
}
