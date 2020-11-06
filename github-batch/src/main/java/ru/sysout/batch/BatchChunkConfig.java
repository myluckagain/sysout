package ru.sysout.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.ResourceAccessException;
import ru.sysout.model.ReposPage;

import java.util.Arrays;

@EnableBatchProcessing
@Configuration
@Profile("chunk")
public class BatchChunkConfig {
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    private RepoPageProcessor repoPageProcessor;

    @Autowired
    private RepoPageWriter repoPageWriter;

    @Bean
    public Step step() {
        return steps.get("step")
                .<Integer, ReposPage>chunk(2)
                .reader(getItemReader())
                .processor(repoPageProcessor)
                .writer(repoPageWriter)
                .faultTolerant()
                .retry(ResourceAccessException.class)
                .retryLimit(3)
                .build();
    }

    @Bean
    public Job job() {
        return jobs.get("GithubJob")
                .start(step())
                .build();
    }

    @Bean
    @JobScope
    ItemReader<Integer> getItemReader() {
        return new ListItemReader<>(Arrays.asList(1, 2, 3, 4, 5));
    }
}