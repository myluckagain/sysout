package ru.sysout.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sysout.model.ReposPage;

@Component
public class RepoPageProcessor implements ItemProcessor<Integer, ReposPage> {
    @Autowired
    private RestTemplate template;
    @Override
    public ReposPage process(Integer integer) throws Exception {
        Thread.sleep(1000);
        System.out.println("trying to download:" );
        ReposPage reposPage = template.getForObject(
                "https://api.github.com/search/repositories?q=spring&page=" + integer, ReposPage.class);
        System.out.println("DOWNLOADED reposPage of size: "+reposPage.getItems().size());

        return reposPage;
    }
}
