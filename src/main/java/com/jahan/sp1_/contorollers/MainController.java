package com.jahan.sp1_.contorollers;

import com.jahan.sp1_.services.BulkInsertService;
import com.jahan.sp1_.services.InsertService;
import com.jahan.sp1_.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final BulkInsertService bulkInsertService;
    private final InsertService insertService;
    private final SearchService searchService;

    @Autowired
    public MainController(BulkInsertService bulkInsertService, InsertService insertService, SearchService searchService) {
        this.bulkInsertService = bulkInsertService;
        this.insertService = insertService;
        this.searchService = searchService;
        logger.info("MainController initialized");
    }

    @PostMapping("/init")
    public String initializeDatabase() {
        logger.info("Initializing database with bulk insert");
        bulkInsertService.insertGeneratedData(500);
        logger.info("Database initialized successfully");
        return "Database initialized successfully!";
    }


    @PostMapping("/user")
    public String addUser(@RequestParam int id, @RequestParam String name,
                          @RequestParam String nationalCode, @RequestParam String email,
                          @RequestParam String slevel) {
        try {
            insertService.insertUserData(id, name, nationalCode, email, slevel);
            return "User added successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }


    @GetMapping("/search")
    public String search(@RequestParam String partialNationalCode,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "10") int size) {
        logger.info("Searching users with partialNationalCode: {}, page: {}, size: {}", partialNationalCode, page, size);
        String result = searchService.searchByPartialNationalCode(partialNationalCode, page, size);
        logger.info("Search completed with result: {}", result.isEmpty() ? "No results" : "Results found");
        return result;
    }
}
