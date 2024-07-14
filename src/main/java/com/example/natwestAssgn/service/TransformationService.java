package com.example.natwestAssgn.service;

import java.util.List;
import java.util.Map;

public interface TransformationService {
    List<Map<String, String>> transformData(List<Map<String, String>> inputRecords, List<Map<String, String>> referenceRecords);
}
