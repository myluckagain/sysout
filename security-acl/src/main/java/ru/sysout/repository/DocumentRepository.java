package ru.sysout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import ru.sysout.model.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Document> findAll();

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Document getById(Integer id);

    Document save(@Param("document") Document document);

}
