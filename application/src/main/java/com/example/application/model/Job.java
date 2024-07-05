package com.example.application.model;
import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_link", nullable = false)
    private String jobLink;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "experience", nullable = false)
    private String experience;

    @Column(name = "job_type", nullable = false)
    private String jobType;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "company", nullable = false)
    private String company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
