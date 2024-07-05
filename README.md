# JobFinderTool

## Overview

This application is designed to scrape job listings from juniors.ro and store them in a database. It periodically checks for new job postings, saves them to a CSV file.
## Features

- **Web Scraping:** Automatically retrieves job listings from juniors.ro based on predefined intervals.
- **Database Integration:** Stores job details (link, title, experience, type, city, company) in a H2 database.
- **CSV Export:** Generates CSV files (`notification.csv`) for all job listings and specific query results, respectively.
- **Logging:** Logs timestamped entries of new job listings to `notification.csv` for tracking updates.

## Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK) version 8 or later
- Apache Maven
- H2 database 
- Git (optional, for cloning the repository)

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/job-scraper.git
   cd job-scraper
   ```

2. **Configure application properties:**

Open src/main/resources/application.properties and configure the following properties:

2.1. **Database connection properties**

```bash
spring.datasource.url=jdbc:h2:mem:jobs
spring.datasource.username=sa
spring.datasource.password=
```
2.2. **Base URL for job search**

```bash
job.base.url=https://www.juniors.ro/jobs?q=
```

2.3. **schema.sql for jobs table**

```bash
create table jobs(
    id int auto_increment,
    job_link varchar(255) NOT NULL,
    job_title varchar(255) NOT NULL,
    experience varchar(255) NOT NULL,
    job_type varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    company varchar(255) NOT NULL
);
```

3. **Build the application:**

```bash
mvn clean package
```

4. **Run the application:**

```bash
java -jar target/job-scraper-1.0.jar
```

## Usage
- **Automatic Job Scraping:** The application will automatically scrape job listings from juniors.ro every 10 minutes (adjustable in NotificationService).
- **CSV Export:** Job data will be exported to notification.csv.
- **Database:** All scraped job listings are stored in the configured H2 database.

## Custom Query
To search for jobs based on specific criteria:

Open NotificationService.java and modify the fetchJobsFromWeb(String keyword) method to include your search criteria.

```bash
// Example: Search jobs requiring "Java" experience
String keyword = "Java";
String url = baseUrl + keyword + "&page=" + page;
```

Run the application to perform the custom query.

## Contributing
Contributions are welcome! Fork the repository and submit pull requests for any improvements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
