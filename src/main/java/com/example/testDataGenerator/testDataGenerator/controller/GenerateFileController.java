package com.example.testDataGenerator.testDataGenerator.controller;

import com.example.testDataGenerator.testDataGenerator.service.GenerateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class GenerateFileController {

    @Autowired
    private GenerateStudentService generateStudentService;

    @PostMapping("/{student}/{hobby}/{priority}")
    public Mono<String> generate(@PathVariable("student") int student, @PathVariable("hobby") int hobby, @PathVariable("priority") int priority){
        generateStudentService.generate(student, hobby, priority);
        return Mono.just("Make");
    }


}
