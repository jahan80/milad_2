package com.jahan.sp1_.contorollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jahan.sp1_.services.UserInput;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserInput userInput;

    @Autowired
    public UserController(UserInput userInput) {
        this.userInput = userInput;
    }

    @PostMapping("/create")
    public String createUser(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String nationalCode,
                             @RequestParam String email,
                             @RequestParam String slevel) {

        Object[] userInputs = userInput.getUserInputs(id, name, nationalCode, email, slevel);
        // در اینجا می‌توانید داده‌ها را در دیتابیس ذخیره کنید یا هر کار دیگری انجام دهید

        return "User created with ID: " + userInputs[0];
    }
}
