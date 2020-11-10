package ru.sysout.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sysout.Number;
import ru.sysout.NumberRepository;

import java.util.Arrays;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    private static final Logger LOGGER= LoggerFactory.getLogger(BatchConfig.class);
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private NumberRepository numberRepository;

    @Bean
    public Job job() {
        return jobs.get("job")
                .start(step())
                .build();
    }


    @Bean
    public Step step() {

        return steps.get("step")
                .<Integer, Integer>chunk(5)
                .reader(itemReader())
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .skipLimit(3)
                .skip(Exception.class)
                .writer(itemWriter())
                .build();
    }
    @Bean
    public ItemReader<Integer> itemReader() {
        return new ListItemReader<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,10));
    }


    @Bean
     public ItemWriter<Integer> itemWriter() {

        return items -> {
            System.out.println(items);
            for (Integer item : items) {
                System.out.println("writer item = " + item);
                numberRepository.save(new Number(item));

                if (item.equals(7)) {
                    throw new Exception("7 выбрасывает исключение");
                }

            }
        };
    }


}