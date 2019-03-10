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

@Service
public class GenerateJsonService {

    Logger log = LoggerFactory.getLogger(GenerateJsonService.class);

    public void saveDataInFile(Student student, int priority) {
        log.info("START WRITE TO FILE");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("test_data_" + priority + ".json", true));
            writer.append(' ')
                    .append(toJson(student))
                    .append(',')
                    .close();
        } catch (IOException e) {
            throw new RuntimeException("Something wrong during write json to file", e);
        }
        log.info("END WRITE TO FILE");
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

