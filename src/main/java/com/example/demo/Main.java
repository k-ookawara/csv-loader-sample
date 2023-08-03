package com.example.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        var records = new ArrayList<CsvRecord>();

        try {
            Map<String, Method> methods = Stream.of(CsvRecord.class.getMethods())
                    .filter(m -> m.getName().startsWith("set")).collect(Collectors.toMap(m -> m.getName(), m -> m));
            for (int i = 0; i < 20000; i++) {
                var csvRecord = new CsvRecord();
                for (int j = 1; j <= 10; j++) {
                    var uuid = UUID.randomUUID().toString();
                    methods.get("setCol" + j).invoke(csvRecord, uuid);
                }
                records.add(csvRecord);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            Path outputPath = Paths.get(Main.class.getClassLoader().getResource("test.csv").toURI());
            new CsvWriter<>(CsvRecord.class).write(outputPath, records);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
