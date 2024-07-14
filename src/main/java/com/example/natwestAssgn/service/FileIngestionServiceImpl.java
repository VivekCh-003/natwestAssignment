package com.example.natwestAssgn.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileIngestionServiceImpl implements FileIngestionService {
    @Override
    public List<Map<String, String>> readCsv(String filePath) throws IOException {
        List<Map<String, String>> records = new ArrayList<>();
        try (
                Reader reader = new InputStreamReader(new BOMInputStream(Files.newInputStream(Paths.get(filePath))));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {

            for (String header : csvParser.getHeaderMap().keySet()) {
            }

            for (CSVRecord csvRecord : csvParser) {
                Map<String, String> record = new LinkedHashMap<>();
                for (String header : csvParser.getHeaderMap().keySet()) {
                    String value = csvRecord.get(header);
                    record.put(header, value);
                }
                records.add(record);
            }
        }
        return records;
    }
}
