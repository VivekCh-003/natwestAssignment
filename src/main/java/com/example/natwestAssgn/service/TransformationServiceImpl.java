package com.example.natwestAssgn.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransformationServiceImpl implements TransformationService{
    @Override
    public List<Map<String, String>> transformData(List<Map<String, String>> inputRecords, List<Map<String, String>> referenceRecords) {
        List<Map<String, String>> outputRecords = new ArrayList<>();

        for (Map<String, String> input : inputRecords) {
            Map<String, String> output = new LinkedHashMap<>();

            String refkey1 = input.get("refkey1");
            String refkey2 = input.get("refkey2");

            Map<String, String> reference = referenceRecords.stream()
                    .filter(ref -> ref.get("refkey1").equals(refkey1) && ref.get("refkey2").equals(refkey2))
                    .findFirst()
                    .orElse(null);

            if (reference != null) {
                // Transformation rules
                output.put("outfield1", input.get("field1") + input.get("field2"));
                output.put("outfield2", reference.get("refdata1"));
                output.put("outfield3", reference.get("refdata2") + reference.get("refdata3"));
                output.put("outfield4", String.valueOf(Double.parseDouble(input.get("field3")) *
                        Math.max(Double.parseDouble(input.get("field5")), Double.parseDouble(reference.get("refdata4")))));
                output.put("outfield5", String.valueOf(Math.max(Double.parseDouble(input.get("field5")), Double.parseDouble(reference.get("refdata4")))));
            }

            outputRecords.add(output);
        }

        return outputRecords;
    }
}
