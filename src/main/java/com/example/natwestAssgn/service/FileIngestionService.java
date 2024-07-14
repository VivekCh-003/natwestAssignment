package com.example.natwestAssgn.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileIngestionService {
    List<Map<String, String>> readCsv(String s) throws IOException;
}
