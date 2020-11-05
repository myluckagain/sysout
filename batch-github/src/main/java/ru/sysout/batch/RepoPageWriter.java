package ru.sysout.batch;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sysout.model.ReposPage;
import ru.sysout.service.RepoService;

import java.util.List;

@Component
public class RepoPageWriter implements ItemWriter<ReposPage> {
    @Autowired
    private RepoService repoDtoService;

    @Override
    public void write(List<? extends ReposPage> list) throws Exception {
        repoDtoService.saveRepos((List<ReposPage>) list);
    }

    @AfterChunk
    public void afterChunk(){
        System.out.println("AFTER CHUNK");
    }
}
