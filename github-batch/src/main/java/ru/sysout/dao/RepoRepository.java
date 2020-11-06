package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.Repo;

public interface RepoRepository extends JpaRepository<Repo, Long> {
}
