package com.example.application.service;

import com.example.application.model.Job;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationService {

    private static final String CSV_FILE = "notification.csv";
    private final JobService jobService;

    @Autowired
    public NotificationService(JobService jobService) {
        this.jobService = jobService;
    }

    @Scheduled(fixedRate = 600000)
    public void checkForNewJobs() {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Date", "Link", "City", "Title", "Type", "Company", "Experience"))) {

            List<Job> jobs = jobService.fetchJobsFromWeb();

            for (Job job : jobs) {
                csvPrinter.printRecord(formattedDateTime, job.getJobLink(), job.getCity(), job.getJobTitle(), job.getJobType(), job.getCompany(), job.getExperience());
            }

            csvPrinter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
