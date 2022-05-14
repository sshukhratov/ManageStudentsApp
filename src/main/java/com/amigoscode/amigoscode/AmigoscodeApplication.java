package com.amigoscode.amigoscode;

import com.amigoscode.amigoscode.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class AmigoscodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmigoscodeApplication.class, args);
    }



}
