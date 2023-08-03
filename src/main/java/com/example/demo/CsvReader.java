package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvReader<T> {

    private Class<T> clazz;

    public CsvReader(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> read(Path filePath) {
        try (InputStream in = Files.newInputStream(filePath, StandardOpenOption.READ)) {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = csvMapper.schemaFor(clazz);
            List<T> records = new ArrayList<>();
            MappingIterator<T> objectMappingIterator = csvMapper.readerFor(clazz).with(csvSchema).readValues(in);
            while (objectMappingIterator.hasNext()) {
                records.add(objectMappingIterator.next());
            }
            return records;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
