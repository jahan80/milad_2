package com.jahan.sp1_.contorollers;


import org.springframework.web.bind.annotation.*;
import DB_Action.db_conection;
import DB_Action.insert;
import data_.bulk_insert;
import DB_Action.search_;

@RestController
@RequestMapping("/api")
public class MainController {

    @PostMapping("/init")
    public String initializeDatabase() {
        bulk_insert bulkinsert = new bulk_insert();
        bulkinsert.insertGeneratedData();
        return "Database initialized successfully!";
    }

    @PostMapping("/userset")
    public String userSet(@RequestBody UserRequest userRequest) {
        insert dbActions = new insert();
        dbActions.insertUserData(userRequest.toObjectArray());
        return "User data inserted successfully!";
    }

    @GetMapping("/getrecord")
    public String getRecord(@RequestParam String partialNationalCode) {
        db_conection con = new db_conection();
        con.connect();
        search_ search = new search_(con.getConnection());
        String result = search.searchByPartialNationalCode(partialNationalCode);
        con.disconnect();
        return result;
    }
}
