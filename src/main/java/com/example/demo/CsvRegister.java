package com.example.demo;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CsvRegister {

    private final CsvDao csvDao;

    public void register() {
        System.out.println("Start insert");
        long s = System.currentTimeMillis();
        try {
            Path filePath = Paths.get(getClass().getClassLoader().getResource("test.csv").toURI());
            CsvReader<CsvRecord> csvReader = new CsvReader<>(CsvRecord.class);
            List<CsvRecord> records = csvReader.read(filePath);
            ExecutorService service = Executors.newFixedThreadPool(4);
            Future<Integer> future1 = service.submit(() -> csvDao.insert(records));
            Future<Integer> future2 = service.submit(() -> csvDao.insert(records));
            Future<Integer> future3 = service.submit(() -> csvDao.insert(records));
            Future<Integer> future4 = service.submit(() -> csvDao.insert(records));
            try {
                future1.get();
                future2.get();
                future3.get();
                future4.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() - s);
            System.out.println("End insert");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
