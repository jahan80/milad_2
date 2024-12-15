package com.jahan.sp1_.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class TimeZoneConfig {

    @PostConstruct
    public void init() {
        // تنظیم تایم‌زون پیش‌فرض به Asia/Tehran
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tehran"));
        System.out.println("Default TimeZone set to: " + TimeZone.getDefault().getID());
    }
}
