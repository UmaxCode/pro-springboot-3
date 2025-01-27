package com.umaxcode.chapter_two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = WebMvcAutoConfiguration.class)
public class ChapterTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChapterTwoApplication.class, args);
    }
}
