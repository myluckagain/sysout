package ru.sysout;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    private Job job;
    @Autowired
    private JobLauncher jobLauncher;
    @Value("${file.input}")
    private String input;

    @Value("${file.output}")
    private String output;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        JobParametersBuilder jobParameters = new JobParametersBuilder();
        jobParameters.addString("file.input", input);
        jobParameters.addString("file.output", output);
        jobParameters.addString("trial","1");

        jobLauncher.run(job, jobParameters.toJobParameters());
    }
}
