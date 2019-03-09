package com.example.testDataGenerator.testDataGenerator.dimain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private String firstname;

    private String lastname;

    private int age;

    private int height;

    private int weight;

    private List<Hobby> hobby;


}
