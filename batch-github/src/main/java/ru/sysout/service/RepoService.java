package ru.sysout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sysout.dto.RepoRepository;
import ru.sysout.model.Repo;
import ru.sysout.model.ReposPage;

import java.util.List;

@Service
public class RepoService {
    @Autowired
    private RepoRepository repoRepository;

    public void saveRepos(List<ReposPage> reposPageDtos){
        for (ReposPage pageDto : reposPageDtos)
        {
           List<Repo> repos= pageDto.getItems();
           repoRepository.saveAll(repos);
        }
    }

}
