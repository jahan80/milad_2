package com.jahan.sp1_.contorollers;


import com.jahan.sp1_.services.BulkInsertService;
import com.jahan.sp1_.services.InsertService;
import com.jahan.sp1_.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private final BulkInsertService bulkInsertService;

    private final InsertService insertService;
    private final SearchService searchService;

    @Autowired
    public MainController(BulkInsertService bulkInsertService, InsertService insertService, SearchService searchService) {
        this.bulkInsertService = bulkInsertService;
        this.insertService = insertService;
        this.searchService = searchService;
    }

    @PostMapping("/init")
    public String initializeDatabase() {
        bulkInsertService.insertGeneratedData(500);
        return "Database initialized successfully!";
    }

    @PostMapping("/user")
    public String addUser(@RequestParam int id, @RequestParam String name,
                          @RequestParam String nationalCode, @RequestParam String email,
                          @RequestParam String slevel) {
        Object[] userData = {id, name, nationalCode, email, slevel};
        insertService.insertUserData(userData);
        return "User added successfully!";
    }

    @GetMapping("/search")
        public String search(@RequestParam String partialNationalCode) {
        return searchService.searchByPartialNationalCode(partialNationalCode);
    }
}
