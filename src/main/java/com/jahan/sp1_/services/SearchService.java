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

    // افزودن پارامترهای page و size
    public String searchByPartialNationalCode(String partialNationalCode, int page, int size) {
        // محاسبه offset بر اساس صفحه و اندازه
        int offset = (page - 1) * size;

        // کوئری با استفاده از ROWNUM برای صفحه‌بندی در Oracle 11g
        String searchQuery = "SELECT * FROM ( " +
                "  SELECT student_.*, ROWNUM AS rn " +
                "  FROM student_ " +
                "  WHERE national_code LIKE ? " +
                ") " +
                "WHERE rn BETWEEN ? AND ?";

        StringBuilder result = new StringBuilder();

        // اجرای کوئری با پارامترها
        jdbcTemplate.query(searchQuery, new Object[]{
                "%" + partialNationalCode + "%",  // کد ملی جزئی
                offset + 1,  // آدرس شروع نتایج (محاسبه‌شده برای صفحه)
                offset + size  // آدرس پایان نتایج (محاسبه‌شده برای صفحه)
        }, rs -> {
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
