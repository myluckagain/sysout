package ru.sysout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sysout.dao.RepoRepository;
import ru.sysout.model.Repo;
import ru.sysout.model.ReposPage;

import java.util.List;

@Service
public class RepoService {
    @Autowired
    private RepoRepository repoRepository;

    public void saveRepos(List<ReposPage> reposPages){
        for (ReposPage reposPage : reposPages)
        {
           List<Repo> repos= reposPage.getItems();
           repoRepository.saveAll(repos);
        }
    }

}
