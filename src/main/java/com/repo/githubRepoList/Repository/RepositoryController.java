package com.repo.githubRepoList.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class RepositoryController {
    RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/user/{username}/repositories")
    public ResponseEntity<?> getRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader)
    {
        return repositoryService.getRepositories(username, acceptHeader);
    }
}
