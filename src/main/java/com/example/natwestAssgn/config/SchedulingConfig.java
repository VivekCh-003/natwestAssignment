package com.example.natwestAssgn.config;

import com.example.natwestAssgn.controller.ReportController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Autowired
    private ReportController reportController;

    @Scheduled(cron = "0 0/10 * * * ?") // Runs every 10 minutes
    public void scheduleReportGeneration() {
        reportController.generatedReport();
    }
}
