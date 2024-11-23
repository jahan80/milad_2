package com.jahan.sp1_.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInput {

    private final Emailchecker emailchecker;

    @Autowired
    public UserInput(Emailchecker emailchecker) {
        this.emailchecker = emailchecker;
    }

    public Object[] getUserInputs(int id, String name, String nationalCode, String email, String slevel) {

        // بررسی صحت ایمیل
        while (!emailchecker.isValidEmail(email)) {  // استفاده از شیء تزریق شده
            System.out.println("Invalid email format. Please try again.");
            // شما می‌توانید با اضافه کردن ورودی از کنترلر برای ایمیل در اینجا اقدام کنید
        }

        // بررسی صحت سطح (slevel)
        while (!(slevel.equals("E") || slevel.equals("R") || slevel.equals("D"))) {
            System.out.println("Invalid input. Please enter one of the values E, R, or D.");
            // شما می‌توانید با اضافه کردن ورودی از کنترلر برای slevel در اینجا اقدام کنید
        }

        // بازگشت ورودی‌ها به صورت یک آرایه
        return new Object[]{id, name, nationalCode, email, slevel};
    }
}
