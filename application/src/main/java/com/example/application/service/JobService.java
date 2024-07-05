package com.example.application.service;

import com.example.application.model.Job;
import com.example.application.repository.JobRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Value("${jobs.base.url}")
    private String baseUrl;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> fetchJobsFromWeb() {
        int page = 1;
        boolean hasNextPage = true;
        List<Job> jobs = new ArrayList<Job>();

        while(hasNextPage){

            String url = baseUrl + "&page=" + page;
            try {
                Document doc = Jsoup.connect(url).get();
                Elements jobElements = doc.select("li.job");

                for (Element jobElement : jobElements) {
                    String jobLink = jobElement.select("a.btn-url").attr("href");
                    Optional<Job> existingJob = jobRepository.findByJobLink(jobLink);

                    if (existingJob.isEmpty()) {
                        Job job = new Job();
                        job.setJobLink(jobLink);
                        job.setJobTitle(jobElement.select("div.job_header_title h3").text());
                        job.setExperience(jobElement.select("ul.job_requirements li:contains(Experiență solicitată)").text().replace("Experiență solicitată: ", ""));
                        job.setJobType(jobElement.select("ul.job_requirements li:contains(Tip ofertă)").text().replace("Tip ofertă: ", ""));
                        job.setCity(jobElement.select("div.job_header_title strong").text().split("\\|")[0].trim());
                        job.setCompany(jobElement.select("ul.job_requirements li:contains(Companie)").text().replace("Companie: ", ""));
                        saveJob(job);
                        jobs.add(job);
                    }
                }
                Elements nextPageLink = doc.select("li.page-item.active + li.page-item a.page-link");
                if (nextPageLink.isEmpty()) {
                    hasNextPage = false;
                } else {
                    page++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jobs;
    }

    private void saveJob(Job job) {
        jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
