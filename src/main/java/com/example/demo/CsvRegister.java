package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CsvRegister {

    private final CsvDao csvDao;

    public CsvRegister(CsvDao csvDao) {
        this.csvDao = csvDao;
    }

    public void register() {
        try {
            // クラスパス上のtest.csvファイルを検索してURIオブジェクトを生成
            URI csvUri = this.getClass().getClassLoader().getResource("test.csv").toURI();
            // URIオブジェクトを元に文字列のファイルパスを生成
            String filePath = Paths.get(csvUri).toAbsolutePath().toString();
            // CSV読み込みクラス
            // <>はジェネリクスといって、そのクラスで扱うデータ型を制限することができる
            // 例えばCsvReaderでは読み込んだCSVをCsvRecordクラスにマッピングすることができ、それ以外のクラスにはマッピングすることができない。
            // そのほかにもArrayListはジェネリクスを指定しない場合、Object型をaddしたりgetしたりできるが、ArrayList<String>のようにジェネリクスを指定するとString型のみを扱うことができるように制限することができる。
            CsvReader<CsvRecord> csvReader = new CsvReader<>(CsvRecord.class);
            // CSV読み込み
            List<CsvRecord> records = csvReader.read(filePath);
            // DB登録
            csvDao.insert(records);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
