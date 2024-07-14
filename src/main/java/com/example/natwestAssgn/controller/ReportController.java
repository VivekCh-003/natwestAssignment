package com.example.natwestAssgn.controller;

import com.example.natwestAssgn.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportGenerationService reportGenerationService;

//    localhost:8081/generate
    @PostMapping("/generate")
    public ResponseEntity<String> generatedReport(){
        reportGenerationService.generateReports();
        System.out.println("generating");
        return ResponseEntity.ok("Report generation triggered");
    }

}
