package com.example.demo;

import static java.nio.file.Files.newOutputStream;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvWriter<T> {

    private Class<T> clazz;

    public CsvWriter(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void write(Path filePath, List<T> data) {
        
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(clazz);
        try (var writer = csvMapper.writer(schema)
                .writeValues(newOutputStream(filePath, CREATE, WRITE, TRUNCATE_EXISTING))) {
            writer.writeAll(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
