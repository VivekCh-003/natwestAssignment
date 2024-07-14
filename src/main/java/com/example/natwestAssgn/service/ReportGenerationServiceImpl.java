package com.example.natwestAssgn.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class ReportGenerationServiceImpl implements ReportGenerationService{

    @Autowired
    private FileIngestionService fileIngestionService;

    @Autowired
    private TransformationService transformationService;

    @Override
    public void generateReports() {
        try {
            List<Map<String, String>> inputRecords = fileIngestionService.readCsv("src/main/resources/input.csv");
            List<Map<String, String>> referenceRecords = fileIngestionService.readCsv("src/main/resources/reference.csv");

            List<Map<String, String>> transformedRecords = transformationService.transformData(inputRecords, referenceRecords);

            saveToCsv(transformedRecords, "src/main/resources/output.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToCsv(List<Map<String, String>> records, String outputPath) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(outputPath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("outfield1", "outfield2", "outfield3", "outfield4", "outfield5"))) {

            for (Map<String, String> record : records) {
                csvPrinter.printRecord(record.values());
            }
        }
    }
}
