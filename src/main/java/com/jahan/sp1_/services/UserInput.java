package com.jahan.sp1_.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInput {

    private final EmailChecker emailChecker;

    @Autowired
    public UserInput(EmailChecker emailChecker) {
        this.emailChecker = emailChecker;
    }

    // تعریف کلاس UserDTO به صورت داخلی
    public static class UserDTO {
        private int id;
        private String name;
        private String nationalCode;
        private String email;
        private String slevel;

        // Constructor
        public UserDTO(int id, String name, String nationalCode, String email, String slevel) {
            this.id = id;
            this.name = name;
            this.nationalCode = nationalCode;
            this.email = email;
            this.slevel = slevel;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNationalCode() {
            return nationalCode;
        }

        public void setNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSlevel() {
            return slevel;
        }

        public void setSlevel(String slevel) {
            this.slevel = slevel;
        }
    }

    // متد validateAndPrepareInputs
    public UserDTO validateAndPrepareInputs(int id, String name, String nationalCode, String email, String slevel) {
        if (!emailChecker.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        if (!slevel.matches("[ERD]")) {
            throw new IllegalArgumentException("Invalid level: " + slevel);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (!nationalCode.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid national code: must be 10 digits");
        }

        return new UserDTO(id, name, nationalCode, email, slevel);
    }
}


