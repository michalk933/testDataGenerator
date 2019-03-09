package com.example.testDataGenerator.testDataGenerator.service;


import com.example.testDataGenerator.testDataGenerator.dimain.Hobby;
import com.example.testDataGenerator.testDataGenerator.dimain.Student;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GenerateStudentService {

    Logger log = LoggerFactory.getLogger(GenerateStudentService.class);

    @Autowired
    private GenerateJsonService generateJsonService;

    public void generate(int numStudent, int numHobby, int proryty) {
        log.info("START GENERATE OBJECT");
        geStudent(numStudent, numHobby, proryty);
        log.info("END GENERATE OBJECT");
    }


    private void geStudent(int nummStudent, int numHobby, int priotity) {
        int randomInt = new Random().nextInt(5);
        String randomString = RandomStringUtils.random(10);

        Flux.range(0, nummStudent)
                .map(integer -> Student.builder()
                        .firstname(randomString)
                        .lastname(randomString)
                        .age(randomInt)
                        .height(randomInt)
                        .weight(randomInt)
                        .hobby(IntStream.range(0, numHobby)
                                .mapToObj(value -> getHobbys())
                                .collect(Collectors.toList())
                        )
                        .build())
                .subscribe(student -> generateJsonService.saveDataInFile(student, priotity));
    }


    private Hobby getHobbys() {
        int randomInt = new Random().nextInt(5);
        String randomString = RandomStringUtils.random(10);

        return Hobby.builder()
                .name(randomString)
                .number(randomInt)
                .where(randomString)
                .build();

    }

}
