package ru.sysout.rest;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.sysout.model.Document;
import ru.sysout.repository.DocumentRepository;
import ru.sysout.security.PermissionService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DocumentController {

    private final DocumentRepository repository;
    private final PermissionService permissionService;

    @GetMapping("/document")
    public List<Document> getAll() {
        return repository.findAll();
    }

    @GetMapping("/document/{id}")
    public Document getById(@PathVariable("id") Integer id) {
        return repository.getById(id);
    }

    @PreAuthorize("hasPermission(#document, 'WRITE')")
    @PutMapping("/document/{id}")
    public Document edit(@PathVariable("id") Integer id, @RequestBody Document document) {
        document.setId(id);
        return repository.save(document);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/document")
    public Document post(@RequestBody Document document, Authentication authentication) {
        permissionService.addPermissionForUser(document, BasePermission.WRITE, authentication.getName());
        permissionService.addPermissionForUser(document, BasePermission.READ, authentication.getName());
        return repository.save(document);
    }
}
