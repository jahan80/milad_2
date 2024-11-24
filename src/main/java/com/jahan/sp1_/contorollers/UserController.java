package com.jahan.sp1_.contorollers;

import com.jahan.sp1_.services.InsertService;
import com.jahan.sp1_.services.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserInput userInput;
    private final InsertService insertService;

    @Autowired
    public UserController(UserInput userInput, InsertService insertService) {
        this.userInput = userInput;
        this.insertService = insertService;
    }

    @PostMapping("/create")
    public String createUser(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String nationalCode,
                             @RequestParam String email,
                             @RequestParam String slevel) {
        try {
            // اعتبارسنجی ورودی‌ها
            UserInput.UserDTO userDTO = userInput.validateAndPrepareInputs(id, name, nationalCode, email, slevel);

            // ذخیره در دیتابیس
            // ایجاد آرایه از مقادیر userDTO برای ارسال به insertUserData
            Object[] userData = {userDTO.getId(), userDTO.getName(), userDTO.getNationalCode(), userDTO.getEmail(), userDTO.getSlevel()};

            // ارسال داده‌ها به متد insertUserData
            insertService.insertUserData(userData);

            return "User created successfully with ID: " + userDTO.getId();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
