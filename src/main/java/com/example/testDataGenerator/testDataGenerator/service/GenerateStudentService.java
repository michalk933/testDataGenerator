package com.example.testDataGenerator.testDataGenerator.service;


import com.example.testDataGenerator.testDataGenerator.dimain.Hobby;
import com.example.testDataGenerator.testDataGenerator.dimain.Student;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GenerateStudentService {

    Logger log = LoggerFactory.getLogger(GenerateStudentService.class);

    @Autowired
    private GenerateJsonService generateJsonService;

    public void generate(int numStudent, int numHobby, int priority) {
        log.info("START GENERATE TEST DATA");
        Flux.range(0, priority)
                .subscribe(value ->
                        getStudents(numStudent, numHobby)
                                .onBackpressureDrop()
                                .parallel(priority)
                                .runOn(Schedulers.parallel())
                                .subscribe(student -> {
                                    generateJsonService.saveDataInFile(student, value);
                                })
                );
        log.info("END GENERATE TEST DATA");
    }

    private Flux<Student> getStudents(int numStudent, int numHobby) {
        int randomInt = new Random().nextInt(5);
        String randomString = RandomStringUtils.random(10);

        return Flux.range(0, numStudent)
                .map(integer -> Student.builder()
                        .firstname(randomString)
                        .lastname(randomString)
                        .age(randomInt)
                        .height(randomInt)
                        .weight(randomInt)
                        .hobby(IntStream.range(0, numHobby)
                                .mapToObj(value -> getHobbys(randomInt, randomString))
                                .collect(Collectors.toList())
                        )
                        .build());
    }

    private Hobby getHobbys(int randomInt, String randomString) {
        return Hobby.builder()
                .name(randomString)
                .number(randomInt)
                .where(randomString)
                .build();
    }

}
