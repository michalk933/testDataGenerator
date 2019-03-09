package com.example.testDataGenerator.testDataGenerator.dimain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hobby {

    private String name;

    private int number;

    private String where;

}
