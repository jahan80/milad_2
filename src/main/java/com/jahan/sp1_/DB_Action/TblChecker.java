package com.jahan.sp1_.DB_Action;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TblChecker {

    private final JdbcTemplate jdbcTemplate;

    // استفاده از JdbcTemplate به جای Connection مستقیم
    @Autowired
    public TblChecker(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean doesTableExist(String tableName) {
        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
                return resultSet.next();  // اگر جدول وجود داشته باشد، true برمی‌گرداند
            }
        } catch (SQLException e) {
            System.out.println("Error in checking table existence");
            e.printStackTrace();
            return false;
        }
    }
}
