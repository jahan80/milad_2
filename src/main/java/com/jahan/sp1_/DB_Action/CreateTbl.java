package com.jahan.sp1_.DB_Action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateTbl {

    private final JdbcTemplate jdbcTemplate;
    private final TblChecker tblChecker;
    public final String tblName = "student_";

    @Autowired
    public CreateTbl(JdbcTemplate jdbcTemplate, TblChecker tblChecker) {
        this.jdbcTemplate = jdbcTemplate;
        this.tblChecker = tblChecker;
    }

    public void createTable() {
        // حذف جدول اگر وجود دارد
        if (tblChecker.doesTableExist(tblName)) {
            String dropTableSQL = "DROP TABLE " + tblName;
            try {
                jdbcTemplate.execute(dropTableSQL);
                System.out.println("Table '" + tblName + "' dropped.");
            } catch (Exception e) {
                System.out.println("Error while dropping the table:");
                e.printStackTrace();
            }
        }

        // ایجاد جدول جدید
        String createTableSQL =
                "CREATE TABLE " + tblName + " (" +
                        "id NUMBER PRIMARY KEY, " +
                        "name VARCHAR2(50), " +
                        "national_code VARCHAR2(50), " +
                        "email_id VARCHAR2(50), " +
                        "slevel VARCHAR2(50))";

        try {
            jdbcTemplate.execute(createTableSQL);
            System.out.println("Table '" + tblName + "' created.");
        } catch (Exception e) {
            System.out.println("Error while creating the table:");
            e.printStackTrace();
        }
    }
}
