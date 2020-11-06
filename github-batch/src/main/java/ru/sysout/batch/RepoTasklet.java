package ru.sysout.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sysout.dao.RepoRepository;
import ru.sysout.model.ReposPage;

@Component
public class RepoTasklet implements Tasklet {
    @Autowired
    private RestTemplate template;

    @Autowired
    private RepoRepository repoRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        for (int page = 1; page <= 5; page++) {
            ReposPage repoDto = template.getForObject(
                    "https://api.github.com/search/repositories?q=spring&page=" + page, ReposPage.class);
            repoRepository.saveAll(repoDto.getItems());

            Thread.sleep(1000);
        }
        return RepeatStatus.FINISHED;
    }
}
