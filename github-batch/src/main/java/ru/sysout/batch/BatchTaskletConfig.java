package ru.sysout.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableBatchProcessing
@Configuration
@Profile("tasklet")
public class BatchTaskletConfig {
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private RepoTasklet repoTasklet;

    @Bean
    public Step step() {
        return steps.get("step")
                .tasklet(repoTasklet)
                .build();
    }

    @Bean
    public Job job() {
        return jobs.get("GithubJob")
                .start(step())
                .build();
    }
}
