package com.jahan.sp1_.services;

import com.jahan.sp1_.DB_Action.CreateTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        logger.info("SearchService initialized.");
    }

    public String searchByPartialNationalCode(String partialNationalCode, int page, int size) {
        logger.debug("Received search request with partialNationalCode: {}, page: {}, size: {}",
                partialNationalCode, page, size);

        // محاسبه offset بر اساس صفحه و اندازه
        int offset = (page - 1) * size;
        logger.debug("Calculated offset: {}", offset);

        String searchQuery = "SELECT * FROM ( " +
                "  SELECT "+ CreateTbl.tblName+ ".*, ROWNUM AS rn " +
                "  FROM "+CreateTbl.tblName  +" " +
                "  WHERE national_code LIKE ? " +
                ") " +
                "WHERE rn BETWEEN ? AND ?";

        StringBuilder result = new StringBuilder();

        try {
            jdbcTemplate.query(searchQuery, new Object[]{
                    "%" + partialNationalCode + "%", // فیلتر کد ملی
                    offset + 1,  // شروع نتایج
                    offset + size  // پایان نتایج
            }, rs -> {
                while (rs.next()) {
                    String record = String.format("ID: %d, Name: %s, National Code: %s, Email: %s, Level: %s",
                            rs.getInt("id"), rs.getString("name"), rs.getString("national_code"),
                            rs.getString("email_id"), rs.getString("slevel"));
                    result.append(record).append("\n");
                }
            });
            logger.info("Search completed successfully with {} results.", result.length() > 0 ? "some" : "no");
        } catch (Exception e) {
            logger.error("Error occurred while executing search query", e);
            return "An error occurred while searching. Please check the logs.";
        }

        return result.length() == 0 ? "No records found" : result.toString();
    }
}
