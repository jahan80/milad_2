package com.jahan.sp1_.services;

import com.jahan.sp1_.DB_Action.CreateTbl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BulkInsertService {

    private final JdbcTemplate jdbcTemplate;
    private final GenerateData generateData;
    private final CreateTbl createTbl;

    @Autowired
    public BulkInsertService(JdbcTemplate jdbcTemplate, GenerateData generateData, CreateTbl createTbl) {
        this.jdbcTemplate = jdbcTemplate;
        this.generateData = generateData;
        this.createTbl = createTbl;
    }

    // متد برای وارد کردن داده‌ها به صورت دسته‌ای
    public void insertGeneratedData(int recordCount) {
        // ابتدا جدول را بررسی و ایجاد می‌کنیم
        createTbl.createTable();

        String insertQuery = "INSERT INTO student_ (id, name, national_code, email_id, slevel) VALUES (?, ?, ?, ?, ?)";

        // لیست برای نگهداری داده‌های ایجاد شده
        List<Object[]> batchArgs = new ArrayList<>();

        // تولید داده‌ها
        for (int i = 0; i < recordCount; i++) {
            Object[] data = new Object[]{
                    generateData.generateUniqueId(),
                    generateData.generateRandomName(),
                    generateData.generateRandomNationalCode(),
                    generateData.generateRandomEmail(),
                    generateData.generateRandomLevel()
            };
            batchArgs.add(data);  // اضافه کردن داده به لیست
        }

        // استفاده از batchInsert برای وارد کردن داده‌ها به صورت دسته‌ای
        jdbcTemplate.batchUpdate(insertQuery, batchArgs);

        System.out.println(recordCount + " records inserted successfully.");
    }
}
