package com.jahan.sp1_.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class GenerateData {
    private static final Logger logger = LoggerFactory.getLogger(InsertService.class);


    private int currentId = 1;
    private final Random random = new Random();

//    public int generateUniqueId() {
//        logger.info("generateUniqueId ok");
//        return currentId++;
//    }

    public String generateRandomName() {
        String[] names = {"jahan", "milad", "ahmad", "mohammad", "parsa", "omid"};
        return names[random.nextInt(names.length)];
    }

    public String generateRandomNationalCode() {
        return String.format("%09d", random.nextInt(1_000_000_000));
    }

    public String generateRandomEmail() {
        return UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public String generateRandomLevel() {
        String[] levels = {"E", "R", "D"};
        return levels[random.nextInt(levels.length)];
    }
}
