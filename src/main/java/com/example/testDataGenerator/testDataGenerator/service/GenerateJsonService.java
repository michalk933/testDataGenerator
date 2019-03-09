package com.example.testDataGenerator.testDataGenerator.service;

import com.example.testDataGenerator.testDataGenerator.dimain.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Service
public class GenerateJsonService {

    Logger log = LoggerFactory.getLogger(GenerateJsonService.class);

    private Date date = new Date();

    public void saveDataInFile(Student student, int priority) {
        log.info("START GENERATE FILE");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("test_data_" + priority + ".json", true));
            writer.append(' ');
            writer.append(toJson(student));
            writer.append(',');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("END GENERATE FILE");
    }

    private String toJson(Student student) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}

